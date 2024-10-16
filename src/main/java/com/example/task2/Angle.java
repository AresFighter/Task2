package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle implements Shape {

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(Color.BLACK);
        gr.setLineWidth(5);

        //Горизонтальный отрезок
        gr.strokeLine(150, 100, 250, 100);
        //Вертикальный отрезок
        gr.strokeLine(150, 100, 150, 200);
    }

    @Override
    public String descriptor() {
        return "Angle";
    }
}
