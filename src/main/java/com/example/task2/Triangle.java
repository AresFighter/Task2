package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {

    private final double alpha; // Смещение вершины вправо
    private final double beta;  // Смещение вершины влево
    private final double omega; // Высота треугольника

    public Triangle(double x, double y, double alpha, double beta, double omega, Color color) {
        super(x, y, color);
        this.alpha = alpha;
        this.beta = beta;
        this.omega = omega;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(color);

        // Определяем координаты трёх точек
        double[] xPoints = {x, x + alpha, x - beta};
        double[] yPoints = {y, y + omega, y + omega};

        gr.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String descriptor() {
        return "треугольник";
    }
}
