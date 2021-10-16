package domain;

import java.util.function.Predicate;

public class DiscountRule {

    final Predicate<Lot> rule;
    final Double discount;

    public DiscountRule(Predicate<Lot> rule, Double discount) {
        this.rule = rule;
        this.discount = discount;
    }

    public boolean test(Lot lot) {
        return rule.test(lot);
    }

    public  double getDiscount() {
        return discount;
    }
}
