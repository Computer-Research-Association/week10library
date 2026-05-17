package model;

import java.sql.*;

public class LibraryModel {
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    public LibraryModel() {
        initDB();
    }

    // DB 및 테이블 초기화
    private void initDB() {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                author TEXT,
                category TEXT,
                price INTEGER,
                year INTEGER,
                is_loaned INTEGER DEFAULT 0
            )
        """;
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 도서 추가
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, category, price, year) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.getPrice());
            pstmt.setInt(5, book.getYear());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 전체 도서 배열 반환 (view 호환용)
    public Book[] getBooks() {
        String sql = "SELECT * FROM books";
        Book[] temp = new Book[100];
        int idx = 0;
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book b = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getInt("year")
                );
                b.setLoaned(rs.getInt("is_loaned") == 1);
                temp[idx++] = b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    // 현재 도서 수 반환
    public int getCount() {
        String sql = "SELECT COUNT(*) FROM books";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 도서 수정 - 성공 true, 실패 false
    public boolean updateBook(String title, String newAuthor, String newCategory, int newPrice, int newYear) {
        String sql = "UPDATE books SET author=?, category=?, price=?, year=? WHERE title=?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newAuthor);
            pstmt.setString(2, newCategory);
            pstmt.setInt(3, newPrice);
            pstmt.setInt(4, newYear);
            pstmt.setString(5, title);
            return pstmt.executeUpdate() > 0; // 영향받은 행이 1 이상이면 성공
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 도서 삭제 - 성공 true, 실패 false
    public boolean deleteBook(String title) {
        String sql = "DELETE FROM books WHERE title=?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 도서 대출 - 0: 없음, 1: 이미대출중, 2: 성공
    public int loanBook(String title) {
        Book b = findBook(title);
        if (b == null) return 0;
        if (b.isLoaned()) return 1;

        String sql = "UPDATE books SET is_loaned=1 WHERE title=?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    // 도서 반납 - 0: 없음, 1: 대출중아님, 2: 성공
    public int returnBook(String title) {
        Book b = findBook(title);
        if (b == null) return 0;
        if (!b.isLoaned()) return 1;

        String sql = "UPDATE books SET is_loaned=0 WHERE title=?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    // 제목으로 도서 찾기 (내부 헬퍼)
    private Book findBook(String title) {
        String sql = "SELECT * FROM books WHERE title=?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Book b = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getInt("year")
                );
                b.setLoaned(rs.getInt("is_loaned") == 1);
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}