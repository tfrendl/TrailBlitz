package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {
    private TextView mViewCartText;
    private TextView mShowUserCart;
    private TextView mTotalText;
    private TextView mShowUserTotal;
    private Button mButtonUpdateCart;
    private Button mButtonCheckout;
    private Button mButtonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        wireUpDisplay();
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