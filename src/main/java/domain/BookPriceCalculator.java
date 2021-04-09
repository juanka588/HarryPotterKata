package domain;

public class BookPriceCalculator {

    public static final double BOOK_UNIT_PRICE = 8.0;

    public double getPrice(Book... books) {
        int id1Count = 0;
        int id2Count = 0;

        for(Book book: books){
            if(book.getId()==1){
                id1Count++;
            }
            if(book.getId()==2){
                id2Count++;
            }
        }

        int ungroupedBooks = Math.abs(id1Count - id2Count);

        int groupedBooks = books.length - ungroupedBooks;

        return groupedBooks * 0.95 * BOOK_UNIT_PRICE + ungroupedBooks * BOOK_UNIT_PRICE;
    }

}
