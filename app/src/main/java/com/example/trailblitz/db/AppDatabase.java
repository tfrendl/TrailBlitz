package com.example.trailblitz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trailblitz.TrailBlitz;
import com.example.trailblitz.User;

@Database(entities = {TrailBlitz.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "TRAILBLITZ_DATABASE";
    public static final String TRAILBLITZ_TABLE = "TRAILBLITZ_TABLE";

    public static final String USER_TABLE = "USER_TABLE";

    public abstract TrailBlitzDAO getTrailBlitzDAO();
}
