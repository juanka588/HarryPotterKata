package domain;

public class Basket {
    public double getPrice(Book... books) {
        double total = 0.0;
        for (Book book : books) {
            total += book.getBasePrice();
        }
        double discount = getDiscountFor(books);
        return total * discount;
    }

    private double getDiscountFor(Book... books) {
        if (books.length > 1) {
            return 1.0 - 0.05; // 5% discount
        }
        return 1.0;
    }
}
