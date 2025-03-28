package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Plus extends Shape {
    private double delta; // Размер лучей плюса

    public Plus(double x, double y, double delta, Color color) {
        super(x, y, color);
        this.delta = delta;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(getColor());
        gr.setLineWidth(2);

        // Вертикальная линия
        gr.strokeLine(getX(), getY() - delta, getX(), getY() + delta);

        // Горизонтальная линия
        gr.strokeLine(getX() - delta, getY(), getX() + delta, getY());
    }

    @Override
    public boolean isEmpty() {
        return false; // Лист никогда не пуст
    }

    @Override
    public String descriptor() {
        return "плюс";
    }
}