package com.example.trailblitz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trailblitz.db.AppDatabase;
import com.example.trailblitz.db.TrailBlitzDAO;

import java.util.List;

public class DeleteUsersActivity extends AppCompatActivity {
    private TextView mViewUsers;
    private EditText mUsernameField;
    private Button mDeleteButton;
    private Button mBackButton;

    private TrailBlitzDAO mTrailBlitzDAO;
    private String mUsername;
    private User mUser;
    private List<String> mUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("in delete users");
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
        mViewUsers.setMovementMethod(new ScrollingMovementMethod());
        refreshDisplay();

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
                    refreshDisplay();
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

    private void refreshDisplay() {
        mUsers = mTrailBlitzDAO.loadUsernames();
        if(mUsers.size() <= 0) {
            mViewUsers.setText(R.string.no_users);
        }

        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String user : mUsers) {
            sb.append(i + ". " + user);
            sb.append("\n");
            i++;
        }
        mViewUsers.setText(sb.toString());
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