package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.DARKBLUE);
        gr.setLineWidth(5);
        gr.strokeLine(150, 100, 250, 100);
    }

    @Override
    public String descriptor() {
        return "Line";
    }
}
