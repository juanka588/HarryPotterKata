package domain;


public class Basket {

    public double getPrice(Book... books) {
        return LotCollection.of(books)
                .getTotalPrice();
    }

}
