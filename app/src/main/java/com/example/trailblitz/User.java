package com.example.trailblitz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.trailblitz.db.AppDatabase;

/**
 * @author Talia Frendl
 * @since  November 30, 2023
 * Assignment: Project 02: Part 02 Login and Landing Page
 * Used ChatGPT to help write javadoc comments.
 * Entity class representing a User in the TrailBlitz application.
 * Each User instance is associated with a unique user ID, which is automatically generated.
 */

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    /**
     * Unique identifier (primary key) for the User, automatically generated.
     */
    @PrimaryKey(autoGenerate = true)    // uniquely identifies data in database
    private int mUserId;

    private String mUserName;
    private String mPassword;
    private boolean mIsAdmin;

    /**
     * Constructor for creating a User instance with the specified username, password, and admin status.
     *
     * @param mUserName The username of the User.
     * @param mPassword The password associated with the User's account.
     * @param mIsAdmin  Indicates whether the User has administrative privileges.
     */
    public User(String mUserName, String mPassword, boolean mIsAdmin) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mIsAdmin = mIsAdmin;
    }

    /**
     * Gets the user ID of the User.
     *
     * @return The user ID of the User.
     */
    public int getUserId(){
        return mUserId;
    }

    /**
     * Sets the user ID of the User.
     *
     * @param userId The user ID to be set for the User.
     */
    public void setUserId(int userId){
        this.mUserId = userId;
    }

    /**
     * Gets the username of the User.
     *
     * @return The username of the User.
     */
    public String getUserName() {
        return mUserName;
    }

    /**
     * Sets the username of the User.
     *
     * @param userName The username to be set for the User.
     */
    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    /**
     * Gets the password associated with the User's account.
     *
     * @return The password of the User.
     */
    public String getPassword() {
        return mPassword;
    }

    /**
     * Sets the password associated with the User's account.
     *
     * @param password The password to be set for the User.
     */
    public void setPassword(String password) {
        this.mPassword = password;
    }

    /**
     * Checks if the User has administrative privileges.
     *
     * @return True if the User is an admin, false otherwise.
     */
    public boolean getIsAdmin() {
        return mIsAdmin;
    }

    /**
     * Sets the administrative privileges of the User.
     *
     * @param isAdmin True if the User should have admin privileges, false otherwise.
     */
    public void setIsAdmin(boolean isAdmin) {
        this.mIsAdmin = isAdmin;
    }
}
