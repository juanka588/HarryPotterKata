package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class LotCollection {

    private final Set<Lot> lots;

    private LotCollection() {
        this.lots = new HashSet<>();
    }

    public static LotCollection of(Book... books) {
        final LotCollection result = new LotCollection();
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
        return () -> lots.add(Lot.of(book));
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

        private Lot() {
            this.books = new HashSet<>();
        }

        public static Lot of(Book book) {
            final Lot result = new Lot();
            result.add(book);
            return result;
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
                    .sum();
        }
    }
}
