package com.example.trailblitz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.trailblitz.db.AppDatabase;

/**
 * @author Talia Frendl
 * @since  December 4, 2023
 * Assignment: Project 02: Part 02 Login and Landing Page
 * Used ChatGPT to help write javadoc comments.
 * Entity class representing a TrailBlitz in the TrailBlitz application.
 * Each TrailBlitz instance is associated with a unique log ID, which is automatically generated.
 */

@Entity(tableName = AppDatabase.TRAILBLITZ_TABLE)
public class TrailBlitz {
    /**
     * Unique identifier (primary key) for the TrailBlitz log entry, automatically generated.
     */
    @PrimaryKey(autoGenerate = true)
    private int mLogId;

    /**
     * Gets the log ID of the TrailBlitz.
     *
     * @return The log ID of the TrailBlitz.
     */
    public int getLogId() {
        return mLogId;
    }

    /**
     * Sets the log ID of the TrailBlitz.
     *
     * @param logId The log ID to be set for the TrailBlitz.
     */
    public void setLogId(int logId) {
        mLogId = logId;
    }
}
