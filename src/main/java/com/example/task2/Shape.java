package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    protected Color color; // Общий цвет для всех фигур

    public Shape(Color color) {
        this.color = color;
    }
    public abstract void draw(GraphicsContext gr);
    public abstract String descriptor();
}
