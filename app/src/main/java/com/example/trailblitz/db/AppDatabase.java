package com.example.trailblitz.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.trailblitz.Purchase;
import com.example.trailblitz.TrailBlitz;
import com.example.trailblitz.User;

/**
 * @author Talia Frendl
 * @since  December 4, 2023
 * Assignment: Project 02: Part 02 Login and Landing Page
 * Used ChatGPT to help write javadoc comments.
 * Room Database class for the TrailBlitz application.
 * Manages the database creation and provides access to the DAOs.
 *
 * @version 2 The version of the database schema.
 */
@Database(entities = {TrailBlitz.class, User.class, Purchase.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    // The name of the database
    public static final String DB_NAME = "TRAILBLITZ_DATABASE";


    // Three tables in database
    public static final String TRAILBLITZ_TABLE = "TRAILBLITZ_TABLE";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String PURCHASE_TABLE = "PURCHASE_TABLE";

    /**
     * Retrieves the Data Access Object (DAO) for TrailBlitz entities.
     *
     * @return The TrailBlitzDAO instance for interacting with TrailBlitz entities in the database.
     */
    public abstract TrailBlitzDAO getTrailBlitzDAO();

}
