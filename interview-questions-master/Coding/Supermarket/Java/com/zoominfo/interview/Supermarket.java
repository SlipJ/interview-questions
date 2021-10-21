package com.zoominfo.interview;

import java.util.HashMap;
import java.util.Map;

public class Supermarket {

    /**
     *
     * @param shoppingCart Products and their counts in shopping cart
     * @return Calculated sum
     */
    public static int calculateShoppingCartSum(Map<Product, Integer> shoppingCart) {
        return shoppingCart.entrySet().stream()
                .mapToInt(entry -> entry.getKey().discount.calcDiscounted(entry.getValue(), entry.getKey().price))
                .sum();
    }

    public static void main(String[] args) {
        Map<Product, Integer> shoppingCart = generateCart();
        int sum = calculateShoppingCartSum(shoppingCart);
        System.out.printf("Shopping cart sum: %s%n", sum);
    }

    private static Map<Product, Integer> generateCart() {
        Discount payXTakeY = new PayXTakeY(3, 2);
        Discount bundle = new Bundle(3, 50);
        Discount noOp = new NoOp();

        Product a = new Product("A", 10, payXTakeY);
        Product b = new Product("B", 20, bundle);
        Product c = new Product("C", 30, noOp);

        Map<Product, Integer> result = new HashMap<>();
        result.put(a, 4);
        result.put(b, 10);
        result.put(c,1);
        return result;
    }
}