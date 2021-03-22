package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Basket {
    public double getPrice(Book... books) {
        double total = 0.0;
        List<List<Book>> groupOfBooks = buildBookGroups(books);
        for (List<Book> group : groupOfBooks) {
            total += getPricePerBookGroup(group);
        }
        return total;
    }

    private List<List<Book>> buildBookGroups(Book[] books) {
        Map<String, List<Book>> booksByTitle = Arrays.stream(books).collect(Collectors.groupingBy(Book::getTitle));
        List<List<Book>> groups = new ArrayList<>();

        int remainingBooks = booksByTitle.values().stream().mapToInt(List::size).sum();
        while (remainingBooks > 0) {
            List<Book> group = new ArrayList<>();
            for (String bookTitle : booksByTitle.keySet()) {
                List<Book> booksOfTitle = booksByTitle.get(bookTitle);
                if (!booksOfTitle.isEmpty()) {
                    group.add(booksOfTitle.remove(booksOfTitle.size() - 1));
                }
            }
            groups.add(group);

            remainingBooks = booksByTitle.values().stream().mapToInt(List::size).sum();
        }
        return groups;
    }

    private double getPricePerBookGroup(Collection<Book> distinctBooks) {
        double total = 0.0;
        for (Book book : distinctBooks) {
            total += book.getBasePrice();
        }
        double discount = getDiscountForDistinctNumberOfBooks(distinctBooks.size());
        return total * discount;
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
