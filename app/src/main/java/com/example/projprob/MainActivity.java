package com.example.projprob;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        // Initializes Firebase module
        fbmodule = new FBmodule(this);

        // Sets up the ActivityResultLauncher to handle data returned from other activities
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String size = data.getStringExtra("size"); // Retrieves grid size from intent
                            String color = data.getStringExtra("background_color"); // Retrieves background color

                            if (size != null) {
                                fbmodule.ChangeSizeInFirebase(size); // Updates size in Firebase
                            }
                            if (color != null) {
                                fbmodule.ChangeBackgroundColorInFirebase(color); // Updates background color in Firebase
                                setBackgroundColor(color); // Updates the background color locally
                            }
                        }
                    }
                }
        );
    }

    public void setBackgroundColor(String color) {
        // Updates the background color of the main layout
        ConstraintLayout mainLayout = findViewById(androidx.constraintlayout.widget.R.id.match_constraint); // Ensure your root layout has this ID
        mainLayout.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void onClick(View v) {
        if (btntogame == v) {
            Intent intent = new Intent(this, GameActivity.class);
            activityResultLauncher.launch(intent);
        }
        if (btntoinst == v) {
            Intent intent = new Intent(this, Instructions.class);
            activityResultLauncher.launch(intent);
        }
        if (btntoset == v) {
            Intent intent = new Intent(this, SettingsActivity.class);
            activityResultLauncher.launch(intent);
        }
    }
}
