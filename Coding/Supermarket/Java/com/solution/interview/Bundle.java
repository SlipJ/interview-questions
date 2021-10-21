package com.solution.interview;

public class Bundle implements Discount {

    public final int bundleSize;
    public final int bundlePrice;

    public Bundle(int bundleSize, int bundlePrice) {
        this.bundleSize = bundleSize;
        this.bundlePrice = bundlePrice;
    }

    @Override
    public int calcDiscounted(int totalAmount, int productPrice) {
        int bundles = totalAmount / bundleSize;
        int rem = totalAmount % bundleSize;
        System.out.printf("Amount: %s, Price: %s, Discount: bundle take %s pay %s%n", totalAmount, productPrice, bundleSize, bundlePrice);
        return bundles * bundlePrice + rem * productPrice;
    }
}
