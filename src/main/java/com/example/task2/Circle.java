package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Circle extends Shape {
    public Circle(double size, Paint color) { // Изменено на Paint
        super(size, color);
        this.type = "Circle";
    }

    @Override
    public void draw(GraphicsContext gr, double x, double y, double opacity) {
        this.x = x;
        this.y = y;
        double radius = size / 2;
        gr.setFill(color);
        gr.setGlobalAlpha(opacity); // Устанавливаем прозрачность
        gr.fillOval(x - radius, y - radius, size, size);
        gr.strokeOval(x - radius, y - radius, size, size); // Добавляем отрисовку контура
        gr.setGlobalAlpha(1.0); // Сбрасываем прозрачность
    }

    @Override
    public String toString() {
        return "Круг";
    }
}