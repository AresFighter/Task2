package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Pentagon extends Shape {
    private double side;

    public Pentagon(double side, Paint color) {
        super(side, color);
        this.side = side;
        this.type = "Pentagon";
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y, double opacity) {
        this.x = x;
        this.y = y;
        gc.setFill(color);
        gc.setGlobalAlpha(opacity); // Устанавливаем прозрачность
        double[] xPoints = new double[5];
        double[] yPoints = new double[5];
        double angle = Math.PI / 180 * 72;
        double radius = side / (2 * Math.sin(Math.PI / 5));

        for (int i = 0; i < 5; i++) {
            xPoints[i] = x + radius * Math.cos(angle * i);
            yPoints[i] = y + radius * Math.sin(angle * i);
        }

        gc.fillPolygon(xPoints, yPoints, 5);
        gc.strokePolygon(xPoints, yPoints, 5); // Добавляем отрисовку контура
        gc.setGlobalAlpha(1.0); // Сбрасываем прозрачность
    }

    @Override
    public String toString() {
        return "Пятиугольник";
    }
}
