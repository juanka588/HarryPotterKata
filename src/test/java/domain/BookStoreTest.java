package domain;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {
    @org.junit.jupiter.api.Test
    void testGetPrice_givenNoBook_ItReturnsZero() {
        assertEquals(0.0, new BookStore().getPrice());
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_givenOnlyOneBookOfTheSeries_ItReturnsMultiplesOfStandardBookPrice() {
        assertEquals(8.0, new BookStore().getPrice(new Book.ChamberOfSecrets()));
        assertEquals(2 * 8.0, new BookStore().getPrice(new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets()));
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_givenTwoDifferentBooksInTheSeries_ItReturnsPriceWithReduction() {
        assertEquals((2 * 8.0) * 0.95, new BookStore().getPrice(new Book.ChamberOfSecrets(), new Book.GobletOfFire()));
        assertEquals((2 * 8.0) * 0.95, new BookStore().getPrice(new Book.ChamberOfSecrets(), new Book.PhilosophalStone()));
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_givenThreeDifferentBooksInTheSeries_ItReturnsPriceWithReduction() {
        assertEquals((3 * 8.0) * 0.9, new BookStore().getPrice(new Book.ChamberOfSecrets(), new Book.GobletOfFire(), new Book.PhilosophalStone()));
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_givenFourDifferentBooksInTheSeries_ItReturnsPriceWithReduction() {
        assertEquals((4 * 8.0) * 0.8, new BookStore().getPrice(new Book.ChamberOfSecrets(), new Book.GobletOfFire(), new Book.PhilosophalStone(), new Book.PrisonerAzkaban()));
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_givenFiveDifferentBooksInTheSeries_ItReturnsPriceWithReduction() {
        assertEquals((5 * 8.0) * 0.75, new BookStore().getPrice(new Book.ChamberOfSecrets(), new Book.GobletOfFire(), new Book.PhilosophalStone(), new Book.PrisonerAzkaban(), new Book.OrderOfPhoenix()));
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_givenSeveralDiscounts_ItReturnsAllDiscounts() {
        assertEquals(2 * 8.0 + (2 * 8.0) * 0.95, new BookStore().getPrice(new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets(), new Book.PrisonerAzkaban()));
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_GivenEdgeCase_ItReturnsOptimalPrice() {
        assertEquals(2 * (8 * 4 * 0.8),
                new BookStore().getPrice(
                        new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets(),
                        new Book.PrisonerAzkaban(), new Book.PrisonerAzkaban(),
                        new Book.PhilosophalStone(), new Book.PhilosophalStone(),
                        new Book.GobletOfFire(),
                        new Book.OrderOfPhoenix())
        );

        assertEquals(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8),
                new BookStore().getPrice(
                        new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets(), new Book.ChamberOfSecrets(),
                        new Book.PrisonerAzkaban(), new Book.PrisonerAzkaban(), new Book.PrisonerAzkaban(), new Book.PrisonerAzkaban(), new Book.PrisonerAzkaban(),
                        new Book.PhilosophalStone(), new Book.PhilosophalStone(), new Book.PhilosophalStone(), new Book.PhilosophalStone(),
                        new Book.GobletOfFire(), new Book.GobletOfFire(), new Book.GobletOfFire(), new Book.GobletOfFire(), new Book.GobletOfFire(),
                        new Book.OrderOfPhoenix(), new Book.OrderOfPhoenix(), new Book.OrderOfPhoenix(), new Book.OrderOfPhoenix())
        );
    }

    @org.junit.jupiter.api.Test
    void testGetPrice_GivenNewBookOutOfReduction_ItReturnsCorrectPrice() {
        assertEquals((8 * 5) * 0.75 + 8,
                new BookStore().getPrice(
                        new Book.ChamberOfSecrets(),
                        new Book.PrisonerAzkaban(),
                        new Book.PhilosophalStone(),
                        new Book.GobletOfFire(),
                        new Book.OrderOfPhoenix(),
                        new FakeBook())
        );
    }

    private static class FakeBook implements Book{

        @Override
        public String getTitle() {
            return "Fake Book";
        }

        @Override
        public int getId() {
            return 6;
        }
    }
}
