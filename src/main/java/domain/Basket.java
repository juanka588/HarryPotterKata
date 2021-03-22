package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, List<Book>> booksByTitle = Arrays.stream(books).collect(Collectors.groupingBy(Book::getTitle));

        return getDiscountForDistinctNumberOfBooks(booksByTitle.keySet().size());
    }

    private double getDiscountForDistinctNumberOfBooks(int numberOfDistinctBooks) {
        switch (numberOfDistinctBooks) {
            case 2:
                return 1.0 - 0.05; // 5% discount
            case 3:
                return 1.0 - 0.10; // 10% discount
            case 4:
                return 1.0 - 0.20; // 20% discount
            case 5:
                return 1.0 - 0.25; // 25% discount
            default:
                return 1.0;
        }
    }
}
