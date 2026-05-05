package model;

public class Book {
    private String title;
    private String author;
    private String category;
    private int price;
    private int year;
    private boolean isLoaned;

    public Book(String title, String author, String category, int price, int year) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.year = year;
        this.isLoaned = false;
    }

    // Getter
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getPrice() { return price; }
    public int getYear() { return year; }
    public boolean isLoaned() { return isLoaned; }

    // Setter
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(int price) { this.price = price; }
    public void setYear(int year) { this.year = year; }
    public void setLoaned(boolean loaned) { this.isLoaned = loaned; }
}