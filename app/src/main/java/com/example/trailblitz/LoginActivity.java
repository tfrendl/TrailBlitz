package com.example.trailblitz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mNewAccountButton;

    // TODO: make DAO

    private String mUsername;
    private String mPassword;



    // TODO: make User class

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wireupDisplay();

    }

    /**
     * Wire up the display elements for the login screen by associating them with
     * their respective views and setting up event listeners for login and new account buttons.
     * This method initializes the following UI elements:
     * - Username Field (EditText)
     * - Password Field (EditText)
     * - Login Button
     * - New Account Button
     * Additionally, it sets up click listeners for the Login and New Account Buttons
     * to handle user interactions. The click listeners are currently placeholders,
     * and it is recommended to finish their implementations for login and new account functionality.
     * <p>
     * Note: Make sure the corresponding layout XML file contains the necessary views
     * with the specified IDs (R.id.editTextLoginUsername, R.id.editTextLoginPassword, etc.)
     * for this method to successfully wire up the display elements.
     */
    private void wireupDisplay() {
        // Wire up text field with their respective views
        mUsernameField = findViewById(R.id.editTextLoginUsername);
        mPasswordField = findViewById(R.id.editTextLoginPassword);

        // Wire up buttons with their respective views
        mLoginButton = findViewById(R.id.buttonLogin);
        mNewAccountButton = findViewById(R.id.buttonNewUser);

        // Set up click listener for the Login button
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Finish the implementation for login functionality
                // Add the necessary logic to handle user login when the Login Button is clicked
            }
        });

        // Set up the click listener for the New Account Button
        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Finish the implementation for creating a new account
                // Add the necessary logic to handle creating a new account when the New Account Button is clicked
            }
        });
    }

    /**
     * Create an Intent for launching the LoginActivity.
     * This factory method simplifies the process of creating an Intent for starting the LoginActivity.
     * The calling component can use this Intent to navigate to the LoginActivity.
     *
     * @param context The context from which the Intent is being created.
     * @return An Intent configured to launch the LoginActivity.
     */
    public static Intent intentFactory(Context context){
        // Create an Intent to launch the LoginActivity
        Intent intent = new Intent(context, LoginActivity.class);

        // Return the configured Intent
        return intent;
    }
}
