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
    private MainActivity mainActivity;
    private LinearLayout gamelayout;
    private String size = "4x4"; // Default grid size
    private int IntSize = 4; // 4 עבור 4x4 מספרים
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        size = MainActivity.StaticSize != null ? MainActivity.StaticSize : "4x4";
        IntSize = IntSizer(size);

        board = new Board(this, IntSize);
        LinearLayout linearLayout = findViewById(R.id.game);
        linearLayout.addView(board);
        updateBackgroundColors();
    }

    private void init() {
        giveupbtn = findViewById(R.id.giveupbtn);
        giveupbtn.setOnClickListener(this);
        gamelayout = findViewById(R.id.game_layout);
    }

    private void createDialog() {
        CustomDialog customDialog = new CustomDialog(this);
        customDialog.show();
    }

    private int IntSizer(String size) {
        switch (size) {
            case "4x4": return 4; // 4x4 מספרים
            case "5x5": return 5; // 5x5 מספרים
            case "6x6": return 6; // 6x6 מספרים
            case "7x7": return 7; // 7x7 מספרים
            default: return 4;
        }
    }

    @Override
    public void onClick(View v) {
        if (giveupbtn == v) {
            createDialog();
        }
    }

    private void updateSize() {
        // TODO: Implement size update logic
    }

    public void updateBackgroundColors() {
        int colorRes;
        String color = MainActivity.staticColorRes != null ? MainActivity.staticColorRes : "black";

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
        LinearLayout linearLayout = findViewById(R.id.game);
        linearLayout.setBackgroundColor(colorRes);
        int textColor = color.equalsIgnoreCase("white") ? Color.BLACK : Color.WHITE;
        giveupbtn.setTextColor(textColor);
    }
}