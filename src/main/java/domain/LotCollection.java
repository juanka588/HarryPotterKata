package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class LotCollection {

    private final Set<Lot> lots;
    private final DiscountRule[] discountRules;

    private LotCollection(DiscountRule... discountRules) {
        this.lots = new HashSet<>();
        this.discountRules = discountRules;
    }

    public static LotCollection of(Book... books) {
        final var discounts = new DiscountRule[]{
                new DiscountRule(lot -> lot.size() == 2, 0.05),
                new DiscountRule(lot -> lot.size() == 3, 0.1),
                new DiscountRule(lot -> lot.size() == 4, 0.20),
                new DiscountRule(lot -> lot.size() == 5, 0.25)
        };
        final LotCollection lotCollection = new LotCollection(discounts);
        lotCollection.addAll(books);
        return lotCollection;
    }

    private void addAll(Book... books) {
        Arrays.stream(books).forEach(this::add);
    }

    private void add(Book book) {
        lots.stream()
                .filter(notContain(book))
                .findFirst()
                .ifPresentOrElse(
                        lot -> lot.add(book),
                        addNewLotOf(book)
                );
    }

    private Runnable addNewLotOf(Book book) {
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
