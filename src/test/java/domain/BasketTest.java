package domain;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @org.junit.jupiter.api.Test
    void getPrice_GivenOneBook() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"));
        assertEquals(8.0, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_GivenTwiceTheSameBook() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"), new Book("hp1"));
        assertEquals(16.0, current);
    }
}