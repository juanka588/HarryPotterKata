package domain;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {

    @org.junit.jupiter.api.Test
    void getPrice() {
        assertEquals(2.0,new BookStore().getPrice(new Book()));
    }
}