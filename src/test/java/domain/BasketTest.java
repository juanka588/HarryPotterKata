package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @Test
    void getPrice_givenOneBookOfTheSeries_itReturnsUnitPrice() {
        Basket sut = new Basket();
        double current = sut.getPrice();
        assertEquals(8.0, current);
    }

    /**
     * Demo test for parameterized test
     */
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    })
    void add(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }

    private static class Calculator {
        public int add(int first, int second) {
            return first + second;
        }
    }

}