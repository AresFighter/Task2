package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Rectangle extends Shape {
    public Rectangle(double size, Paint color) {
        super(size, color);
        this.type = "Rectangle";
    }

    @Override
    public void draw(GraphicsContext gr, double x, double y, double opacity) {
        this.x = x;
        this.y = y;
        gr.setFill(color);
        gr.setGlobalAlpha(opacity); // Устанавливаем прозрачность
        gr.fillRect(x - size / 2, y - size / 2, size * 1.5, size);
        gr.strokeRect(x - size / 2, y - size / 2, size * 1.5, size); // Добавляем отрисовку контура
        gr.setGlobalAlpha(1.0); // Сбрасываем прозрачность
    }

    @Override
    public String toString() {
        return "Прямоугольник";
    }
}
