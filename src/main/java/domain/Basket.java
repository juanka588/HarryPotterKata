package domain;

import java.util.Arrays;

public class Basket {

    public double getPrice(Book... books) {
        final double totalPrice = Arrays.stream(books)
                .mapToDouble(Book::getPrice)
                .sum();
        return totalPrice * getDiscountRate(books);
    }

    private double getDiscountRate(final Book... books) {
        if(books.length == 1) {
            return 1.0;
        } else if (books.length == 2 && getDistinctBookNumber(books) == books.length) {
            return 0.95;
        } else if (books.length == 3 && getDistinctBookNumber(books) == books.length) {
            return 0.90;
        }
        return 1.0;
    }

    private long getDistinctBookNumber(Book... books) {
        return Arrays.stream(books).map(Book::getTitle).distinct().count();
    }
}
