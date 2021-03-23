package domain;

import java.util.*;
import java.util.stream.Collectors;

public class BookStore {
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
            boolean isBookAdded = false;
            for (Bucket bucket : bookBuckets) {
                if (!bucket.hasBook(book) && !bucket.isFull()) {
                    bucket.addBook(book);
                    isBookAdded = true;
                    break;
                }
            }

            if (!isBookAdded) {
                Bucket newBucket = new Bucket();
                newBucket.addBook(book);
                bookBuckets.add(newBucket);
            }

            bookBuckets.sort(Comparator.comparing(Bucket::getNumberOfBooks));
        }


        Optional<Double> reduce =
                bookBuckets
                        .stream()
                        .map(Bucket::getBucketPrice)
                        .reduce(Double::sum);

        return reduce.isPresent() ? reduce.get() : 0;
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
