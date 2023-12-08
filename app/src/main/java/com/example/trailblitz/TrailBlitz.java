package com.example.trailblitz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.trailblitz.db.AppDatabase;

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
    private String mItem;
    private int mQuantity;



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

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }
}
