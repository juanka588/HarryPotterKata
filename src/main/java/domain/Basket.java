package domain;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    public static final double SHELF_PRICE = 8.0d;
    public static final double TWO_DIFFERENT_BOOKS_DISCOUNT = 0.95;

    public double getPrice(Book... books) {
        List<Bucket> buckets = buildBuckets(books);
        double totalPrice = 0.0;
        for (Bucket bucket : buckets) {
            totalPrice += bucket.getPrice();
        }
        return totalPrice;
    }

    private List<Bucket> buildBuckets(Book... books) {
        List<Bucket> groups = new ArrayList<>();
        BucketBuilder builder = new BucketBuilder(books);
        while (builder.hasNextBucket()) {
            Bucket bucket = builder.createBucket();
            groups.add(bucket);
        }
        return groups;
    }
}
