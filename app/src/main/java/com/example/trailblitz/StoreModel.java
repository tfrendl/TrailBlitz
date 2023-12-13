package com.example.trailblitz;

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
