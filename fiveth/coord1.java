package com.example.fiveth;
//課題R
public class coord1 {
    double x, y;

    coord1() {
        x = y = 0.0;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void moveto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void print() {
        System.out.println("x = " + x + ", y = " + y);
    }
}
