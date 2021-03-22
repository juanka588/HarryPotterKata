package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @org.junit.jupiter.api.Test
    void getPriceForEmptyBasket() {
        Basket sut = new Basket();
        double current = sut.getPrice();
        assertEquals(0.0, current);
    }

    @org.junit.jupiter.api.Test
    void getPriceForOneBookOnTheBasket() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("HP1"));
        assertEquals(8.0, current);
    }

    @org.junit.jupiter.api.Test
    void getPriceForTwoBooksOnTheSeries() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("HP1"), new Book("HP2"));
        assertEquals(8.0 * 2 * 0.95, current);
    }

    @org.junit.jupiter.api.Test
    void getPriceForThreeBooksOnTheSeries() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("HP1"), new Book("HP2"), new Book("HP3"));
        assertEquals(8.0 * 3 * 0.90, current);
    }

    @org.junit.jupiter.api.Test
    void getPriceForFourBooksOnTheSeries() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("HP1"), new Book("HP2"), new Book("HP3"), new Book("HP4"));
        assertEquals(8.0 * 4 * 0.80, current);
    }

    @org.junit.jupiter.api.Test
    void getPriceForFiveBooksOnTheSeries() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("HP1"), new Book("HP2"), new Book("HP3"), new Book("HP4"), new Book("HP5"));
        assertEquals(8.0 * 5 * 0.75, current);
    }

    @org.junit.jupiter.api.Test
    void getPriceForOneBookTwoCopies() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("HP1"), new Book("HP1"));
        assertEquals(8.0 * 2, current);
    }

    @org.junit.jupiter.api.Test
    void getPriceForThreeBooksOneCopy() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("HP1"), new Book("HP1"), new Book("HP2"));
        assertEquals(8.0 * 3 * 0.95, current);
    }
}