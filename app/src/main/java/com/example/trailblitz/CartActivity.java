package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.trailblitz.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.trailblitz.PREFERENCES_KEY";
    private TextView mViewCartText;
    private TextView mShowUserCart;
    private TextView mTotalText;
    private TextView mShowUserTotal;
    private Button mButtonUpdateCart;
    private Button mButtonCheckout;
    private Button mButtonBack;
    private TrailBlitzDAO mTrailBlitzDAO;
    private int mUserId;



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
        for (int i = 0; i < items.length; i++) {
            quantity[i] = mTrailBlitzDAO.getQuantityByUserId(mUserId, items[i], false);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i] + "      " + quantity[i] + "\n");
        }
        mShowUserCart.setText(sb.toString());
    }
    private void setUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserId = sharedPreferences.getInt(USER_ID_KEY, -1);
    }

    private void wireUpDisplay() {
        mViewCartText = findViewById(R.id.textViewCart);
        mShowUserCart = findViewById(R.id.textViewShowUsersCart);
        mTotalText = findViewById(R.id.textViewTotal);
        mShowUserTotal = findViewById(R.id.textViewTotalPrice);
        mButtonUpdateCart = findViewById(R.id.buttonUpdateCart);
        mButtonCheckout = findViewById(R.id.buttonCheckout);
        mButtonBack = findViewById(R.id.buttonBack5);

        mButtonUpdateCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement
            }
        });

        mButtonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent); // launch that activity
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
}