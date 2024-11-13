package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle extends Shape {

    public Angle(Color color) {
        super(color);
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(color);
        gr.setLineWidth(5);

        //Горизонтальный отрезок
        gr.strokeLine(150, 100, 250, 100);
        //Вертикальный отрезок
        gr.strokeLine(150, 100, 150, 200);
    }

    @Override
    public String descriptor() {
        return "угол";
    }
}
