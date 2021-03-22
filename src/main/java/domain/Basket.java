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
        if (books.length == 2) {
            if (books[0].getTitle().equals(books[1].getTitle())) {
                return 1.0;
            }
            return 1.0 - 0.05; // 5% discount
        }
        if (books.length == 3) {
            return 1.0 - 0.10; // 10% discount
        }
        if (books.length == 4) {
            return 1.0 - 0.20; // 20% discount
        }
        if (books.length == 5) {
            return 1.0 - 0.25; // 25% discount
        }
        return 1.0;
    }
}
