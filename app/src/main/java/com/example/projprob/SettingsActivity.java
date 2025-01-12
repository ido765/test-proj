package com.example.projprob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button Backset;
    private Spinner spinner, colorSpinner; // Spinner for grid size and background color
    private boolean IsFirstTime = true; // Tracks if this is the first time the spinners are used
    String[] sizes = {"choose grid size", "4x4", "5x5", "6x6", "7x7"}; // Available grid sizes
    String[] colors = {"choose background color", "#FFFFFF", "#FF0000", "#00FF00", "#0000FF"}; // Background colors (hex values)
    private FBmodule fbmodule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init(); // Initializes components

        // Configures the grid size spinner
        spinner = findViewById(R.id.spin);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sizeAdapter);

        // Configures the background color spinner
        colorSpinner = findViewById(R.id.color_spin);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if (i > 0) { // Skips the first item (placeholder)
                    String selectedColor = colors[i];
                    // Update background color in Firebase
                    fbmodule.ChangeBackgroundColorInFirebase(selectedColor);

                    // Return the selected color back to MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("background_color", selectedColor);
                    setResult(RESULT_OK, intent); // Returns the selected color to MainActivity
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SettingsActivity.this, "No color selected", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colors);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);

        // Initialize Firebase module
        fbmodule = new FBmodule(this);
    }

    private void init() {
        // Initializes the back button
        Backset = findViewById(R.id.back_set);
        Backset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Returns to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        if (!IsFirstTime) {
            // Sends the selected size to Firebase
            String selectedSize = sizes[i];
            fbmodule.ChangeSizeInFirebase(selectedSize);

            // Sends the selected size back to MainActivity
            Intent intent = new Intent();
            intent.putExtra("size", selectedSize);
            setResult(RESULT_OK, intent);
            finish();
        }
        IsFirstTime = false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "No size selected", Toast.LENGTH_SHORT).show();
    }
}
