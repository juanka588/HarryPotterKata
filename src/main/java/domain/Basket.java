package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Basket {
    static final int BOOK_PRICE = 8;

    static final Map<Integer, Double> numberOfBooksToReduction = new HashMap<>();

    static {
        numberOfBooksToReduction.put(5, 0.75);
        numberOfBooksToReduction.put(4, 0.8);
        numberOfBooksToReduction.put(3, 0.9);
        numberOfBooksToReduction.put(2, 0.95);
        numberOfBooksToReduction.put(1, 1.0);
        numberOfBooksToReduction.put(0, 0.0);
    }


    public double getPrice(Book... books) {
        return getPrice(
                Arrays
                        .stream(books)
                        .collect(Collectors.toList())
        );
    }

    private double getPrice(List<Book> books) {
        List<Bucket> bookBuckets = new ArrayList<>();
        for (Book book : books) {

            final var bucket = getOrCreateBucket(bookBuckets, book);

            bucket.addBook(book);
        }


        Optional<Double> reduce =
                bookBuckets
                        .stream()
                        .map(Bucket::getBucketPrice)
                        .reduce(Double::sum);

        return reduce.isPresent() ? reduce.get() : 0;
    }

    private Bucket getOrCreateBucket(List<Bucket> bookBuckets, Book book) {
        bookBuckets.sort(Comparator.comparing(Bucket::getNumberOfBooks));

        for (Bucket bucket : bookBuckets) {
            if (!bucket.hasBook(book) && !bucket.isFull()) {
                return bucket;
            }
        }

        final var newBucket = new Bucket();
        bookBuckets.add(newBucket);
        return newBucket;
    }

    static class Bucket {
        private static final int BUCKET_LIMIT = 5;

        Set<Integer> bookIds;
        List<Book> books;

        Bucket() {
            bookIds = new HashSet<>();
            books = new ArrayList<>();
        }

        public boolean hasBook(Book book) {
            return bookIds.contains(book.getId());
        }

        public boolean isFull() {
            return getNumberOfBooks() == BUCKET_LIMIT;
        }

        public void addBook(Book book) {
            bookIds.add(book.getId());
            books.add(book);
        }

        public double getBucketPrice() {
            int numberOfBooks = bookIds.size();
            int basePrice = BOOK_PRICE * numberOfBooks;

            return basePrice * getReduction(numberOfBooks);
        }

        private double getReduction(int numberOfBooks) {
            return numberOfBooksToReduction.get(numberOfBooks);
        }

        public int getNumberOfBooks() {
            return this.bookIds.size();
        }
    }
}
