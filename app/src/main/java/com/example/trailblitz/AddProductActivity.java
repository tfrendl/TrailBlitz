package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

public class AddProductActivity extends AppCompatActivity {
    private TextView mAddProductText;
    private EditText mProductPrompt;
    private EditText mPricePrompt;
    private EditText mQuantityPrompt;
    private Button mButtonAdd;
    private Button mButtonBack;

    private TrailBlitzDAO mTrailBlitzDAO;
    private TrailBlitz mTrailBlitz; // contains item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getDatabase();
        wireUpDisplay();
    }


    private void getDatabase() {
        mTrailBlitzDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()   // generally do not want to do on main thread
                .build()
                .getTrailBlitzDAO();
    }

    private void wireUpDisplay() {
        mAddProductText = findViewById(R.id.textViewAddProduct);
        mProductPrompt = findViewById(R.id.editTextEntryProduct);
        mPricePrompt = findViewById(R.id.editTextPrice);
        mQuantityPrompt = findViewById(R.id.editTextQuan);
        mButtonAdd = findViewById(R.id.buttonAdd);
        mButtonBack = findViewById(R.id.buttonProBack);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrailBlitz product = getValuesFromDisplay();
                if (product != null) {
                    mTrailBlitzDAO.insert(product);
                }
            }
        });

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ItemControlsActivity.class);
                startActivity(intent);
            }
        });
    }

    private TrailBlitz getValuesFromDisplay() {
        String itemName = "no item found";
        double price = 0;
        int quantity = 0;
        // see if field is empty https://stackoverflow.com/questions/6290531/how-do-i-check-if-my-edittext-fields-are-empty
        if(mProductPrompt.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Enter an item name to add", Toast.LENGTH_SHORT).show();
            return null;
        }
        itemName = mProductPrompt.getText().toString();
        if(mPricePrompt.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Enter an item price to add", Toast.LENGTH_SHORT).show();
            return null;
        }

        if(mQuantityPrompt.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Enter an item quantity to add", Toast.LENGTH_SHORT).show();
            return null;
        }
        try {
            price = Double.parseDouble(mPricePrompt.getText().toString());
        } catch (NumberFormatException e) {
            Log.d("TRAILBLITZ", "couldn't convert price");
        }
        try {
            quantity = Integer.parseInt(mQuantityPrompt.getText().toString());
        } catch (NumberFormatException e) {
            Log.d("TRAILBLITZ", "couldn't convert quantity");
        }

        if(checkIfInDatabase(itemName)) {
            Toast.makeText(this, itemName + " is already in the store", Toast.LENGTH_SHORT).show();
            return null;
        }

        Toast.makeText(this, quantity + " " + itemName + "'s were added to the store for $" + price, Toast.LENGTH_SHORT).show();

        TrailBlitz trailBlitz = new TrailBlitz(itemName, price, quantity);
        return trailBlitz;
    }

    private boolean checkIfInDatabase(String itemName) {
        mTrailBlitz = mTrailBlitzDAO.getTrailBlitzByItem(itemName);
        if (mTrailBlitz != null) {
            return true;
        }
        return false;
    }
}