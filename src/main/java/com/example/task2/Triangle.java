package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements Shape {

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(Color.INDIGO);

        double[] xPoints = {200, 250, 150};
        double[] yPoints = {100, 200, 200};

        gr.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String descriptor() {
        return "Triangle";
    }
}
