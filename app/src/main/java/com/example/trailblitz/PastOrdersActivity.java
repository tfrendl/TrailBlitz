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

import org.w3c.dom.Text;

public class PastOrdersActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.trailblitz.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.trailblitz.PREFERENCES_KEY";
    private TextView mTextPastOrders;
    private TextView mTitleOrderNum;
    private TextView mTitleItem;
    private TextView mTitleQuantity;
    private TextView mTitleCost;
    private TextView mDisplayOrderNums;
    private TextView mDisplayItems;
    private TextView mDisplayQuantity;
    private TextView mDisplayCost;
    private Button mButtonBack;
    private TrailBlitzDAO mTrailBlitzDAO;
    private int mUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_orders);
        getDatabase();
        wireUpDisplay();
        setUserId();
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
        mTextPastOrders = findViewById(R.id.textViewAllOrders);
        mTitleOrderNum = findViewById(R.id.textViewOrderNum);
        mTitleItem = findViewById(R.id.textViewItemOrdered);
        mTitleQuantity = findViewById(R.id.textViewQuantity1);
        mTitleCost = findViewById(R.id.textViewPrice1);
        mDisplayOrderNums = findViewById(R.id.textViewDisplayOrder);
        mDisplayItems = findViewById(R.id.textViewDisplayItem);
        mDisplayQuantity = findViewById(R.id.textViewDisplayQuantity);
        mDisplayCost = findViewById(R.id.textViewDisplayCost);
        mButtonBack = findViewById(R.id.buttonAllOrdersBack);

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent); // launch that activity
            }
        });

    }
}