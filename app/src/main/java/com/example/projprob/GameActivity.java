package com.example.projprob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private Button giveupbtn;
    private ConstraintLayout gamelayout;
    private String size = "4x4"; // Default grid size
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init(); // Initializes components
        updateBackgroundColors();

        board = new Board(this, 6);
        LinearLayout linearLayout = findViewById(R.id.game);
        linearLayout.addView(board);
        linearLayout.setBackgroundColor(Color.RED);

    }

    private void init() {
        giveupbtn = findViewById(R.id.giveupbtn); // Button for "Give Up" option
        giveupbtn.setOnClickListener(this); // Listens for clicks on the "Give Up" button


        gamelayout = findViewById(R.id.game_layout);


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

    private void updateSize()
    {
        int Rsize;



    }

    public void updateBackgroundColors()
    {
        int colorRes;

        String color = MainActivity.staticColorRes;

        switch (color.toLowerCase()) {
            case "black":
                colorRes = Color.BLACK;
                break;
            case "white":
                colorRes = Color.WHITE;
                break;
            case "blue":
                colorRes = Color.BLUE;
                break;
            case "red":
                colorRes = Color.RED;
                break;
            case "green":
                colorRes = Color.GREEN;
                break;
            default:
                colorRes = Color.BLACK;
        }
        gamelayout.setBackgroundColor(colorRes);

        // Update color for all buttons to ensure visibility
        int textColor = color.equalsIgnoreCase("white") ? Color.BLACK : Color.WHITE;
        giveupbtn.setTextColor(textColor);
    }
}
