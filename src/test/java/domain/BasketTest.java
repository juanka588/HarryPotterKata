package domain;

import static org.junit.jupiter.api.Assertions.*;



class BasketTest {

    @org.junit.jupiter.api.Test
    void getPrice_GivenOneBook() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"));
        assertEquals(Basket.BASE_PRICE, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_GivenTwiceTheSameBook() {
        Basket sut = new Basket();
        double current = sut.getPrice(new Book("hp1"), new Book("hp1"));
        assertEquals(Basket.BASE_PRICE*2, current);
    }
}