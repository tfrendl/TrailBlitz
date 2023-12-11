package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

public class SignUpActivity extends AppCompatActivity {
    private TextView mSignUpDisplay;
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mPasswordRepeatField;
    private Button mButton;
    private Button mButtonBack;

    private String mUsername;
    private String mPassword;
    private User mUser;
    private TrailBlitzDAO mTrailBlitzDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        wireUpDisplay();
        getDatabase();
    }

    private void wireUpDisplay() {
        mSignUpDisplay = findViewById(R.id.textViewSignUp);
        mUsernameField = findViewById(R.id.editTextUsername);
        mPasswordField = findViewById(R.id.editTextPassword);
        mPasswordRepeatField = findViewById(R.id.editTextPassword2);
        mButton = findViewById(R.id.buttonSignUp);
        mButtonBack = findViewById(R.id.buttonBack);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = getValuesFromDisplay();
                if (user != null) {
                    mTrailBlitzDAO.insert(user);
                }
            }
        });

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            /**
             * This checks to see if the intent said that it was or wasn't an admin
             * If admin is accessing, back button goes back to admin controls
             * If admin is not accessing, back button goes to login page
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent receivedIntent = getIntent();
                boolean isFromAdmin = receivedIntent.getBooleanExtra("fromAdmin", false);
                if (isFromAdmin) {
                    // How to use booleans in intents:
                    // https://programzools.com/android/pass-data-from-one-activity-to-other-android.html
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent); // launch that activity
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent); // launch that activity
                }
            }
        });

    }

    /**
     * Initializes the TrailBlitzDAO by building the Room database instance.
     * This method allows database operations on the main thread for simplicity.
     */
    private void getDatabase() {
        mTrailBlitzDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()   // generally do not want to do on main thread
                .build()
                .getTrailBlitzDAO();
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
            // https://www.quora.com/How-can-I-clear-text-in-EditText-when-entered-in-Android-Studio
            mPasswordField.getText().clear();
            mPasswordRepeatField.getText().clear();
            return null;
        }

        Toast.makeText(SignUpActivity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
        User user = new User(username, password, false);    // default not admin
        return user;
    }
}