package domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Lot {
    private final Set<Book> books;
    private final Set<DiscountRule> discountRules;

    public Lot(DiscountRule... discountRules) {
        this.discountRules = Set.of(discountRules);
        this.books = new HashSet<>();
    }


    public boolean contains(Book book) {
        return books.contains(book);
    }

    public void add(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "{ lot: " + books + "}";
    }

    public double getPrice() {
        return applyDiscount(books.stream()
                .mapToDouble(Book::getPrice)
                .sum());
    }

    private double applyDiscount(double price) {
        return discountRate()
                .map(discount -> price * (1 - discount))
                .orElse(price);
    }



    private Optional<Double> discountRate() {
        return discountRules
                .stream()
                .filter(discountRule -> discountRule.test(this))
                .findFirst()
                .map(DiscountRule::getDiscount);
    }

    public int size() {
        return books.size();
    }
}
