package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup extends Shape {
    private final List<Shape> shapes = new ArrayList<>();

    // Добавление фигуры в группу
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    // Удаление фигуры из группы
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    // Проверка, пуста ли группа
    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    // Получение списка фигур в группе
    public List<Shape> getShapes() {
        return shapes;
    }

    // Очистка группы
    public void clear() {
        shapes.clear();
    }

    // Отрисовка всех фигур в группе
    /*@Override
    public void draw(GraphicsContext gr) {
        for (Shape shape : shapes) {
            shape.draw(gr);
        }
    }*/
    @Override
    public void draw(GraphicsContext gr) {
        if (isEmpty()) return; // Не рисуем пустую группу
        for (Shape shape : shapes) {
            shape.draw(gr);
        }
    }

    // Описание группы
    @Override
    public String descriptor() {
        return "Группа фигур";
    }

    // Клонирование группы (глубокая копия)
    @Override
    public Object clone() {
        ShapeGroup clonedGroup = new ShapeGroup();
        for (Shape shape : shapes) {
            clonedGroup.addShape((Shape) shape.clone());
        }
        return clonedGroup;
    }
}