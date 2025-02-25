package com.example.projprob;

public class Square
{
    private int height, width, num;
    private boolean ShouldBeUsed, pressed;

    public Square(int height, int width, int num, boolean shouldBeUsed, boolean pressed) {
        this.height = height;
        this.width = width;
        this.num = num;
        ShouldBeUsed = shouldBeUsed;
        this.pressed = pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
