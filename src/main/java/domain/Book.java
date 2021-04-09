package domain;

public class Book {
    private final String title;

    public int getId() {
        return id;
    }

    private final int id;

    public Book(String title, int id) {
        this.title = title;
        this.id = id;
    }
}
