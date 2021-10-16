package domain;


public class Basket {

    public double getPrice(Book... books) {
        final var discounts = new DiscountRule[]{
                new DiscountRule(lot -> lot.size() == 2, 0.05),
                new DiscountRule(lot -> lot.size() == 3, 0.1),
                new DiscountRule(lot -> lot.size() == 4, 0.20),
                new DiscountRule(lot -> lot.size() == 5, 0.25)
        };
        final LotCollection lotCollection = new LotCollection(discounts);
        lotCollection.addAll(books);
        return lotCollection
                .getTotalPrice();
    }

}
