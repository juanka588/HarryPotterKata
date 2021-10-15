package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BasketTest {
    final static Book HARRY_POTTER_1 = new Book("Harry Potter and the Philosopher's Stone", 8.0);
    final static Book HARRY_POTTER_2 = new Book("Harry Potter and the Chamber of Secrets", 9.0);
    final static Book HARRY_POTTER_3 = new Book("Harry Potter and the Prisoner of Azkaban", 10.0);
    final static Book HARRY_POTTER_4 = new Book("Harry Potter and the Goblet of Fire", 11.0);
    final static Book HARRY_POTTER_5 = new Book("Harry Potter and the Order of the Phoenix", 12.0);

    @Test
    void getPrice_GivenOneBook_ItReturnsItsPrice() {
        final Basket sut = new Basket();
        assertEquals(HARRY_POTTER_1.getPrice(), sut.getPrice(HARRY_POTTER_1));
    }

    @Test
    void getPrice_GivenTwiceTheSameBook_ItSumsPrices() {
        final Basket sut = new Basket();
        assertEquals(HARRY_POTTER_1.getPrice() + HARRY_POTTER_1.getPrice(),
                sut.getPrice(HARRY_POTTER_1, HARRY_POTTER_1));
    }

    @Test
    void getPrice_GivenTwoDifferentBooks_ItSumsPricesAndApplies5PercentDiscount() {
        final Basket sut = new Basket();
        assertEquals((HARRY_POTTER_1.getPrice() + HARRY_POTTER_2.getPrice()) * .95,
                sut.getPrice(HARRY_POTTER_1, HARRY_POTTER_2));
    }

    @Test
    void getPrice_GivenThreeDifferentBooks_ItSumsPricesAndApplies10PercentDiscount() {
        final Basket sut = new Basket();
        assertEquals((HARRY_POTTER_1.getPrice() + HARRY_POTTER_2.getPrice() + HARRY_POTTER_3.getPrice()) * .9,
                sut.getPrice(HARRY_POTTER_1, HARRY_POTTER_2, HARRY_POTTER_3));
    }
}