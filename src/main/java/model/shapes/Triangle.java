package model.shapes;

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
        gr.setFill(getColor());

        // Определяем координаты трёх точек
        double[] xPoints = {getX(), getX() + alpha, getX() - beta};
        double[] yPoints = {getY(), getY() + omega, getY() + omega};

        gr.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean isEmpty() {
        return false; // Лист никогда не пуст
    }

    @Override
    public String descriptor() {
        return "треугольник";
    }
}