package com.example.projprob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button Backset;
    private Spinner spinner;
    private boolean IsFirstTime = true; // Tracks if this is the first time the spinner is used
    String[] sizes = {"choose grid size", "4x4", "5x5", "6x6", "7x7"}; // Available sizes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init(); // Initializes components

        // Configures the spinner
        spinner = findViewById(R.id.spin);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
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
            // Sends the selected size back to MainActivity
            Intent intent = new Intent();
            intent.putExtra("size", sizes[i]);
            setResult(RESULT_OK, intent);
            finish();
        }
        IsFirstTime = false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Notifies the user when no selection is made
        Toast.makeText(this, "onNothingSelected", Toast.LENGTH_SHORT).show();
    }
}
