package view;

import model.Book;

public class LibraryView {

    // 메뉴 출력
    public void showMenu() {
        System.out.println("============================");
        System.out.println("       도서 관리 시스템       ");
        System.out.println("============================");
        System.out.println("1. 도서 추가");
        System.out.println("2. 도서 목록 출력");
        System.out.println("3. 도서 정보 수정");
        System.out.println("4. 도서 대출");
        System.out.println("5. 도서 반납");
        System.out.println("6. 도서 삭제");
        System.out.println("0. 종료");
        System.out.println("============================");
        System.out.print("선택: ");
    }

    // 일반 메시지 출력
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    // 도서 목록 출력
    public void showBooks(Book[] books, int count) {
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
}