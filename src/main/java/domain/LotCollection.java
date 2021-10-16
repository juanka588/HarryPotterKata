package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class LotCollection {

    private final Set<Lot> lots;
    private final DiscountRule[] discountRules;

    public LotCollection(DiscountRule... discountRules) {
        this.lots = new HashSet<>();
        this.discountRules = discountRules;
    }

    public void addAll(Book... books) {
        Arrays.stream(books).forEach(this::add);
    }

    private void add(Book book) {
        lots.stream()
                .filter(notContain(book))
                .findFirst()
                .ifPresentOrElse(
                        lot -> lot.add(book),
                        addNewLot(book)
                );
    }

    private Runnable addNewLot(Book book) {
        return () -> {
            final Lot newLot = new Lot(discountRules);
            newLot.add(book);
            lots.add(newLot);
        };
    }

    private Predicate<Lot> notContain(Book book) {
        return lot -> !lot.contains(book);
    }

    @Override
    public String toString() {
        return lots.toString();
    }

    public double getTotalPrice() {
        return lots.stream()
                .mapToDouble(Lot::getPrice)
                .sum();
    }

    public int size() {
        return this.lots.size();
    }

}
