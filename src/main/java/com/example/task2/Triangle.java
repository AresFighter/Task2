package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Triangle extends Shape {
    public Triangle(double size, Paint color) {
        super(size, color);
        this.type = "Triangle";
    }

    @Override
    public void draw(GraphicsContext gr, double x, double y, double opacity) {
        this.x = x;
        this.y = y;
        gr.setFill(color);
        gr.setGlobalAlpha(opacity);
        double[] xPoints = new double[3];
        double[] yPoints = new double[3];
        xPoints[0] = x;
        yPoints[0] = y - size / 2;
        xPoints[1] = x - size / 2;
        yPoints[1] = y + size / 2;
        xPoints[2] = x + size / 2;
        yPoints[2] = y + size / 2;
        gr.fillPolygon(xPoints, yPoints, 3);
        gr.strokePolygon(xPoints, yPoints, 3); // Добавляем отрисовку контура
        gr.setGlobalAlpha(1.0); // Сбрасываем прозрачность
    }

    @Override
    public String toString() {
        return "Треугольник";
    }
}
