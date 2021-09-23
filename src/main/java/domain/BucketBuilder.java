package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class BucketBuilder {
    final Map<String, List<Book>> groupedBooksByTitle;

    BucketBuilder(Book... books) {
        groupedBooksByTitle = Arrays
                .stream(books)
                .collect(Collectors.groupingBy(Book::getTitle));
    }

    Bucket createBucket() {
        Bucket group = new Bucket();
        for (String title : groupedBooksByTitle.keySet()) {
            List<Book> booksOfSeries = groupedBooksByTitle.get(title);
            Book book = booksOfSeries.remove(0);
            group.add(book);
        }
        cleanEmptyList();
        return group;
    }

    void cleanEmptyList() {
        List<String> collect = groupedBooksByTitle.entrySet()
                .stream()
                .filter(e -> e.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for (String toRemove : collect) {
            groupedBooksByTitle.remove(toRemove);
        }
    }

    boolean hasNextBucket() {
        return !groupedBooksByTitle.isEmpty();
    }

}
