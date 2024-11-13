package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Plus extends Shape {
    private double delta; //размер лучей плюса

    public Plus(double x, double y, double delta, Color color) {
        super(x, y, color);
        this.delta = delta;
    }
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(color);
        gr.setLineWidth(2);

        // Вертикальная линия
        gr.strokeLine(x, y - delta, x, y + delta);

        // Горизонтальная линия
        gr.strokeLine(x - delta, y, x + delta, y);
    }

    @Override
    public String descriptor() {
        return "плюс";
    }
}
