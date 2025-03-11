package com.example.projprob;

public class Square {
    public int height, width; // שינוי ל-public כדי שיהיה נגיש מבחוץ
    private int num;
    private boolean shouldBeUsed, pressed, isCorrect, isLocked;
    private int row, col;

    public Square(int height, int width, int num, int row, int col, boolean shouldBeUsed,
                  boolean pressed, boolean isCorrect, boolean isLocked) {
        this.height = height;
        this.width = width;
        this.num = num;
        this.row = row;
        this.col = col;
        this.shouldBeUsed = shouldBeUsed;
        this.pressed = pressed;
        this.isCorrect = isCorrect;
        this.isLocked = isLocked;
    }

    // Getters ו-Setters
    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }
    public boolean isPressed() { return pressed; }
    public void setPressed(boolean pressed) { this.pressed = pressed; }
    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }
    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean isLocked) { this.isLocked = isLocked; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean shouldBeUsed() { return shouldBeUsed; }
    public void setShouldBeUsed(boolean shouldBeUsed) { this.shouldBeUsed = shouldBeUsed; }
}