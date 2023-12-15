package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.db.TrailBlitzDAO;
import com.example.trailblitz.db.AppDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.trailblitz.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.trailblitz.PREFERENCES_KEY";
    private TextView mInventoryText;
    private RecyclerView mRecyclerViewItems;
    private EditText mItemPrompt;
    private EditText mQuantityPrompt;
    private Button mButtonAddToCart;
    private Button mButtonBack;
    private TrailBlitzDAO mTrailBlitzDAO;
    private List<TrailBlitz> mItems;
    ArrayList<StoreModel> storeModel = new ArrayList<>();
    private Button mButtonUpdate;
    private EditText mPriceEntry;
    boolean editMode = false;
    private TrailBlitz mTrailBlitz;
    private Purchase mPurchase;
    private int mUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        // check to see if in edit mode
        Intent receivedIntent = getIntent();
        editMode = receivedIntent.getBooleanExtra("editMode", false);
        getDatabase();
        wireUpDisplay();
        setUserId();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewInventory);
        setUpStoreModels();
        Store_RecyclerViewAdapter adapter = new Store_RecyclerViewAdapter(this, storeModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserId = sharedPreferences.getInt(USER_ID_KEY, -1);
    }

    private void getDatabase() {
        mTrailBlitzDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()   // generally do not want to do on main thread
                .build()
                .getTrailBlitzDAO();
    }

    private void wireUpDisplay() {
        mInventoryText = findViewById(R.id.textViewInventory);
        mPriceEntry = findViewById(R.id.editTextChangePrice);
        mButtonUpdate = findViewById(R.id.buttonUpdate);
        mButtonAddToCart = findViewById(R.id.buttonAddToCart);

        if(editMode) {
            mPriceEntry.setVisibility(View.VISIBLE);
            mButtonUpdate.setVisibility(View.VISIBLE);
            mButtonAddToCart.setVisibility(View.INVISIBLE);
        } else {
            mPriceEntry.setVisibility(View.INVISIBLE);
            mButtonUpdate.setVisibility(View.INVISIBLE);
            mButtonAddToCart.setVisibility(View.VISIBLE);
        }

        mItemPrompt = findViewById(R.id.editTextItemName);
        mQuantityPrompt = findViewById(R.id.editTextQuantity);
        mButtonBack = findViewById(R.id.buttonBackInventory);

        mButtonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = mItemPrompt.getText().toString();
                if (checkIfInDatabase(itemName)) {
                    // if in database, check if there are enough in stock
                    int inStock = mTrailBlitzDAO.getQuantityByItem(itemName);
                    int requestQuan = getInputQuantity();
                    if (requestQuan <= inStock) {
                        // check how many are in cart
                        if (checkIfInCart(itemName)) {
                            int inCart = mTrailBlitzDAO.getQuantityByUserId(mUserId, itemName, false);
                            if (inCart + requestQuan <= inStock) {
                                mPurchase = mTrailBlitzDAO.getIfInCartByItem(mUserId, itemName, false);
                                mPurchase.setQuantity(mPurchase.getQuantity() + requestQuan);
                                mTrailBlitzDAO.update(mPurchase);
                                String toast = (Integer.toString(mPurchase.getQuantity()) + " in your cart!");
                                makeToast(toast);
                                 toast = "Added to Cart!";
                                makeToast(toast);
                            } else {
                                String toast = "No more in stock!";
                                makeToast(toast);
                            }
                        } else {
                            double price = mTrailBlitzDAO.getPriceByItem(itemName);
                            mPurchase = new Purchase(mUserId, itemName, price, requestQuan, false);
                            mTrailBlitzDAO.insert(mPurchase);
                            String toast = "Added to Cart";
                            makeToast(toast);
                        }
                    } else {
                        String toast = "No more in stock!";
                        makeToast(toast);
                    }
                }
            }
        });


        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent); // launch that activity
            }
        });

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMode) {
                    TrailBlitz item = getInputValues();
                    if (item != null) {
                        mTrailBlitzDAO.update(item);
                    }
                }
            }
        });
    }


    private void makeToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    private int getInputQuantity() {
        int quantity = 0;
        String quantityString = mQuantityPrompt.getText().toString();
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            Log.d("TRAILBLITZ", "couldn't convert quantity");
            return 0;
        }
        return quantity;
    }


    private TrailBlitz getInputValues() {
        TrailBlitz trailBlitzItem;
        String itemName = "no item found";
        double price = 0;
        int quantity = 0;
        boolean updatePrice = false;
        boolean updateQuantity = false;

        itemName = mItemPrompt.getText().toString();
        String priceString = mPriceEntry.getText().toString();

        // check for if the price is being changed
        if (!priceString.isEmpty()) {
            try {
                price = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                Log.d("TRAILBLITZ", "couldn't convert price");
            }
            updatePrice = true;
        }

        String quantityString = mQuantityPrompt.getText().toString();

        // check if the quantity is being changed
        if (!quantityString.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException e) {
                Log.d("TRAILBLITZ", "couldn't convert quantity");
            }
            updateQuantity = true;
        }

        if (updatePrice || updateQuantity) {
            if(checkIfInDatabase(itemName)) {
                Toast.makeText(this, "found " + itemName + " in the store!", Toast.LENGTH_SHORT).show();
                int logId = mTrailBlitzDAO.getLogIdByItem(itemName);
                if (updatePrice && !updateQuantity) {
                    // update price, get quantity
                    quantity = mTrailBlitzDAO.getQuantityByItem(itemName);
                    Toast.makeText(this, "updating price", Toast.LENGTH_SHORT).show();
                } else if (updateQuantity && !updatePrice) {
                    // update quantity, get price
                    price = mTrailBlitzDAO.getPriceByItem(itemName);
                    Toast.makeText(this, "updating quantity", Toast.LENGTH_SHORT).show();
                } else {
                        Toast.makeText(this, "updating price and quantity", Toast.LENGTH_SHORT).show();
                }

                    TrailBlitz updatedTrailBlitz = new TrailBlitz(itemName, price, quantity);
                    updatedTrailBlitz.setLogId(logId);
                    return updatedTrailBlitz;
            }
        } else {
            Toast.makeText(this, "enter a price or quantity", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    private boolean checkIfInDatabase(String itemName) {
        mTrailBlitz = mTrailBlitzDAO.getTrailBlitzByItem(itemName);
        if (mTrailBlitz != null) {
            return true;
        }

        Toast.makeText(this, itemName + " not in stock", Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean checkIfInCart(String itemName) {
        mPurchase = mTrailBlitzDAO.getIfInCartByItem(mUserId, itemName, false);
        if (mPurchase != null) {
            return true;
        }

        Toast.makeText(this, "Added to cart!", Toast.LENGTH_SHORT).show();
        return false;
    }


    private void setUpStoreModels() {
        String[] itemNames = mTrailBlitzDAO.loadItemsNames();
        double[] itemPrices = mTrailBlitzDAO.loadItemPrices();
        int[] itemQuantities = mTrailBlitzDAO.loadItemQuantity();

        for (int i = 0; i < itemNames.length; i++) {
            storeModel.add(new StoreModel(itemNames[i],
                    itemPrices[i],
                    itemQuantities[i]));
        }
    }
}