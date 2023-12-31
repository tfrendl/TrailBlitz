package com.example.trailblitz.db;

 import androidx.room.Dao;
 import androidx.room.Delete;
 import androidx.room.Insert;
 import androidx.room.Query;
 import androidx.room.Update;

 import com.example.trailblitz.Purchase;
 import com.example.trailblitz.TrailBlitz;
 import com.example.trailblitz.User;
 import com.example.trailblitz.db.AppDatabase;

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

    @Query("SELECT * FROM " + AppDatabase.TRAILBLITZ_TABLE)
    List<TrailBlitz> getAllItems();

    @Query("SELECT mItem FROM " + AppDatabase.TRAILBLITZ_TABLE)
    String[] loadItemsNames();

    @Query("SELECT mPrice FROM " + AppDatabase.TRAILBLITZ_TABLE)
    double[] loadItemPrices();

    @Query("SELECT mQuantity FROM " + AppDatabase.TRAILBLITZ_TABLE)
    int[] loadItemQuantity();

    @Query("SELECT * FROM " + AppDatabase.TRAILBLITZ_TABLE + " WHERE mItem = :item")
    TrailBlitz getTrailBlitzByItem(String item);
    @Query("SELECT mPrice FROM " + AppDatabase.TRAILBLITZ_TABLE + " WHERE mItem = :item")
    Double getPriceByItem(String item);

    @Query("SELECT mLogId FROM " + AppDatabase.TRAILBLITZ_TABLE + " WHERE mItem = :item")
    int getLogIdByItem(String item);

    @Query("SELECT mQuantity FROM " + AppDatabase.TRAILBLITZ_TABLE + " WHERE mItem = :item")
    int getQuantityByItem(String item);

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

    @Query ("SELECT mUserName FROM " + AppDatabase.USER_TABLE)
    List<String> loadUsernames();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserByUserId(int userId);

    @Query("SELECT mIsAdmin FROM " + AppDatabase.USER_TABLE + " WHERE mUserId = :userId")
    boolean checkIfAdmin(int userId);

    /**
     * Purchases Table
     */
    @Insert
    void insert(Purchase... Purchases);

    @Update
    void update(Purchase... Purchases);

    @Delete
    void delete(Purchase... Purchase);

    @Query("SELECT mQuantity FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId = :userId AND mItemName = :itemName and mHasPurchased = :hasPurchased")
    int getQuantityByUserId(int userId, String itemName, boolean hasPurchased);
    @Query("SELECT mQuantity FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId = :userId AND mHasPurchased = :hasPurchased")
    int[] getallQuantitites(int userId, boolean hasPurchased);

    @Query("SELECT * FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId = :userId AND mItemName = :item AND mHasPurchased = :hasPurchased")
    Purchase getIfInCartByItem(int userId, String item, boolean hasPurchased);

    @Query ("SELECT * FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId = :userId AND mItemName = :itemName")
    Purchase getItemInfoFromUserIdAndItemName(int userId, String itemName);

    @Query ("SELECT mItemName FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId = :userId AND mHasPurchased = :hasPurchased")
    String[] getItemsInCart(int userId, Boolean hasPurchased);

    @Query("SELECT * FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId = :userId AND mItemName = :itemName and mHasPurchased = :hasPurchased")
    Purchase getPurchase(int userId, String itemName, boolean hasPurchased);

    @Query ("SELECT * FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId = :userId AND mHasPurchased = :hasPurchased")
    Purchase[] getAllBeingPurchased(int userId, Boolean hasPurchased);

    @Query("SELECT mTransaction FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId =:userId AND mHasPurchased =:hasPurchased")
    int[] getTransactionNumbers(int userId, Boolean hasPurchased);

    @Query("SELECT mItemPrice FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserId =:userId AND mHasPurchased =:hasPurchased")
    double[] getAllCharges(int userId, Boolean hasPurchased);

    @Query("SELECT mTransaction FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mHasPurchased =:hasPurchased")
    int[] adminGetTransactionNumbers(Boolean hasPurchased);

    @Query("SELECT mItemPrice FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mHasPurchased =:hasPurchased")
    double[] adminGetAllCharges(Boolean hasPurchased);

    @Query ("SELECT mItemName FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mHasPurchased = :hasPurchased")
    String[] adminGetAllBeingPurchased(Boolean hasPurchased);

    @Query ("SELECT mQuantity FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mHasPurchased = :hasPurchased")
    int[] adminGetAllQuantities(Boolean hasPurchased);








}
