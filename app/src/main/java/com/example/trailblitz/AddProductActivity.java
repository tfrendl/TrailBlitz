package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddProductActivity extends AppCompatActivity {
    private TextView mAddProductText;
    private EditText mProductPrompt;
    private EditText mPricePrompt;
    private EditText mQuantityPrompt;
    private Button mButtonAdd;
    private Button mButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        mAddProductText = findViewById(R.id.textViewAddProduct);
        mProductPrompt = findViewById(R.id.editTextEntryProduct);
        mPricePrompt = findViewById(R.id.editTextPrice);
        mQuantityPrompt = findViewById(R.id.editTextQuantity);
        mButtonAdd = findViewById(R.id.buttonAdd);
        mButtonBack = findViewById(R.id.buttonProBack);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement
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
}