package com.example.trailblitz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

/**
 * @author Talia Frendl
 * @since  December 4, 2023
 * Assignment: Project 02: Part 02 Login and Landing Page
 * Used ChatGPT to help write javadoc comments.
 * Entity class representing a item in the TrailBlitz application.
 */

@Entity(tableName = AppDatabase.TRAILBLITZ_TABLE)
public class TrailBlitz {
    /**
     * Unique identifier (primary key) for the TrailBlitz log entry, automatically generated.
     */
    @PrimaryKey(autoGenerate = true)
    private int mLogId;

    @ColumnInfo(name = "mItem")
    private String mItem;
    @ColumnInfo(name = "mPrice")
    private double mPrice;
    @ColumnInfo(name = "mQuantity")
    private int mQuantity;

    public TrailBlitz(String mItem, double mPrice, int mQuantity) {
        this.mItem = mItem;
        this.mPrice = mPrice;
        this.mQuantity = mQuantity;
    }


    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        this.mLogId = logId;
    }

    public String getItem() {
        return mItem;
    }

    public void setItem(String item) {
        this.mItem = item;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        this.mPrice = price;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }
}
