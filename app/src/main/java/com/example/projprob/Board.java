package com.example.projprob;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class Board extends View {
    private int size;

    public Board(Context context, int size) {
        super(context);


        this.size = size;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);



    }
}
