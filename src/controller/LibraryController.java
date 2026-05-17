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
                case 0 -> { view.showExit(); return; }
                default -> view.showInvalidInput();
            }
        }
    }

    private void handleAdd() {
        view.showAddHeader();
        view.promptTitle(); String title = scanner.nextLine();
        view.promptAuthor(); String author = scanner.nextLine();
        view.promptCategory(); String category = scanner.nextLine();
        view.promptPrice(); int price = Integer.parseInt(scanner.nextLine().trim());
        view.promptYear(); int year = Integer.parseInt(scanner.nextLine().trim());

        model.addBook(new Book(title, author, category, price, year));
        view.showAddSuccess();
    }

    private void handlePrintAll() {
        view.showPrintAllHeader();
        view.showBooks(model.getBooks(), model.getCount());
    }

    private void handleUpdate() {
        view.showUpdateHeader();
        view.promptTitleToUpdate(); String title = scanner.nextLine();
        view.promptNewAuthor(); String newAuthor = scanner.nextLine();
        view.promptNewCategory(); String newCategory = scanner.nextLine();
        view.promptNewPrice(); int newPrice = Integer.parseInt(scanner.nextLine().trim());
        view.promptNewYear(); int newYear = Integer.parseInt(scanner.nextLine().trim());

        boolean result = model.updateBook(title, newAuthor, newCategory, newPrice, newYear);
        if (result) view.showUpdateSuccess();
        else view.showNotFound();
    }

    private void handleLoan() {
        view.showLoanHeader();
        view.promptTitleToLoan(); String title = scanner.nextLine();

        int result = model.loanBook(title);
        if (result == 0) view.showNotFound();
        else if (result == 1) view.showAlreadyLoaned();
        else view.showLoanSuccess(title);
    }

    private void handleReturn() {
        view.showReturnHeader();
        view.promptTitleToReturn(); String title = scanner.nextLine();

        int result = model.returnBook(title);
        if (result == 0) view.showNotFound();
        else if (result == 1) view.showNotLoaned();
        else view.showReturnSuccess(title);
    }

    private void handleDelete() {
        view.showDeleteHeader();
        view.promptTitleToDelete(); String title = scanner.nextLine();

        boolean result = model.deleteBook(title);
        if (result) view.showDeleteSuccess();
        else view.showNotFound();
    }
}