package com.example.trailblitz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;
import java.util.List;

/**
 * @author Talia Frendl
 * @since  November 30, 2023
 * Assignment: Project 02: Part 02 Login and Landing Page
 * Used ChatGPT to help write javadoc comments.
 * MainActivity represents the main screen of the TrailBlitz application for authenticated users.
 * It provides access to various features such as viewing inventory, managing the shopping cart,
 * accessing order history, and administrative controls (if the user is an admin).
 * The class handles user authentication, UI initialization, and button click events for navigation.
 */
public class MainActivity extends AppCompatActivity {
    // constants for intent extra keys and preferences
    private static final String USER_ID_KEY = "com.example.trailblitz.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.trailblitz.PREFERENCES_KEY";

    // UI elements
    private Button mInventoryButton;
    private Button mCartButton;
    private Button mOrderHistoryButton;
    private Button mAdminControlsButton;
    private Button mUpdatePasswordButton;
    private Button mSignOutButton;
    private TextView mUserNameTextView;


    // Database and user-related fields
    private TrailBlitzDAO mTrailBlitzDAO;
    private int mUserId = -1;   // default value when there is no user yet defined
    private SharedPreferences mPreferences = null;
    private User mUser;

    /**
     * Called when the activity is first created. Initializes the activity's UI, sets the content view,
     * and wires up display elements. This method is called after the activity is created but before
     * it becomes visible to the user.
     *
     * @param savedInstanceState If the activity is being re-initialized, this Bundle contains
     *                           the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDatabase();
        checkForUser();
        loginUser(mUserId);
        wireupDisplay();
    }

    /**
     * Initializes the TrailBlitzDAO by building the Room database instance.
     * This method allows database operations on the main thread for simplicity.
     */
    private void getDatabase() {
        mTrailBlitzDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()       // generally do not want to do on main thread
                .build()                        // constructs
                .getTrailBlitzDAO();                // returns our instance - makes sure only one instance at a time
    }

    /**
     * Checks for a user in the intent or preferences. If not found, redirects to the login screen.
     */
    private void checkForUser() {
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1); // if theres an extra, it becomes USER_ID_KEY
        // if there isn't an extra, it's -1
        // do we have a user in the intent?
        if(mUserId != -1) {
            return;
        }

        if(mPreferences == null) {
            getPrefs();
        }
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        // do we have a user in the preferences?
        if(mUserId != -1) {
            return;
        }

        List<User> users = mTrailBlitzDAO.getAllUsers();
        // if there are no users, make a default user
        if (users.size() <= 0) {
            User defaultUser = new User("testuser1","testuser1", false);
            User altUser = new User("admin2","admin2", true);
            mTrailBlitzDAO.insert(defaultUser, altUser);
        }

        Intent intent = LoginActivity.intentFactory(this);
        startActivity(intent);
    }

    /**
     * Logs in the user based on the provided user ID and retrieves the user's information.
     *
     * @param userId The user ID used to log in the user.
     */
    private void loginUser(int userId) {
        mUser = mTrailBlitzDAO.getUserByUserId(userId);
        addUserToPreference(userId);
    }

    /**
     * Wire up the display elements by associating them with their respective views
     * and setting up event listeners for specific buttons.
     * This method initializes UI elements for inventory, cart, order history, admin controls, etc.
     * It sets up click listeners for these buttons to handle navigation or other functionalities.
     */
    private void wireupDisplay(){
        mAdminControlsButton = findViewById(R.id.buttonAdminControls);

        // only show admin button if user is an admin
        if(mUser.getIsAdmin()) {
            mAdminControlsButton.setVisibility(View.VISIBLE);
        } else {
            mAdminControlsButton.setVisibility(View.INVISIBLE);
        }
        mInventoryButton = findViewById(R.id.buttonInventory);
        mCartButton = findViewById(R.id.buttonCart);
        mOrderHistoryButton = findViewById(R.id.buttonPastOrders);
        mUpdatePasswordButton = findViewById(R.id.buttonUpdatePassword);
        mSignOutButton = findViewById(R.id.buttonSignOut);
        mUserNameTextView = findViewById(R.id.textViewUsername);

        mUserNameTextView.setText(mUser.getUserName());     // show username

        // Set up click listener for the Inventory Button
        mInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InventoryActivity.class);
                intent.putExtra("editMode", false);
                startActivity(intent); // launch that activity
            }
        });

        // Set up click listener for the Cart button
        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent); // launch that activity
            }
        });

        // Set up click listener for Order History Button
        mOrderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Finish the implementation for accessing the order history
            }
        });

        // Set up click listener for Admin Controls Button
        mAdminControlsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent); // launch that activity
            }
        });

        // Set up click listener for Update Password Button
        mUpdatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePassActivity.class);
                startActivity(intent); // launch that activity
            }
        });

        // Set up a click listener for the Sign Out Button
        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    /**
     * Initializes shared preferences.
     */
    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Clears the user information from shared preferences.
     */
    private void clearUserFromPref() {
        addUserToPreference(-1);
    }

    /**
     * Adds the user ID to shared preferences.
     *
     * @param userId The user ID to be added to preferences.
     */
    private void addUserToPreference(int userId) {
        if(mPreferences == null) {
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();      // singleton pattern
        editor.putInt(USER_ID_KEY, userId);
        editor.apply();
    }

    /**
     * Clears the user information from the intent.
     */
    private void clearUserFromIntent() {
        getIntent().putExtra(USER_ID_KEY, -1);
    }

    /**
     * Logs out the user by displaying a confirmation dialog and handling the user's response.
     * Clears the user information from intent, preferences, and performs necessary actions.
     */
    private void logoutUser() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage(R.string.logout);

        alertBuilder.setPositiveButton(getString(R.string.yes),
                (dialog, which) -> {
                    clearUserFromIntent();
                    clearUserFromPref();
                    mUserId = -1;
                    checkForUser();
                    // go back to Login activity
                    Intent intent = LoginActivity.intentFactory(this);
                    startActivity(intent);
                });
        alertBuilder.setNegativeButton(getString(R.string.no),
                (dialog, which) -> {
                    // we don't really need to do anything here
                });
        alertBuilder.create().show();
    }

    /**
     * Factory method for creating an Intent to launch the MainActivity with the specified user ID as an extra.
     * This method simplifies the process of creating an Intent for starting the MainActivity with the provided user ID as an extra.
     *
     * @param context The context from which the Intent is being created.
     * @param userId  The user ID to be included as an extra in the Intent.
     * @return An Intent configured to launch the MainActivity with the provided user ID as an extra.
     */
    public static Intent intentFactory(Context context, int userId) {
        // Create an Intent to launch the MainActivity
        Intent intent = new Intent(context, MainActivity.class);

        // Put the user ID as an extra in the Intent
        intent.putExtra(USER_ID_KEY, userId);

        // Return the configured Intent
        return intent;
    }

    public int getUserId() {
        return mUserId;
    }
}