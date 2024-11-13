package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    private double radius;

    public Circle(double x, double y, double radius, Color color) {

        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(color);
        gr.fillOval(x - radius, y - radius,2 * radius, 2 * radius);
    }

    @Override
    public String descriptor() {
        return "круг";
    }
}
