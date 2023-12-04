package com.example.trailblitz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trailblitz.db.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)    // uniquely identifies data in database
    private int mUserId;

    private String mUserName;
    private String mPassword;
    private String mIsAdmin;

    public User(String mUserName, String mPassword, String mIsAdmin) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mIsAdmin = mIsAdmin;
    }

    public int getUserId(){
        return mUserId;
    }

    public void setUserId(int userId){
        this.mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.mIsAdmin = isAdmin;
    }
}
