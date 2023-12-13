package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

import org.w3c.dom.Text;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {
    private TextView mInventoryText;
    private RecyclerView mRecyclerViewItems;
    private EditText mItemPrompt;
    private EditText mQuantityPrompt;
    private Button mButtonAddToCart;
    private Button mButtonBack;
    private TrailBlitzDAO mTrailBlitzDAO;
    private List<TrailBlitz> mItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
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
        mInventoryText = findViewById(R.id.textViewInventory);
        mRecyclerViewItems = findViewById(R.id.recyclerViewInventory);
        mInventoryText.setMovementMethod(new ScrollingMovementMethod());
        refreshDisplay();

        mItemPrompt = findViewById(R.id.editTextItemName);
        mQuantityPrompt = findViewById(R.id.editTextQuantity);
        mButtonAddToCart = findViewById(R.id.buttonAddToCart);
        mButtonBack = findViewById(R.id.buttonBackInventory);

        mButtonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement
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

    private void refreshDisplay() {
        mItems = mTrailBlitzDAO.getAllItems();
        if(mItems.size() <= 0) {
            Toast.makeText(this, "There are no items", Toast.LENGTH_SHORT).show();
        }

        StringBuilder sb = new StringBuilder();

        for (TrailBlitz item : mItems) {
            sb.append(item);
            sb.append("\n");
        }
        mInventoryText.setText(sb.toString());
    }

}