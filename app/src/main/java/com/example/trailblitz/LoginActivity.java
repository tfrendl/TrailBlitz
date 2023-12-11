package com.example.trailblitz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;
/**
 * @author Talia Frendl
 * @since  November 30, 2023
 * Assignment: Project 02: Part 02 Login and Landing Page
 * Used ChatGPT to help write javadoc comments.
 * LoginActivity represents the user interface for logging into the TrailBlitz application.
 * It includes UI elements such as username and password fields, login, and new account buttons.
 * This class interacts with the TrailBlitz database to authenticate users and navigate to the main activity upon successful login.
 */

public class LoginActivity extends AppCompatActivity {
    // UI elements
    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mNewAccountButton;


    // Database-related fields
    private TrailBlitzDAO mTrailBlitZDAO;
    private String mUsername;
    private String mPassword;
    private User mUser;

    /**
     * Initializes the activity and sets up the UI elements.
     * It also initializes the database access object (DAO) for TrailBlitz entities.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getDatabase();
        wireupDisplay();
    }

    /**
     * Initializes the TrailBlitzDAO by building the Room database instance.
     * This method allows database operations on the main thread for simplicity.
     */
    private void getDatabase() {
        mTrailBlitZDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()   // generally do not want to do on main thread
                .build()
                .getTrailBlitzDAO();
    }

    /**
     * Wire up the display elements for the login screen and set up click listeners for buttons.
     * This method associates UI elements with their respective views and sets up event listeners for login and new account buttons.
     * It also initializes click listeners for these buttons, currently serving as placeholders for future implementation.
     * Make sure the corresponding layout XML file contains the necessary views
     * with the specified IDs (R.id.editTextLoginUsername, R.id.editTextLoginPassword, etc.).
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
                getValuesFromDisplay();
                if(checkForUserInDatabase()) {
                    if (!validatePassword()) {
                        Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = MainActivity.intentFactory(getApplicationContext(),mUser.getUserId());
                        startActivity(intent);
                    }
                }
            }
        });

        // Set up the click listener for the New Account Button
        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // specify the class of the activity I want to start
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.putExtra("fromAdmin", false);
                startActivity(intent); // launch that activity
            }
        });
    }

    /**
     * Retrieves the username and password values entered in the UI fields.
     */
    private void getValuesFromDisplay() {
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
    }

    /**
     * Checks if the entered username exists in the database and retrieves the corresponding user.
     *
     * @return True if the user exists, false otherwise.
     */
    private boolean checkForUserInDatabase() {
        mUser = mTrailBlitZDAO.getUserByUsername(mUsername);
        if(mUser == null) {
            Toast.makeText(this, "no user " + mUsername + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Validates the entered password against the stored user password.
     *
     * @return True if the password is valid, false otherwise.
     */
    private boolean validatePassword() {
        return mUser.getPassword().equals(mPassword);
    }

    /**
     * Factory method for creating an Intent to launch the LoginActivity.
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
