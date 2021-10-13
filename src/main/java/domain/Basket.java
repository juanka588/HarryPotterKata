package domain;

import java.util.Arrays;

public class Basket {

    public static final double BASE_PRICE = 8.0;

    public double getPrice(Book... books){
        if(books.length > 1 && Arrays.stream(books).map(Book::getTitle).distinct().count() == books.length) {
            return books.length * BASE_PRICE * .95;
        }
        return books.length * BASE_PRICE;
    }
}
