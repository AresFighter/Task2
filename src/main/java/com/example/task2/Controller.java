package com.example.task2;

import model.factory.ShapeFactory;
import model.memento.Caretaker;
import model.memento.Memento;
import model.shapes.Shape;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.shapes.ShapeGroup;

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
    private final List<Shape> shapes = new ArrayList<>(); // Все фигуры на холсте
    private Shape selectedShape; // Выбранная фигура для рисования
    private boolean isSelecting = false; // Флаг для режима выделения
    private double selectStartX, selectStartY; // Начальные координаты выделения
    private final ShapeGroup selectedShapes = new ShapeGroup(); // Выделенные фигуры (группа)
    private boolean isDrawingMode = true; // По умолчанию режим рисования
    private double dragStartX, dragStartY; // Начальные координаты мыши при перемещении
    private List<Double> shapesStartX = new ArrayList<>(); // Начальные координаты X выделенных фигур
    private List<Double> shapesStartY = new ArrayList<>(); // Начальные координаты Y выделенных фигур
    private boolean isDrawingLine = false; // Флаг для режима рисования линии

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
    public void onMousePressed(MouseEvent event) {
        if (isDrawingMode) { // Если режим рисования
            if (event.isPrimaryButtonDown()) { // Если нажата левая кнопка мыши
                isDrawingLine = true; // Начинаем рисование линии
                drawShape(event.getX(), event.getY()); // Рисуем первую фигуру
            }
        } else { // Режим выделения
            if (event.isPrimaryButtonDown()) { // Если нажата левая кнопка мыши
                if (!selectedShapes.isEmpty()) { // Если есть выделенные фигуры
                    // Запоминаем начальные координаты мыши и фигур
                    dragStartX = event.getX();
                    dragStartY = event.getY();
                    shapesStartX.clear();
                    shapesStartY.clear();
                    for (Shape shape : selectedShapes.getShapes()) {
                        shapesStartX.add(shape.getX());
                        shapesStartY.add(shape.getY());
                    }
                }
            } else if (event.isSecondaryButtonDown()) { // Если нажата правая кнопка мыши, начинаем выделение
                isSelecting = true;
                selectStartX = event.getX();
                selectStartY = event.getY();
            }
        }
    }

    @FXML
    public void onMouseDragged(MouseEvent event) {
        if (isDrawingMode) { // Если режим рисования
            if (isDrawingLine && event.isPrimaryButtonDown()) { // Если рисуем линию и нажата левая кнопка мыши
                drawShape(event.getX(), event.getY()); // Рисуем фигуру вдоль траектории мыши
            }
        } else { // Режим выделения
            if (!selectedShapes.isEmpty() && !shapesStartX.isEmpty()) { // Если есть выделенные фигуры и начальные координаты
                // Вычисляем смещение мыши
                double offsetX = event.getX() - dragStartX;
                double offsetY = event.getY() - dragStartY;

                // Обновляем координаты выделенных фигур
                int i = 0;
                /*for (Shape shape : selectedShapes.getShapes()) {
                    /*shape.x = shapesStartX.get(i) + offsetX;
                    shape.y = shapesStartY.get(i) + offsetY;*/
                    /*shape.setX(shapesStartX.get(i) + offsetX);
                    shape.setY(shapesStartY.get(i) + offsetY);
                    i++;
                }*/
                // В методе onMouseDragged
                if (!selectedShapes.isEmpty()) {
                    for (Shape shape : selectedShapes.getShapes()) {
                        if (shape.isEmpty()) continue; // Пропускаем пустые группы
                        if (i < shapesStartX.size() && i < shapesStartY.size()) { // Проверка индекса
                            shape.setX(shapesStartX.get(i) + offsetX);
                            shape.setY(shapesStartY.get(i) + offsetY);
                        }
                        i++;
                    }
                }

                // Перерисовываем холст
                redrawCanvas();
            } else if (isSelecting) { // Если идет выделение
                // Рисуем прямоугольник выделения
                GraphicsContext gr = canvas.getGraphicsContext2D();
                gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                redrawCanvas();
                gr.setStroke(Color.BLUE);
                gr.strokeRect(selectStartX, selectStartY, event.getX() - selectStartX, event.getY() - selectStartY);
            }
        }
    }

    @FXML
    public void onMouseReleased(MouseEvent event) {
        if (isDrawingMode) { // Если режим рисования
            if (isDrawingLine) { // Если завершено рисование линии
                isDrawingLine = false; // Завершаем рисование линии
            }
        } else { // Режим выделения
            if (isSelecting) { // Если завершено выделение
                isSelecting = false;
                selectShapesInArea(selectStartX, selectStartY, event.getX(), event.getY());
                redrawCanvas();
            } else if (!selectedShapes.isEmpty()) { // Если завершено перемещение
                // Сохраняем состояние после перемещения
                caretaker.saveState(new Memento(shapes));

                // Очищаем выделение после перемещения
                selectedShapes.clear();
                redrawCanvas(); // Перерисовываем холст без выделения
            }
        }
    }

    @FXML
    public void toggleMode() {
        isDrawingMode = !isDrawingMode;
        shapeLabel.setText(isDrawingMode ? "Режим: Рисование" : "Режим: Выделение");
    }

    // Выбор фигур в область
    private void selectShapesInArea(double startX, double startY, double endX, double endY) {
        selectedShapes.clear();
        double minX = Math.min(startX, endX);
        double maxX = Math.max(startX, endX);
        double minY = Math.min(startY, endY);
        double maxY = Math.max(startY, endY);

        for (Shape shape : shapes) {
            if (shape.getX() >= minX && shape.getX() <= maxX && shape.getY() >= minY && shape.getY() <= maxY) {
                try {
                    selectedShapes.addShape(shape);
                } catch (UnsupportedOperationException e) {
                    showError("Нельзя добавить фигуру в одиночный объект");
                }
            }
        }
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
            shapeToDraw.setX(x);
            shapeToDraw.setY(y);
            shapeToDraw.setColor(selectedColor);
            shapeToDraw.draw(gr);

            // Сохраняем текущее состояние
            caretaker.saveState(new Memento(shapes));
            shapes.add(shapeToDraw);
        } else {
            showError("Ошибка рисования фигуры.");
        }
    }

    @FXML
    public void undo() {
        // Откат последнего действия
        Memento memento = caretaker.retrieveState();
        if (memento != null) {
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
        // Подсветка выделенных фигур (только если есть выделенные фигуры)
        if (!selectedShapes.isEmpty()) {
            for (Shape shape : selectedShapes.getShapes()) {
                if (shape.isEmpty()) continue; // Пропускаем пустые группы
                gr.setStroke(Color.RED);
                gr.strokeRect(shape.getX() - 2, shape.getY() - 2, 10, 10); // Рисуем рамку вокруг выделенных фигур
            }
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