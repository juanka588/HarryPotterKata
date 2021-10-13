package domain;

import java.util.Arrays;

public class Basket {

    public static final double BASE_PRICE = 8.0;

    public double getPrice(Book... books) {
        return books.length * BASE_PRICE * getDiscountRate(books);
    }

    public double getDiscountRate(final Book... books) {
        if(books.length == 1) {
            return 1.0;
        } else if (Arrays.stream(books).map(Book::getTitle).distinct().count() == books.length) {
            return 0.95;
        }
        return 1.0;
    }
}
