package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemControlsActivity extends AppCompatActivity {
    private TextView mTextItemControls;
    private Button mButtonAddItem;
    private Button mButtonUpdatePrice;
    private Button mButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_controls);
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        mTextItemControls = findViewById(R.id.textViewItemControls);
        mButtonAddItem = findViewById(R.id.buttonAdminAddItem);
        mButtonUpdatePrice = findViewById(R.id.buttonAdminUpdatePrice);
        mButtonBack = findViewById(R.id.buttonAdminItemBack);

        mButtonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
                startActivity(intent);
            }
        });

        mButtonUpdatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                intent.putExtra("editMode", true);
                startActivity(intent);
            }
        });


        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent); // launch that activity
            }
        });
    }
}