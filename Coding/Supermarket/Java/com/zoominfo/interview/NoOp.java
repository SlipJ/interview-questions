package com.zoominfo.interview;

public class NoOp implements Discount{

    @Override
    public int calcDiscounted(int totalAmount, int productPrice) {
        System.out.printf("Amount: %s, Price: %s, No discount%n", totalAmount, productPrice);
        return totalAmount * productPrice;
    }
}
