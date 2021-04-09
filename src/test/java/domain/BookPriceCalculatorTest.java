package domain;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @org.junit.jupiter.api.Test
    void getPrice_givenOneBookOfOneSeries_itReturnsUnitPrice() {
        BookPriceCalculator sut = new BookPriceCalculator();

        double current = sut.getPrice(getHp1());

        assertEquals(8.0, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_givenManyBooksOfOneSeries_itReturnsManyTimesUnitPrice() {
        BookPriceCalculator sut = new BookPriceCalculator();

        double current = sut.getPrice(getHp1(), getHp1());

        assertEquals(8.0 * 2, current);
    }

    @org.junit.jupiter.api.Test
    void getPrice_givenTwoDifferentBooksInTheSeries_itReturnsReduction() {
        BookPriceCalculator sut = new BookPriceCalculator();

        double current = sut.getPrice(getHp1(), getHp2());

        assertEquals(8.0 * 2 * 0.95, current);

        current = sut.getPrice(getHp1(), getHp1(), getHp2());

        assertEquals((8.0 * 2 * 0.95) + 8.0, current);

        current = sut.getPrice(getHp1(), getHp2(), getHp2());

        assertEquals((8.0 * 2 * 0.95) + 8.0, current);

        current = sut.getPrice(getHp1(), getHp1(), getHp2(), getHp2());

        assertEquals(2 * (8.0 * 2 * 0.95), current);
    }


    private Book getHp1() {
        return new Book("hp1", 1);
    }

    private Book getHp2() {
        return new Book("hp2", 2);
    }
}
