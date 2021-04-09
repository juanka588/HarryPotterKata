package domain;

public class Basket {

    public static final double BOOK_UNIT_PRICE = 8.0;

    public double getPrice(Book... books){
        return BOOK_UNIT_PRICE;
    }
}
