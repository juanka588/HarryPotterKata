package domain;

import static org.junit.jupiter.api.Assertions.*;

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


}