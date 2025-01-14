package com.example.projprob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btntogame, btntoinst, btntoset;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private FBmodule fbmodule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); // Initializes components
    }

    private void init() {
        // Sets up buttons for navigation
        btntogame = findViewById(R.id.to_game);
        btntogame.setOnClickListener(this);
        btntoinst = findViewById(R.id.inst);
        btntoinst.setOnClickListener(this);
        btntoset = findViewById(R.id.settings);
        btntoset.setOnClickListener(this);

        // Initializes ActivityResultLauncher for receiving data from other activities
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String str = data.getStringExtra("size"); // Retrieves size from intent
                        Toast.makeText(MainActivity.this, "" + str, Toast.LENGTH_SHORT).show();

                        fbmodule.ChangeSizeInFirebase(str); // Updates size in Firebase
                    }
                }
        );

        fbmodule = new FBmodule(this); // Initializes Firebase module
    }

    @Override
    public void onClick(View v) {
        if (btntogame == v) {
            // Navigates to GameActivity
            Intent intent = new Intent(this, GameActivity.class);
            activityResultLauncher.launch(intent);
        }
        if (btntoinst == v) {
            // Navigates to Instructions activity
            Intent intent = new Intent(this, Instructions.class);
            activityResultLauncher.launch(intent);
        }
        if (btntoset == v) {
            // Navigates to SettingsActivity
            Intent intent = new Intent(this, SettingsActivity.class);
            activityResultLauncher.launch(intent);
        }
    }
}
