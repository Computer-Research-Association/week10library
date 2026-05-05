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
        System.out.println("도서가 추가되었습니다: " + book.getTitle());
    }

    // 도서 목록 출력
    public void printAll() {
        if (count == 0) {
            System.out.println("등록된 도서가 없습니다.");
            return;
        }
        for (int i = 0; i < count; i++) {
            Book b = books[i];
            System.out.println("----------------------------");
            System.out.println("제목: " + b.getTitle());
            System.out.println("저자: " + b.getAuthor());
            System.out.println("카테고리: " + b.getCategory());
            System.out.println("가격: " + b.getPrice());
            System.out.println("출판연도: " + b.getYear());
            System.out.println("대출여부: " + (b.isLoaned() ? "대출중" : "가능"));
        }
    }

    // 도서 수정
    public void updateBook(String title, String newAuthor, String newCategory, int newPrice, int newYear) {
        Book b = findBook(title);
        if (b == null) {
            System.out.println("해당 도서를 찾을 수 없습니다.");
            return;
        }
        b.setAuthor(newAuthor);
        b.setCategory(newCategory);
        b.setPrice(newPrice);
        b.setYear(newYear);
        System.out.println("도서 정보가 수정되었습니다.");
    }

    // 도서 삭제
    public void deleteBook(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equals(title)) {
                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--count] = null;
                System.out.println("도서가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 도서를 찾을 수 없습니다.");
    }

    // 도서 대출
    public void loanBook(String title) {
        Book b = findBook(title);
        if (b == null) {
            System.out.println("해당 도서를 찾을 수 없습니다.");
            return;
        }
        if (b.isLoaned()) {
            System.out.println("이미 대출중인 도서입니다.");
            return;
        }
        b.setLoaned(true);
        System.out.println("대출되었습니다: " + title);
    }

    // 도서 반납
    public void returnBook(String title) {
        Book b = findBook(title);
        if (b == null) {
            System.out.println("해당 도서를 찾을 수 없습니다.");
            return;
        }
        if (!b.isLoaned()) {
            System.out.println("대출중이 아닌 도서입니다.");
            return;
        }
        b.setLoaned(false);
        System.out.println("반납되었습니다: " + title);
    }

    // 도서 검색 (내부용)
    private Book findBook(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }
}