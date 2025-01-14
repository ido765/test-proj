package com.example.trivia2024inclass;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projprob.FBmodule;

public class SettingsActivity extends AppCompatActivity {
    private Spinner colorSpinner, sizeSpinner;
    private FBmodule fbmodule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        colorSpinner = findViewById(R.id.color_spin);
        sizeSpinner = findViewById(R.id.size_spin);
        fbmodule = new Fbmodule(null); // No MainActivity context needed in Settings

        setupColorSpinner();
        setupSizeSpinner();
    }

    private void setupColorSpinner() {
        String[] colors = {"#FFFFFF", "#FF0000", "#00FF00", "#0000FF"}; // Example colors
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colors);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedColor = colors[position];
                fbmodule.changeBackgroundColorInFirebase(selectedColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }

    private void setupSizeSpinner() {
        String[] sizes = {"4x4", "5x5", "6x6", "7x7"}; // Example sizes
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = sizes[position];
                fbmodule.changeSizeInFirebase(selectedSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }
}
