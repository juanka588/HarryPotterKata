package domain;

import java.util.Arrays;

public class Basket {
    public static final double SHELF_PRICE = 8.0d;
    public static final double TWO_DIFFERENT_BOOKS_DISCOUNT = 0.95;

    public double getPrice(Book... books) {
        long numDistinct = Arrays.stream(books).distinct().count();
        if (numDistinct > 1) {
            return books.length * SHELF_PRICE * TWO_DIFFERENT_BOOKS_DISCOUNT;
        }
        return books.length * SHELF_PRICE;
    }
}
