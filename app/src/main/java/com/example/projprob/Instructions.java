package com.example.projprob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instructions extends AppCompatActivity implements View.OnClickListener {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        init(); // Initializes the back button
    }

    private void init() {
        back = findViewById(R.id.back_ins); // Back button to return to MainActivity
        back.setOnClickListener(this); // Listens for clicks on the back button
    }

    @Override
    public void onClick(View v) {
        if (back == v) {
            // Navigates back to the main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
