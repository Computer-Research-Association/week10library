package controller;

import model.LibraryModel;
import view.LibraryView;
import model.Book;
import java.util.Scanner;

public class LibraryController {
    private LibraryModel model;
    private LibraryView view;
    private Scanner scanner;

    public LibraryController(LibraryModel model, LibraryView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            view.showMenu();
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> handleAdd();
                case 2 -> handlePrintAll();
                case 3 -> handleUpdate();
                case 4 -> handleLoan();
                case 5 -> handleReturn();
                case 6 -> handleDelete();
                case 0 -> {
                    view.showMessage("프로그램을 종료합니다.");
                    return;
                }
                default -> view.showMessage("잘못된 입력입니다.");
            }
        }
    }

    // 도서 추가
    private void handleAdd() {
        view.showMessage("=== 도서 추가 ===");
        view.showMessage("제목: ");
        String title = scanner.nextLine();
        view.showMessage("저자: ");
        String author = scanner.nextLine();
        view.showMessage("카테고리: ");
        String category = scanner.nextLine();
        view.showMessage("가격: ");
        int price = Integer.parseInt(scanner.nextLine().trim());
        view.showMessage("출판연도: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        Book book = new Book(title, author, category, price, year);
        model.addBook(book);
        view.showMessage("도서가 추가되었습니다.");
    }
    

    // 도서 수정
    private void handleUpdate() {
        view.showMessage("=== 도서 수정 ===");
        view.showMessage("수정할 도서 제목: ");
        String title = scanner.nextLine();
        view.showMessage("새 저자: ");
        String newAuthor = scanner.nextLine();
        view.showMessage("새 카테고리: ");
        String newCategory = scanner.nextLine();
        view.showMessage("새 가격: ");
        int newPrice = Integer.parseInt(scanner.nextLine().trim());
        view.showMessage("새 출판연도: ");
        int newYear = Integer.parseInt(scanner.nextLine().trim());

        model.updateBook(title, newAuthor, newCategory, newPrice, newYear);
        view.showMessage("도서 정보가 수정되었습니다.");
    }

    //도서 목록 출력
    private void handlePrintAll() {
        view.showMessage("=== 도서 목록 ===");
        model.printAll();
    }

    // 도서 대출
    private void handleLoan() {
        view.showMessage("=== 도서 대출 ===");
        view.showMessage("대출할 도서 제목: ");
        String title = scanner.nextLine();
        model.loanBook(title);
    }

    // 도서 반납
    private void handleReturn() {
        view.showMessage("=== 도서 반납 ===");
        view.showMessage("반납할 도서 제목: ");
        String title = scanner.nextLine();
        model.returnBook(title);
    }

    // 도서 삭제
    private void handleDelete() {
        view.showMessage("=== 도서 삭제 ===");
        view.showMessage("삭제할 도서 제목: ");
        String title = scanner.nextLine();
        model.deleteBook(title);
        view.showMessage("도서가 삭제되었습니다.");
    }
}