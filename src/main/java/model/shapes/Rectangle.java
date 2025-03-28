package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(getColor());
        gr.fillRect(getX(), getY(), width, height);
    }

    @Override
    public boolean isEmpty() {
        return false; // Лист никогда не пуст
    }

    @Override
    public String descriptor() {
        return "прямоугольник";
    }
}