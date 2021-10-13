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
        } else if (books.length == 2 && getDistinctBookNumber(books) == books.length) {
            return 0.95;
        } else if (books.length == 3 && getDistinctBookNumber(books) == books.length) {
            return 0.90;
        }
        return 1.0;
    }

    private long getDistinctBookNumber(Book[] books) {
        return Arrays.stream(books).map(Book::getTitle).distinct().count();
    }
}
