package com.example.trailblitz;

/**
 * @author Talia Frendl
 * @since December 13, 2023
 *
 * Recycler View:
 * https://www.youtube.com/watch?v=Mc0XT58A1Z4
 */
public class StoreModel {
    String item;
    double price;
    int quantity;

    public StoreModel(String item, double price, int quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
