package com.example.fiveth;
//課題R
public class CircleTest2 {
    public static void main(String[] args) {
        Circle1 c= new Circle1();
        c.move(100.0, 100.0);
        c.print(); // (100,100)

        c.move(100.0, 100.0);
        c.print(); // (200,200)

        c.moveto(100.0, 100.0);
        c.print(); // (100,100)
    }
}
