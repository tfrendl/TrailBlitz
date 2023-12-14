package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

public class ChangePassActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.trailblitz.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.trailblitz.PREFERENCES_KEY";
    private TextView mChangePassText;
    private Button mButtonUpdate;
    private Button mButtonBack;
    private EditText mNewPassField;
    private EditText mNewPassRepeatField;
    private TrailBlitzDAO mTrailBlitzDAO;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        wireUpDisplay();
        getDatabase();
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

    private void wireUpDisplay() {
        mChangePassText = findViewById(R.id.textViewChangePass);
        mButtonUpdate = findViewById(R.id.buttonUpdatePass);
        mButtonBack = findViewById(R.id.buttonBackUpdatePass);
        mNewPassField = findViewById(R.id.editTextNewPassword);
        mNewPassRepeatField = findViewById(R.id.editTextnewPass2);

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = getValuesFromDisplay();
                if (user != null) {
                    mTrailBlitzDAO.update(user);
                }
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

    private User getValuesFromDisplay() {
        String password1 = "no password";
        String password2 = "no password";

        // get who is logged in
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt(USER_ID_KEY, -1);
        User user = mTrailBlitzDAO.getUserByUserId(userId);

        password1 = mNewPassField.getText().toString();
        password2 = mNewPassRepeatField.getText().toString();

        if (!password1.equals(password2)) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return null;
        }

        Toast.makeText(this, "Updating password...", Toast.LENGTH_SHORT).show();
        user.setPassword(password1);

        return user;
    }
}