package model.memento;

import model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private final List<Shape> shapesSnapshot;

    // Конструктор сохраняет состояние всех фигур
    public Memento(List<Shape> shapes) {
        // Создаем глубокую копию списка фигур
        this.shapesSnapshot = new ArrayList<>();
        for (Shape shape : shapes) {
            this.shapesSnapshot.add((Shape) shape.clone());
        }
    }

    // Метод восстанавливает список фигур
    public void restore(List<Shape> shapes) {
        shapes.clear();
        for (Shape shape : shapesSnapshot) {
            shapes.add((Shape) shape.clone()); // Восстанавливаем копии фигур
        }
    }
}