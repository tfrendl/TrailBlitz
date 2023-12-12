package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemControlsActivity extends AppCompatActivity {
    private TextView mTextItemControls;
    private Button mButtonAddItem;
    private Button mButtonUpdatePrice;
    private Button mUpdateQuantity;
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
        mUpdateQuantity = findViewById(R.id.buttonAdminUpdateQuantity);
        mButtonBack = findViewById(R.id.buttonAdminItemBack);

        mButtonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement
            }
        });

        mButtonUpdatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement
            }
        });

        mUpdateQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement
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