package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Абстрактный класс, представляющий геометрическую фигуру.
 * Содержит базовые свойства и методы для всех фигур:
 * <ul>
 *     <li>Координаты центра (x, y)</li>
 *     <li>Цвет заливки</li>
 *     <li>Метод отрисовки</li>
 * </ul>
 * Все наследники должны реализовать методы {@link #draw(GraphicsContext)} и {@link #descriptor()}.
 */

public abstract class Shape implements Cloneable {
    // Поля
    private double x;
    private double y;
    private Color color;

    // Конструктор по умолчанию
    public Shape() {
        this.x = 0;
        this.y = 0;
        this.color = Color.BLACK; // Значение по умолчанию
    }

    // Конструктор с параметрами
    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(GraphicsContext gr);

    public abstract String descriptor();

    /**
     * Проверяет, является ли объект пустым (актуально для групп).
     * @return {@code true}, если объект не содержит данных для обработки
     */

    public abstract boolean isEmpty();

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}