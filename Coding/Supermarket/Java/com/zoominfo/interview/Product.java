package com.zoominfo.interview;

public class Product {
    public final String name;
    public final int price;
    public final Discount discount;

    public Product(String name, int price, Discount discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
}
