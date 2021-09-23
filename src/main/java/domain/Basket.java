package domain;

public class Basket {
    public static final double SHELF_PRICE = 8.0d;

    public double getPrice(Book... books) {
        return books.length * SHELF_PRICE;
    }
}
