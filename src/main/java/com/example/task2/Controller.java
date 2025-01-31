package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private ListView<String> shapeListView;
    @FXML
    private Canvas canvas;
    @FXML
    private Label shapeLabel;
    @FXML
    private ColorPicker colorPicker;

    private final ShapeFactory factory = new ShapeFactory();
    private final Caretaker caretaker = new Caretaker();
    private final List<Shape> shapes = new ArrayList<>();
    private Shape selectedShape;

    @FXML
    public void initialize() {
        // Заполняем ListView доступными фигурами
        shapeListView.getItems().addAll(factory.getAllShapes().keySet());

        // Устанавливаем слушатель выбора
        shapeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                onShapeSelected(newValue);
            }
        });
    }

    public void onShapeSelected(String shapeType) {
        selectedShape = factory.getShape(shapeType); // Клонируем объект
        if (selectedShape != null) {
            shapeLabel.setText("Выбрана фигура: " + selectedShape.descriptor());
        } else {
            showError("Ошибка выбора фигуры.");
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

    }

    private void drawShape(double x, double y) {
        if (selectedShape == null) {
            showError("Сначала выберите фигуру!");
            return;
        }

        GraphicsContext gr = canvas.getGraphicsContext2D();
        Color selectedColor = colorPicker.getValue();

        // Клонируем выбранную фигуру и задаем координаты
        Shape shapeToDraw = (Shape) selectedShape.clone();
        if (shapeToDraw != null) {
            shapeToDraw.x = x;
            shapeToDraw.y = y;
            shapeToDraw.color = selectedColor;
            shapeToDraw.draw(gr);

            // Сохраняем текущее состояние
            //caretaker.saveState(new Memento(shapeToDraw));
            caretaker.saveState(new Memento(shapes));
            shapes.add(shapeToDraw);
        } else {
            showError("Ошибка рисования фигуры.");
        }
    }

    @FXML
    public void undo() {// Откат последнего действия
        Memento memento = caretaker.retrieveState();
        if (memento != null) {
            //shapes.remove(memento.getShape());
            memento.restore(shapes); // Восстанавливаем состояние списка фигур
            redrawCanvas();
        } else {
            showError("Больше нечего отменять.");
        }
    }

    private void redrawCanvas() {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapes) {
            shape.draw(gr);
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
        gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}