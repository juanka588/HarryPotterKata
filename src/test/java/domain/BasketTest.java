package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @org.junit.jupiter.api.Test
    void givenEmptyBasketThenPriceMustBeZero() {
        // Arrange
        Basket sut = new Basket();
        // Act
        double totalPrice = sut.getPrice();
        // Assert
        assertEquals(0.0, totalPrice);
    }

    @org.junit.jupiter.api.Test
    void givenOneBookInBasketThenPriceIsShelfPrice() {
        // Arrange
        Basket sut = new Basket();
        // Act
        double totalPrice = sut.getPrice(new Book("hp1"));
        // Assert
        assertEquals(8.0, totalPrice);
    }

    @org.junit.jupiter.api.Test
    void givenTwoIdenticalBooksInBasketThenPriceIsShelfPrice() {
        // Arrange
        Basket sut = new Basket();
        // Act
        Book hp1 = new Book("hp1");
        Book hp1Copy = new Book("hp1");
        double totalPrice = sut.getPrice(hp1, hp1Copy);
        // Assert
        assertEquals(2 * 8.0, totalPrice);
    }
}