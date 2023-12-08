package com.example.trailblitz.db;

 import androidx.room.Dao;
 import androidx.room.Delete;
 import androidx.room.Insert;
 import androidx.room.Query;
 import androidx.room.Update;
 import com.example.trailblitz.TrailBlitz;
 import com.example.trailblitz.User;
 import java.util.List;

/**
 * @author Talia Frendl
 * @since  December 4, 2023
 * Assignment: Project 02: Part 02 Login and Landing Page
 * Used ChatGPT to help write javadoc comments.
 * Data Access Object (DAO) interface for interacting with the TrailBlitz and User entities in the database.
 */

@Dao
public interface TrailBlitzDAO {

    /**
     * TrailBlitz Table
     */
    @Insert
    void insert(TrailBlitz... trailBlitz);

    @Update
    void update(TrailBlitz... trailBlitz);

    @Delete
    void delete(TrailBlitz trailBlitz);


    /**
     * User Table
     */

    @Insert
    void insert(User...users);

    @Update
    void update(User...users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserByUserId(int userId);

    /**
     * Purchases Table
     */
    @Insert
    void insert(Purchase... Purchases);

    @Update
    void update(Purchase... Purchases);

    @Delete
    void delete(Purchase... Purchase);

}
