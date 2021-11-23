package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LotCollectionTest {

    private static final Book BOOK_1 = new Book("Book1", 1.);
    private static final Book BOOK_2 = new Book("Book2", 2.);
    private static final Book BOOK_3 = new Book("Book3", 3.);

    private static Stream<Arguments> provideBooksAndExpectedLotSize() {
        return Stream.of(
                Arguments.of(new Book[]{BOOK_1, BOOK_2}, 1),
                Arguments.of(new Book[]{BOOK_1, BOOK_2},1),
                Arguments.of(new Book[]{BOOK_1, BOOK_2, BOOK_1}, 2),
                Arguments.of(new Book[]{BOOK_1, BOOK_2, BOOK_1, BOOK_3, BOOK_3, BOOK_1, BOOK_2}, 3)
        );
    }

    private static Stream<Arguments> provideBooksAndExpectedTotalPrice() {
        return Stream.of(
                Arguments.of(new Book[]{BOOK_1, BOOK_2}, 3.),
                Arguments.of(new Book[]{BOOK_1, BOOK_2, BOOK_1}, 4.),
                Arguments.of(new Book[]{BOOK_1, BOOK_2, BOOK_1, BOOK_3, BOOK_3, BOOK_1, BOOK_2}, 13.)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBooksAndExpectedLotSize")
    void addAll_groupsBooksInLot(Book[] books, int expectedSize) {
        final LotCollection sut = new LotCollection();
        sut.addAll(books);
        assertEquals(expectedSize, sut.size());
    }

    @ParameterizedTest
    @MethodSource("provideBooksAndExpectedTotalPrice")
    void getTotalPrice(Book[] books, Double expectedTotalPrice) {
        final LotCollection sut = new LotCollection();
        sut.addAll(books);
        assertEquals(expectedTotalPrice, sut.getTotalPrice());
    }
}