package domain;

public class Basket {

    public static final double BASE_PRICE = 8.0;

    public double getPrice(Book... books){
        return books.length * BASE_PRICE;
    }
}
