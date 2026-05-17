package model;

public class LibraryModel {
    private Book[] books;
    private int count;

    public LibraryModel() {
        this.books = new Book[100];
        this.count = 0;
    }

    // 도서 추가
    public void addBook(Book book) {
        books[count++] = book;
    }

    // 전체 도서 배열 반환 (view에서 출력용)
    public Book[] getBooks() {
        return books;
    }

    // 현재 도서 수 반환
    public int getCount() {
        return count;
    }

    // 도서 수정 - 성공 true, 실패 false
    public boolean updateBook(String title, String newAuthor, String newCategory, int newPrice, int newYear) {
        Book b = findBook(title);
        if (b == null) return false;
        b.setAuthor(newAuthor);
        b.setCategory(newCategory);
        b.setPrice(newPrice);
        b.setYear(newYear);
        return true;
    }

    // 도서 삭제 - 성공 true, 실패 false
    public boolean deleteBook(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equals(title)) {
                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--count] = null;
                return true;
            }
        }
        return false;
    }

    // 도서 대출 - 0: 없음, 1: 이미대출중, 2: 성공
    public int loanBook(String title) {
        Book b = findBook(title);
        if (b == null) return 0;
        if (b.isLoaned()) return 1;
        b.setLoaned(true);
        return 2;
    }

    // 도서 반납 - 0: 없음, 1: 대출중아님, 2: 성공
    public int returnBook(String title) {
        Book b = findBook(title);
        if (b == null) return 0;
        if (!b.isLoaned()) return 1;
        b.setLoaned(false);
        return 2;
    }

    // 제목으로 도서 찾기
    private Book findBook(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }
}