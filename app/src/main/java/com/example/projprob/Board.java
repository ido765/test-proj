package com.example.projprob;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;

public class Board extends View {
    private int size;
    private Square[][] arr;
    private int[] rowSums;
    private int[] colSums;
    private float offsetX, offsetY;

    public Board(Context context, int size) {
        super(context);
        this.size = size + 1; // 5 עבור 4x4 מספרים + שורה ועמודה לסכומים
        rowSums = new int[this.size];
        colSums = new int[this.size];
        init();
    }

    private void init() {
        arr = new Square[size][size];
        fillArr(arr);
        calculateSums();
    }

    private void fillArr(Square[][] arr) {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (i == 0 && k == 0) {
                    arr[i][k] = new Square(0, 0, 0, i, k, false, false, false, false); // תא ריק
                } else if (i == 0 || k == 0) {
                    arr[i][k] = new Square(0, 0, 0, i, k, false, false, false, false); // תאים לסכומים
                } else {
                    int num = rand.nextInt(9) + 1;
                    arr[i][k] = new Square(0, 0, num, i, k, true, false, false, false);
                }
            }
        }
    }

    private void calculateSums() {
        for (int i = 1; i < size; i++) {
            int sum = 0;
            for (int k = 1; k < size; k++) {
                sum += arr[i][k].getNum();
            }
            rowSums[i] = sum;
        }
        for (int k = 1; k < size; k++) {
            int sum = 0;
            for (int i = 1; i < size; i++) {
                sum += arr[i][k].getNum();
            }
            colSums[k] = sum;
        }
    }

    public void updateSquareSizes() {
        int boardWidth = getWidth();
        int boardHeight = getHeight() - 150; // הפחתת גובה לכפתורים ולכותרת

        // חישוב גודל התא כריבוע, בהתבסס על המימד הקטן יותר
        int squareSize = Math.min(boardWidth / size, boardHeight / size);

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                arr[i][k].height = squareSize;
                arr[i][k].width = squareSize;
            }
        }

        offsetX = (getWidth() - (size * squareSize)) / 2; // מרכוז אופקי
        offsetY = 50; // מקום לכותרת
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (arr[1][1].height == 0 || arr[1][1].width == 0) {
            updateSquareSizes();
        }

        Paint paint = new Paint();
        paint.setTextSize(arr[0][0].height / 2);
        paint.setAntiAlias(true);

        // ציור כותרת "Level 1"
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        canvas.drawText("Level 1", getWidth() / 2 - 50, 40, paint);
        paint.setTextSize(arr[0][0].height / 2); // חזרה לגודל טקסט רגיל

        // ציור התאים
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                Square sq = arr[i][k];
                float x = offsetX + k * sq.width;
                float y = offsetY + i * sq.height;

                // רקע התא
                paint.setStyle(Paint.Style.FILL);
                if (i == 0 || k == 0) {
                    paint.setColor(Color.BLUE); // צבע כחול לתאים עם סכומים
                } else {
                    paint.setColor(sq.isPressed() ? Color.YELLOW : Color.WHITE); // תאים רגילים לבנים
                }
                canvas.drawRect(x, y, x + sq.width, y + sq.height, paint);

                // מסגרת התא
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(3);
                canvas.drawRect(x, y, x + sq.width, y + sq.height, paint);

                // הצגת סכומים או מספרים במרכז
                paint.setStyle(Paint.Style.FILL);
                if (i == 0 && k > 0) {
                    paint.setColor(Color.WHITE); // סכום עמודה
                    canvas.drawText(String.valueOf(colSums[k]), x + sq.width / 2 - paint.measureText(String.valueOf(colSums[k])) / 2, y + sq.height / 2 + paint.getTextSize() / 3, paint);
                } else if (k == 0 && i > 0) {
                    paint.setColor(Color.WHITE); // סכום שורה
                    canvas.drawText(String.valueOf(rowSums[i]), x + sq.width / 2 - paint.measureText(String.valueOf(rowSums[i])) / 2, y + sq.height / 2 + paint.getTextSize() / 3, paint);
                } else if (sq.getNum() != 0) {
                    paint.setColor(Color.BLACK); // מספר רגיל
                    canvas.drawText(String.valueOf(sq.getNum()), x + sq.width / 2 - paint.measureText(String.valueOf(sq.getNum())) / 2, y + sq.height / 2 + paint.getTextSize() / 3, paint);
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateSquareSizes();
    }
}