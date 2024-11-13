package com.example.task2;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape createShape(int numberOfSides) {
        switch (numberOfSides) {
            case 0:
                return new Circle(Color.CORAL);
            case 1:
                return new Straight(Color.DARKBLUE);
            case 2:
                return new Angle(Color.BLACK);
            case 3:
                return new Triangle(Color.INDIGO);
            case 4:
                return new Square(Color.DARKORANGE);
            case 5:
                return new Pentagon(Color.DARKOLIVEGREEN);
            default:
                return null;
        }
    }
}
