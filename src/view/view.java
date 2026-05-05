package view;

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

    // 메시지 출력
    public void showMessage(String msg) {
        System.out.println(msg);
    }
}