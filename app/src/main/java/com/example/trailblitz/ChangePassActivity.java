package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePassActivity extends AppCompatActivity {
    private TextView mChangePassText;
    private Button mButtonUpdate;
    private Button mButtonBack;
    private EditText mNewPassField;
    private EditText mNewPassRepeatField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        mChangePassText = findViewById(R.id.textViewChangePass);
        mButtonUpdate = findViewById(R.id.buttonUpdatePass);
        mButtonBack = findViewById(R.id.buttonBackUpdatePass);
        mNewPassField = findViewById(R.id.editTextNewPassword);
        mNewPassRepeatField = findViewById(R.id.editTextnewPass2);

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
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
}