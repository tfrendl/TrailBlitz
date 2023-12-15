package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

import org.w3c.dom.Text;

public class CartActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.trailblitz.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.trailblitz.PREFERENCES_KEY";
    private TextView mTextCart;
    private TextView mShowItems;
    private TextView mShowPrices;
    private TextView mShowQuantity;
    private TextView mTextItem;
    private TextView mTextQuantity;
    private TextView mTextPrice;
    private TextView mShowUserCart;
    private TextView mTotalText;
    private TextView mShowUserTotal;
    private Button mButtonDelete;
    private Button mButtonCheckout;
    private Button mButtonBack;
    private TrailBlitzDAO mTrailBlitzDAO;
    private EditText mItemPrompt;
    private int mUserId;
    private Purchase mPurchase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getDatabase();
        wireUpDisplay();
        setUserId();
        refreshDisplay();
    }

    private void getDatabase() {
        mTrailBlitzDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()   // generally do not want to do on main thread
                .build()
                .getTrailBlitzDAO();
    }

    private void refreshDisplay() {
        String[] items = mTrailBlitzDAO.getItemsInCart(mUserId, false);
        if(items.length <= 0) {
            mShowUserCart.setText("No items in cart");
        }
        int[] quantity = new int[items.length];
        double[] price = new double[items.length];
        for (int i = 0; i < items.length; i++) {
            quantity[i] = mTrailBlitzDAO.getQuantityByUserId(mUserId, items[i], false);
            price[i] = mTrailBlitzDAO.getPriceByItem(items[i]);
        }
        StringBuilder sbItems = new StringBuilder();
        StringBuilder sbPrices = new StringBuilder();
        StringBuilder sbQuantity = new StringBuilder();

        for (int i = 0; i < items.length; i++) {
            sbItems.append(items[i] + "\n");
            sbPrices.append("$" + price[i] + "\n");
            sbQuantity.append(quantity[i] + "\n");
        }
        mShowUserCart.setText(sbItems.toString());
        mShowQuantity.setText(sbQuantity.toString());
        mShowPrices.setText(sbPrices.toString());

        double totalCost = getUsersTotalBalance();
        mShowUserTotal.setText("$" + totalCost);
    }

    private double getUsersTotalBalance() {
        String[] itemsInCart = mTrailBlitzDAO.getItemsInCart(mUserId, false);
        int[] howManyOfEach = new int[itemsInCart.length];
        double[] itemPrice = new double[itemsInCart.length];
        double total = 0.0;

        for (int i = 0; i < itemsInCart.length; i++) {
            howManyOfEach[i] = mTrailBlitzDAO.getQuantityByUserId(mUserId, itemsInCart[0], false);
            itemPrice[i] = mTrailBlitzDAO.getPriceByItem(itemsInCart[0]);
            total += (howManyOfEach[i] * itemPrice[i]);
        }
        total = (double) Math.round(total * 100.0) / 100.0; // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
        return total;
    }

    private void setUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserId = sharedPreferences.getInt(USER_ID_KEY, -1);
    }

    private void wireUpDisplay() {
        mTextItem = findViewById(R.id.textView9Item);
        mTextQuantity = findViewById(R.id.textViewQuantity);
        mTextCart = findViewById(R.id.textViewCart);
        mShowUserCart = findViewById(R.id.textViewShowUsersCart);
        mShowPrices = findViewById(R.id.showUserPrice);
        mShowQuantity = findViewById(R.id.showUserQuantity);
        mTotalText = findViewById(R.id.textViewTotal);
        mShowUserTotal = findViewById(R.id.textViewTotalPrice);
        mButtonDelete = findViewById(R.id.buttonRemoveItem);
        mButtonCheckout = findViewById(R.id.buttonCheckout);
        mButtonBack = findViewById(R.id.buttonBack5);
        mItemPrompt = findViewById(R.id.editTextRemoveItem);
        mTextPrice = findViewById(R.id.textView11Price);

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = getItem();
                if(validItem(itemName)) {
                    removeOne(itemName);
                    refreshDisplay();
                } else {
                    String toast = "invalid request";
                    makeToast(toast);
                }
            }
        });

        mButtonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseItems();
            }
        });

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent); // launch that activity
            }
        });
    }

    private void purchaseItems() {
        Purchase[] purchases = mTrailBlitzDAO.getAllBeingPurchased(mUserId, false);
        String[] itemNames = mTrailBlitzDAO.getItemsInCart(mUserId, false); // from Purchases
        int quantity = 0;
        int requestedQuantity = 0;
        if (purchases.length <= 0) {
            makeToast("No Items in Cart");
            return;
        }

        // check Trailblitz inventory
        TrailBlitz[] itemsBeingPurchased = new TrailBlitz[purchases.length];

        for(int i = 0; i < itemsBeingPurchased.length; i++){
            itemsBeingPurchased[i] = mTrailBlitzDAO.getTrailBlitzByItem(itemNames[i]);
            // check quantity in TrailBlitz
            quantity = mTrailBlitzDAO.getQuantityByItem(itemNames[i]);
            requestedQuantity = mTrailBlitzDAO.getQuantityByUserId(mUserId, itemNames[i], false);
            if (requestedQuantity <= quantity) {
                purchases[i].setHasPurchased(true);
                mTrailBlitzDAO.update(purchases[i]);
                itemsBeingPurchased[i].setQuantity(quantity - requestedQuantity);
                mTrailBlitzDAO.update(itemsBeingPurchased[i]);
            } else {
                makeToast("Not enough of " + itemNames[i] + " in stock.");
            }
        }
        refreshDisplay();
    }

    private void makeToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    private void removeOne(String itemName) {
        mPurchase = mTrailBlitzDAO.getPurchase(mUserId, itemName, false);
        mPurchase.setQuantity(mPurchase.getQuantity() - 1);
        mTrailBlitzDAO.update(mPurchase);
        if (mTrailBlitzDAO.getQuantityByUserId(mUserId, itemName, false) <= 0){
            mTrailBlitzDAO.delete(mPurchase);
        }
    }

    private String getItem() {
        String itemName = "no item found";
        itemName = mItemPrompt.getText().toString();
        return itemName;
    }

    private boolean validItem(String itemName) {
        // check if item is in cart and enough
        if (mTrailBlitzDAO.getQuantityByUserId(mUserId, itemName, false) > 0) {
            return true;
        }

        Toast.makeText(this, itemName + " is not in cart", Toast.LENGTH_SHORT).show();
        return false;
    }
}