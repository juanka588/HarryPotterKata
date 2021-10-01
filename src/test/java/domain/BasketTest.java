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

    @org.junit.jupiter.api.Test
    void givenTwoDifferentBooksInBasketThenPriceIsDiscounted() {
        // Arrange
        Basket sut = new Basket();
        // Act
        Book hp1 = new Book("hp1");
        Book hp2 = new Book("hp2");
        double totalPrice = sut.getPrice(hp1, hp2);
        // Assert
        assertEquals(2 * 8.0 * 0.95, totalPrice);
    }

    @org.junit.jupiter.api.Test
    void givenTwoDifferentBooksOneCopyInBasketThenPriceIsDiscounted() {
        // Arrange
        Basket sut = new Basket();
        // Act
        Book hp1 = new Book("hp1");
        Book hp1Copy = new Book("hp1");
        Book hp2 = new Book("hp2");
        double totalPrice = sut.getPrice(hp1, hp1Copy, hp2);
        // Assert
        assertEquals(2 * 8.0 * 0.95 + 8.0, totalPrice);
    }

    @org.junit.jupiter.api.Test
    void givenMultipleGroupsOfBooksInBasketThenPriceIsDiscounted() {
        // Arrange
        Basket sut = new Basket();
        // Act
        Book hp1 = new Book("hp1");
        Book hp1Copy = new Book("hp1");
        Book hp2 = new Book("hp2");
        Book hp2Copy = new Book("hp2");
        double totalPrice = sut.getPrice(hp1, hp1Copy, hp2, hp2Copy);
        // Assert
        assertEquals(4 * 8.0 * 0.95, totalPrice);
    }

    @org.junit.jupiter.api.Test
    void givenThreeDifferentBooksInBasketThenPriceIsDiscounted() {
        // Arrange
        Basket sut = new Basket();
        // Act
        Book hp1 = new Book("hp1");
        Book hp2 = new Book("hp2");
        Book hp3 = new Book("hp3");
        double totalPrice = sut.getPrice(hp1, hp2, hp3);
        // Assert
        assertEquals(3 * 8.0 * 0.90, totalPrice);
    }

    @org.junit.jupiter.api.Test
    void givenFourDifferentBooksInBasketThenPriceIsDiscounted() {
        // Arrange
        Basket sut = new Basket();
        // Act
        Book hp1 = new Book("hp1");
        Book hp2 = new Book("hp2");
        Book hp3 = new Book("hp3");
        Book hp4 = new Book("hp4");
        double totalPrice = sut.getPrice(hp1, hp2, hp3, hp4);
        // Assert
        assertEquals(4 * 8.0 * 0.80, totalPrice);
    }

    @org.junit.jupiter.api.Test
    void givenFiveDifferentBooksInBasketThenPriceIsDiscounted() {
        // Arrange
        Basket sut = new Basket();
        // Act
        Book hp1 = new Book("hp1");
        Book hp2 = new Book("hp2");
        Book hp3 = new Book("hp3");
        Book hp4 = new Book("hp4");
        Book hp5 = new Book("hp5");
        double totalPrice = sut.getPrice(hp1, hp2, hp3, hp4, hp5);
        // Assert
        assertEquals(5 * 8.0 * 0.75, totalPrice);
    }
}
