package com.example.trailblitz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.trailblitz.userIdKey";
    private Button mInventoryButton;
    private Button mCartButton;
    private Button mOrderHistoryButton;
    private Button mAdminControlsButton;
    private Button mUpdatePasswordButton;
    private Button mSignOutButton;

    /**
     * Called when the activity is first created. This method is responsible for
     * initializing the activity's user interface, setting the content view,
     * and wiring up display elements. It is called after the activity is
     * created but before it becomes visible to the user.
     * <p>
     * In this implementation:
     * - The content view is set to the layout defined in {@code R.layout.activity_main}.
     * - The {@code updateUsernameDisplay()} method is called to initialize and display the username.
     * - The {@code wireupDisplay()} method is called to associate UI elements with their views
     *   and set up event listeners for specific buttons.
     * <p>
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down, this Bundle contains the
     *                           data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUsernameDisplay();
        wireupDisplay();

    }

    /**
     * Update the displayed username in the user interface.
     * This method is intended to be called when the username associated
     * with the current user changes. It should handle updating the UI elements
     * that show the username to reflect the updated information.
     * <p>
     */
    private void updateUsernameDisplay() {
        // TODO: Finish the implementation for updating the displayed username
    }

    /**
     * Wire up the display elements by associating them with their respective views
     * and setting up event listeners for specific buttons.
     * This method initializes the following UI elements:
     * - Inventory Button
     * - Cart Button
     * - Order History Button
     * - Admin Controls Button
     * - Update Password Button
     * - Sign Out Button
     * Additionally, it sets up a click listener for the Sign Out Button to handle the
     * user logout functionality.
     * <p>
     * Note: Make sure the corresponding layout XML file contains the necessary views
     * with the specified IDs (R.id.buttonInventory, R.id.buttonCart, etc.) for this
     * method to successfully wire up the display elements.
     */
    private void wireupDisplay(){
        mInventoryButton = findViewById(R.id.buttonInventory);
        mCartButton = findViewById(R.id.buttonCart);
        mOrderHistoryButton = findViewById(R.id.buttonPastOrders);
        mAdminControlsButton = findViewById(R.id.buttonAdminControls);
        mUpdatePasswordButton = findViewById(R.id.buttonUpdatePassword);
        mSignOutButton = findViewById(R.id.buttonSignOut);

        // Set up click listener for the Inventory Button
        mInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Finish the implementation for accessing the inventory
            }
        });

        // Set up click listener for the Cart button
        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Finish the implementation for accessing the cart
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
                // TODO: Finish the implementation for accessing the admin controls
            }
        });

        // Set up click listener for Update Password Button
        mUpdatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Finish the implementation for accessing the update password
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
     * Prompt the user with an AlertDialog to confirm logout and handle the user's response.
     * This method creates an AlertDialog with a message confirming the user's intent to logout.
     * It provides positive and negative buttons for the user to confirm or cancel the logout action.
     * The positive button is set up to execute the necessary logic to log the user out when clicked.
     * <p>
     */
    private void logoutUser() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage(R.string.logout);

        alertBuilder.setPositiveButton(getString(R.string.yes),
                (dialog, which) -> {
                    // TODO: Finish the implementation for logging the user out
                    // TODO: the next two lines should probably get moved there
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
     * Create an Intent for launching the MainActivity with the specified user ID as an extra.
     * This factory method simplifies the process of creating an Intent for starting the MainActivity
     * with the provided user ID as an extra. The calling component can use this Intent to navigate
     * to the MainActivity while passing necessary user-related information.
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
}