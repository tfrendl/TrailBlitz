package com.example.trailblitz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trailblitz.TrailBlitz;

@Database(entities = {TrailBlitz.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "com.example.trailblitz.TRAILBLITZ_DATABASE";
    public static final String TRAILBLITZ_TABLE = "com.example.trailblitz.TRAILBLITZ_TABLE";

    public static final String USER_TABLE = "USER_TABLE";

    public abstract TrailBlitzDAO getTrailBlitzDAO();
}
