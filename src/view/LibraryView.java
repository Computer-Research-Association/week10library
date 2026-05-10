package view;

import model.Book;

public class LibraryView {

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

    public void showAddHeader() { System.out.println("=== 도서 추가 ==="); }
    public void showUpdateHeader() { System.out.println("=== 도서 수정 ==="); }
    public void showPrintAllHeader() { System.out.println("=== 도서 목록 ==="); }
    public void showLoanHeader() { System.out.println("=== 도서 대출 ==="); }
    public void showReturnHeader() { System.out.println("=== 도서 반납 ==="); }
    public void showDeleteHeader() { System.out.println("=== 도서 삭제 ==="); }

    public void promptTitle() { System.out.print("제목: "); }
    public void promptAuthor() { System.out.print("저자: "); }
    public void promptCategory() { System.out.print("카테고리: "); }
    public void promptPrice() { System.out.print("가격: "); }
    public void promptYear() { System.out.print("출판연도: "); }
    public void promptTitleToUpdate() { System.out.print("수정할 도서 제목: "); }
    public void promptNewAuthor() { System.out.print("새 저자: "); }
    public void promptNewCategory() { System.out.print("새 카테고리: "); }
    public void promptNewPrice() { System.out.print("새 가격: "); }
    public void promptNewYear() { System.out.print("새 출판연도: "); }
    public void promptTitleToLoan() { System.out.print("대출할 도서 제목: "); }
    public void promptTitleToReturn() { System.out.print("반납할 도서 제목: "); }
    public void promptTitleToDelete() { System.out.print("삭제할 도서 제목: "); }

    public void showAddSuccess() { System.out.println("도서가 추가되었습니다."); }
    public void showUpdateSuccess() { System.out.println("도서 정보가 수정되었습니다."); }
    public void showDeleteSuccess() { System.out.println("도서가 삭제되었습니다."); }
    public void showNotFound() { System.out.println("해당 도서를 찾을 수 없습니다."); }
    public void showAlreadyLoaned() { System.out.println("이미 대출중인 도서입니다."); }
    public void showNotLoaned() { System.out.println("대출중이 아닌 도서입니다."); }
    public void showLoanSuccess(String title) { System.out.println("대출되었습니다: " + title); }
    public void showReturnSuccess(String title) { System.out.println("반납되었습니다: " + title); }
    public void showExit() { System.out.println("프로그램을 종료합니다."); }
    public void showInvalidInput() { System.out.println("잘못된 입력입니다."); }

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