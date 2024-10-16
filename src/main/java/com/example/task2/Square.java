package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape {
    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(Color.DARKORANGE);
        gr.fillRect(150, 100, 100, 100);
    }

    @Override
    public String descriptor() {
        return "Square";
    }
}
