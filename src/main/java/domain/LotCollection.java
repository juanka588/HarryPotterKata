package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class LotCollection {

    private final Set<Lot> lots;
    private final Map<Predicate<Lot>, Double> discountRules;

    private LotCollection(Map<Predicate<Lot>, Double> discountRules) {
        this.lots = new HashSet<>();
        this.discountRules = discountRules;
    }

    public static LotCollection of(Book... books) {
        final Map<Predicate<Lot>, Double> discounts = Map.of(
                lot -> lot.size() == 2, 0.95,
                lot -> lot.size() == 3, 0.90,
                lot -> lot.size() == 4, 0.80,
                lot -> lot.size() == 5, 0.75
        );
        final LotCollection result = new LotCollection(discounts);
        Arrays.stream(books).forEach(result::add);
        return result;
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

    private static class Lot {
        private final Set<Book> books;
        private Map<Predicate<Lot>, Double> discountRules;

        private Lot(Map<Predicate<Lot>, Double> discountRules) {
            this.discountRules = discountRules;
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
            return books.stream()
                    .mapToDouble(Book::getPrice)
                    .sum() * discountRate();
        }

        private double discountRate() {
            final double discountRate = discountRules.entrySet()
                    .stream()
                    .filter(e -> e.getKey().test(this))
                    .mapToDouble(Map.Entry::getValue).sum();
            return discountRate == 0 ? 1 : discountRate;
        }

        public int size() {
            return books.size();
        }
    }
}
