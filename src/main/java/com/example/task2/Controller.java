package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private TextField fieldShape;
    @FXML
    private Canvas canvas;
    @FXML
    private Label shapeLabel;
    @FXML
    private ColorPicker colorPicker;

    private String selectedShape;  // Хранение выбранной фигуры

    @FXML
    public void onShapeSelected() {
        String shapeType = fieldShape.getText().trim();
        if (shapeType.isEmpty()) {
            showError("Пожалуйста, введите название фигуры.");
            return;
        }

        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.createShape(shapeType, 0, 0, Color.BLACK); // Пробуем создать фигуру

        if (shape != null) {
            selectedShape = shapeType;  // Сохраняем выбранную фигуру
            shapeLabel.setText("Выбрана фигура: " + shape.descriptor());  // Показываем описание фигуры
        } else {
            showError("Неверный тип фигуры! Попробуйте снова.");
        }
    }

    @FXML
    public void onMousePressed(javafx.scene.input.MouseEvent event) {
        drawShape(event.getX(), event.getY());
    }

    @FXML
    public void onMouseDragged(javafx.scene.input.MouseEvent event) {
        drawShape(event.getX(), event.getY());
    }

    @FXML
    public void onMouseReleased(javafx.scene.input.MouseEvent event) {
        // Завершаем рисование
    }

    private void drawShape(double x, double y) {
        if (selectedShape == null || selectedShape.isEmpty()) {
            showError("Сначала выберите фигуру!");
            return;
        }

        GraphicsContext gr = canvas.getGraphicsContext2D();
        Color selectedColor = colorPicker.getValue();

        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.createShape(selectedShape, x, y, selectedColor);

        if (shape != null) {
            shape.draw(gr);
        } else {
            showError("Не удалось нарисовать фигуру. Попробуйте снова.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void cleanCan() {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}
