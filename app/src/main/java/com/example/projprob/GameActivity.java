package com.example.projprob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button giveupbtn;
    String size = "4x4"; // Default grid size

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init(); // Initializes components
    }

    private void init() {
        giveupbtn = findViewById(R.id.giveupbtn); // Button for "Give Up" option
        giveupbtn.setOnClickListener(this); // Listens for clicks on the "Give Up" button
    }

    private void createDialog() {
        // Creates and shows a custom dialog
        CustomDialog customDialog = new CustomDialog(this);
        customDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (giveupbtn == v) {
            createDialog(); // Shows the custom dialog when the "Give Up" button is clicked
        }
    }
}
