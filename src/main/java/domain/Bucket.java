package domain;

import java.util.ArrayList;
import java.util.List;

class Bucket {

    private final List<Book> books;

    public Bucket() {
        books = new ArrayList<>();
    }

    public void add(Book book) {
        this.books.add(book);
    }

    public int size() {
        return this.books.size();
    }

    private double computeDiscount() {
        int numDistinct = size();

        switch (numDistinct) {
            case 2:
                return Basket.TWO_DIFFERENT_BOOKS_DISCOUNT;
            case 3:
                return Basket.THREE_DIFFERENT_BOOKS_DISCOUNT;
            case 4:
                return Basket.FOUR_DIFFERENT_BOOKS_DISCOUNT;
                case 5:
                return Basket.FIVE_DIFFERENT_BOOKS_DISCOUNT;
            default:
                return 1;
        }
    }

    public double getPrice() {
        return computeDiscount() * size() * Basket.SHELF_PRICE;
    }
}
