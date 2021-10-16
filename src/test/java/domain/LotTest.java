package domain;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class LotTest {

    @Test
    void getPrice_returns0_WhenEmpty() {
        final Lot sut = new Lot();
        assertEquals(0, sut.getPrice());
    }

    @Test
    void getPrice_returnsTheSumOfBookPrices_WhenNoDiscountRules() {
        final Lot sut = new Lot();
        sut.add(new Book("Book1", 10));
        assertEquals(10, sut.getPrice());
    }

    @Test
    void getPrice_returnsTheSumOfBookPrices_WhenNoDiscountRulesApplies() {
        final DiscountRule tenPercentDiscountIfHas5Items = new DiscountRule(lot -> lot.size() == 5, 0.1);
        final Lot sut = new Lot(tenPercentDiscountIfHas5Items);
        sut.add(new Book("Book1", 10));
        assertEquals(10, sut.getPrice());
    }

    @Test
    void getPrice_returnsTheSumOfBookPrices_AndAppliesDiscount() {
        final DiscountRule tenPercentDiscount = new DiscountRule(x -> true, 0.1);
        final Lot sut = new Lot(tenPercentDiscount);
        sut.add(new Book("Book1", 10));
        assertEquals(9, sut.getPrice());
    }


    @Test
    void getPrice_returnsTheSumOfBookPrices_AndAppliesTheFirstDiscountThatApplies() {
        final DiscountRule twentyFivePercentDiscountIfHas5Items = new DiscountRule(lot -> lot.size() == 5, 0.25);
        final DiscountRule fiftyPercentDiscount = new DiscountRule(x -> true, 0.5);
        final Lot sut = new Lot(fiftyPercentDiscount, twentyFivePercentDiscountIfHas5Items);
        sut.add(new Book("Book1", 10));
        sut.add(new Book("Book2", 10));
        sut.add(new Book("Book3", 10));
        sut.add(new Book("Book4", 10));
        sut.add(new Book("Book5", 10));
        assertEquals(25, sut.getPrice());
    }
}