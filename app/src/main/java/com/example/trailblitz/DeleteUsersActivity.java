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

public class DeleteUsersActivity extends AppCompatActivity {
    private TextView mViewUsers;
    private EditText mUsernameField;
    private Button mDeleteButton;
    private Button mBackButton;

    private TrailBlitzDAO mTrailBlitzDAO;
    private String mUsername;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_users);
        getDatabase();
        wireUpDisplay();
    }

    private void getDatabase() {
        mTrailBlitzDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()   // generally do not want to do on main thread
                .build()
                .getTrailBlitzDAO();
    }


    private void wireUpDisplay() {
        mViewUsers = findViewById(R.id.textViewUsers);
        mUsernameField = findViewById(R.id.editTextUserToDelete);
        mDeleteButton = findViewById(R.id.buttonDelete);
        mBackButton = findViewById(R.id.buttonDeleteBack);

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = mUsernameField.getText().toString();    // get from display
                if (checkForUserInDatabase()){
                    mTrailBlitzDAO.delete(mUser);
                    Toast.makeText(getApplicationContext(), mUsername + " has been deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent); // launch that activity
            }
        });
    }

    private boolean checkForUserInDatabase() {
        mUser = mTrailBlitzDAO.getUserByUsername(mUsername);
        if (mUser == null) {
            Toast.makeText(this, mUsername + " is not a valid user", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}