package com.example.trailblitz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trailblitz.db.AppDatabase;

@Entity(tableName = AppDatabase.TRAILBLITZ_TABLE)
public class TrailBlitz {
    @PrimaryKey(autoGenerate = true)
    private int mLogId;


    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }
}
