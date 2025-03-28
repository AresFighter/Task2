package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    private double radius;

    public Circle(double x, double y, double radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(getColor());
        gr.fillOval(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
    }

    @Override
    public boolean isEmpty() {
        return false; // Лист никогда не пуст
    }

    @Override
    public String descriptor() {
        return "круг";
    }
}