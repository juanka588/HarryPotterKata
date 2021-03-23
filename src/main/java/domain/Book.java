package domain;

public class Book {
    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public double getBasePrice() {
        return 8.0;
    }

    public String getTitle() {
        return title;
    }
}
