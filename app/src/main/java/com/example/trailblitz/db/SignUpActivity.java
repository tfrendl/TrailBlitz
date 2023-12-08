package com.example.trailblitz.db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.R;
import com.example.trailblitz.User;

public class SignUpActivity extends AppCompatActivity {
    private TextView mSignUpDisplay;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mPasswordRepeatField;
    private Button mButton;

    private String mUsername;
    private String mPassword;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        mSignUpDisplay = findViewById(R.id.textViewSignUp);
        mUsernameField = findViewById(R.id.editTextUsername);
        mPasswordField = findViewById(R.id.editTextPassword);
        mPasswordRepeatField = findViewById(R.id.editTextPassword2);
        mButton = findViewById(R.id.buttonSignUp);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = getValuesFromDisplay();
            }
        });

    }

    private User getValuesFromDisplay() {
        String username = "No username found";
        String password = "No password found";
        String passwordRepeat = "No repeat password found";
        username = mUsernameField.getText().toString();
        password = mPasswordField.getText().toString();
        passwordRepeat = mPasswordRepeatField.getText().toString();

        if (!password.equals(passwordRepeat)) {
            Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            mPasswordField.getText().clear();
            mPasswordRepeatField.getText().clear();
            return null;
        }

        Toast.makeText(SignUpActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
        User user = new User(username, password, false);    // default not admin
        return user;
    }
}