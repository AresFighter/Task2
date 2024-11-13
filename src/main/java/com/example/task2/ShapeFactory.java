package com.example.task2;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape createShape(String shapeType, double x, double y, Color color) {
        switch (shapeType.toLowerCase()) {
            case "круг":
                //параметры по умолчанию
                return new Circle(x, y, 10, color);
            case "прямоугольник":
                return new Rectangle(x, y, 10, 5, color);
            case "треугольник":
                return new Triangle(x, y, 15, 5, 13, color);
            case "плюс":
                return new Plus(x, y, 10, color); // Пример дельты
            default:
                return null;
        }
    }
}
