package com.example.trailblitz.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDatabase.PURCHASE_TABLE)
public class Purchase {
    @PrimaryKey(autoGenerate = true)
    private int mPurchaseId;

    private String mPurchasedItem;
    private double mItemPrice;
    private int mQuantity;

    public Purchase(String mPurchasedItem, double mItemPrice, int mQuantity) {
        this.mPurchasedItem = mPurchasedItem;
        this.mItemPrice = mItemPrice;
        this.mQuantity = mQuantity;
    }

    public int getPurchaseId() {
        return mPurchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.mPurchaseId = purchaseId;
    }

    public String getPurchasedItem() {
        return mPurchasedItem;
    }

    public void setPurchasedItem(String purchasedItem) {
        this.mPurchasedItem = purchasedItem;
    }

    public double getItemPrice() {
        return mItemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.mItemPrice = itemPrice;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }
}
