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
     * Inserts one or more TrailBlitz entities into the database.
     *
     * @param trailBlitz One or more TrailBlitz entities to be inserted.
     */
    @Insert
    void insert(TrailBlitz... trailBlitz);

    /**
     * Updates one or more existing TrailBlitz entities in the database.
     *
     * @param trailBlitz One or more TrailBlitz entities to be updated.
     */
    @Update
    void update(TrailBlitz... trailBlitz);

    /**
     * Deletes a specific TrailBlitz entity from the database.
     *
     * @param trailBlitz The TrailBlitz entity to be deleted.
     */
    @Delete
    void delete(TrailBlitz trailBlitz);



    /**
     * Inserts one or more User entities into the database.
     *
     * @param users One or more User entities to be inserted.
     */
    @Insert
    void insert(User...users);

    /**
     * Updates one or more existing User entities in the database.
     *
     * @param users One or more User entities to be updated.
     */
    @Update
    void update(User...users);

    /**
     * Deletes a specific User entity from the database.
     *
     * @param user The User entity to be deleted.
     */
    @Delete
    void delete(User user);

    /**
     * Retrieves a list of all User entities from the database.
     *
     * @return A List containing all User entities in the database.
     */
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getAllUsers();

    /**
     * Retrieves a User entity from the database based on the provided username.
     *
     * @param username The username to search for.
     * @return The User entity with the specified username.
     */
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserName = :username")
    User getUserByUsername(String username);

    /**
     * Retrieves a User entity from the database based on the provided user ID.
     *
     * @param userId The user ID to search for.
     * @return The User entity with the specified user ID.
     */
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserByUserId(int userId);
}
