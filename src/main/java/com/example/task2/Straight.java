package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight extends Shape {

    public Straight(Color color){
        super(color);
    }
    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(color);
        gr.setLineWidth(5);
        gr.strokeLine(150, 100, 250, 100);
    }

    @Override
    public String descriptor() {
        return "отрезок";
    }
}
