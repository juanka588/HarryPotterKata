package domain;

public class Basket {
    public double getPrice(Book... books){
        return books.length * 8.0;
    }
}
