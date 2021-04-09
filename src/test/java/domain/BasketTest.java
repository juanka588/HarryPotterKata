package domain;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @org.junit.jupiter.api.Test
    void getPrice_givenOneBookOfTheSeries_itReturnsFullPrice() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"));
        assertEquals(8.0, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_givenBooksOfTheSameSeries_itReturnsTotalPrice() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"));
        assertEquals(8.0, current);
    }
}