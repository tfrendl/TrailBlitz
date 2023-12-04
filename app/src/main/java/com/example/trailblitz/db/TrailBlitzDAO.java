package com.example.trailblitz.db;

 import androidx.room.Dao;
 import androidx.room.Delete;
 import androidx.room.Insert;
 import androidx.room.Query;
 import androidx.room.Update;
 import com.example.trailblitz.TrailBlitz;
 import com.example.trailblitz.User;
 import java.util.List;

@Dao
public interface TrailBlitzDAO {

    @Insert
    void insert(TrailBlitz... trailBlitz);

    @Update
    void update(TrailBlitz... trailBlitz);

    @Delete
    void delete(TrailBlitz trailBlitz);

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
}
