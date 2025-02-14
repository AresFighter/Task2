package com.example.task2;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class ShapeFactory {
    private final Map<String, Shape> shapeMap = new HashMap<>();

    public ShapeFactory() {
        // Добавление фигур с размерами по умолчанию
        shapeMap.put("круг", new Circle(0, 0, 7, Color.BLACK));
        shapeMap.put("прямоугольник", new Rectangle(0, 0, 15, 10, Color.BLACK));
        shapeMap.put("треугольник", new Triangle(0, 0, 15, 5, 13, Color.BLACK));
        shapeMap.put("плюс", new Plus(0, 0, 5, Color.BLACK)); // Длина лучей 30
    }

    public Shape getShape(String shapeType) {
        Shape prototype = shapeMap.get(shapeType.toLowerCase());
        if (prototype != null) {
            return (Shape) prototype.clone(); // Клонируем объект
        }
        return null;
    }

    public Map<String, Shape> getAllShapes() {
        return shapeMap;
    }
}