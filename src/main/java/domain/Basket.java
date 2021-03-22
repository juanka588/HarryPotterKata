package domain;

public class Basket {
    public double getPrice(Book... books) {
        double total = 0.0;
        for (Book book : books) {
            total += book.getBasePrice();
        }
        return total;
    }
}
