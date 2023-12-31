package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    private TextView mAdminDisplay;
    private Button mButtonAddUser;
    private Button mButtonDeleteUser;
    private Button mButtonItemControls;
    private Button mButtonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        mAdminDisplay = findViewById(R.id.textViewAdmin);
        mButtonAddUser = findViewById(R.id.buttonAddUser);
        mButtonDeleteUser = findViewById(R.id.buttonDeleteUser);
        mButtonItemControls = findViewById(R.id.buttonItemControls);
        mButtonBack = findViewById(R.id.buttonAdminBack);

        mButtonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                // How to use booleans in intents:
                // https://programzools.com/android/pass-data-from-one-activity-to-other-android.html
                intent.putExtra("fromAdmin", true);
                startActivity(intent); // launch that activity
            }
        });

        mButtonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeleteUsersActivity.class);
                startActivity(intent);
            }
        });

        mButtonItemControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ItemControlsActivity.class);
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