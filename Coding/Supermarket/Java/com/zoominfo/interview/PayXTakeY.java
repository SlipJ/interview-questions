package com.zoominfo.interview;

public class PayXTakeY implements Discount{

    public final int baseAmount;
    public final int bonusAmount;

    public PayXTakeY(int baseAmount, int bonusAmount) {
        this.baseAmount = baseAmount;
        this.bonusAmount = bonusAmount;
    }

    @Override
    public int calcDiscounted(int totalAmount, int productPrice) {
        int bundles = totalAmount / (baseAmount + bonusAmount);
        int rem = totalAmount % (baseAmount + bonusAmount);
        if (rem > baseAmount) {
            bundles += 1;
            rem = 0;
        }
        System.out.printf("Amount: %s, Price: %s, Discount: buy %s take %s%n", totalAmount, productPrice, baseAmount, bonusAmount);
        return productPrice * (bundles * baseAmount + rem);
    }
}
