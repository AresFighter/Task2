package com.example.task2;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class Controller implements Initializable {
    @FXML
    private ListView<Shape> listView;

    private Stack<Shape> shapeStack = new Stack<>();
    private Stack<Shape> redoStack = new Stack<>();

    @FXML
    private Canvas canvas;

    @FXML
    private TextField sizeField;

    @FXML
    private ColorPicker outlineColorPicker;

    @FXML
    private TextField outlineSizeField;

    @FXML
    private ComboBox<String> fillTypeComboBox;

    @FXML
    private VBox solidColorBox;

    @FXML
    private ColorPicker solidColorPicker;

    @FXML
    private VBox gradientColorBox;

    @FXML
    private ColorPicker gradientColorPicker1;

    @FXML
    private ColorPicker gradientColorPicker2;

    @FXML
    private VBox patternColorBox;

    @FXML
    private CheckBox animationCheckBox;

    private ObservableList<Shape> items;
    private boolean isDrawing = false;
    private double currentSize = 50;
    private double opacity = 1.0;
    private boolean increasing = false;
    private AnimationTimer timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle rectangle = new Rectangle(currentSize, Color.RED);
        Circle circle = new Circle(currentSize, new Color(0, 0, 1, 0.5)); // Создаем синий цвет с 50% прозрачностью
        Square square = new Square(currentSize, Color.GREEN);
        Pentagon pentagon = new Pentagon(currentSize, Color.YELLOW);
        Triangle triangle = new Triangle(currentSize, Color.ORANGE);

        items = FXCollections.observableArrayList(rectangle, circle, square, pentagon, triangle);
        listView.setItems(items);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        fillTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            updateVisibility(newVal);
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onMouseDragged);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onMouseReleased);

        updateVisibility(fillTypeComboBox.getValue());

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateOpacity();
                redrawCanvas();
            }
        };
        timer.start();
    }

    private void updateVisibility(String fillType) {
        solidColorBox.setVisible("solid".equals(fillType));
        gradientColorBox.setVisible("gradient".equals(fillType));
        patternColorBox.setVisible("pattern".equals(fillType));
    }

    private void onMousePressed(MouseEvent event) {
        isDrawing = true;
        drawShape(event);
    }

    private void onMouseDragged(MouseEvent event) {
        if (isDrawing) {
            drawShape(event);
        }
    }

    private void onMouseReleased(MouseEvent event) {
        isDrawing = false;
        redoStack.clear(); // Очищаем redoStack, когда начинаем рисовать новую фигуру
    }

    public void drawShape(MouseEvent event) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {
            Shape selectedShape = items.get(selectedIndex);
            Shape newShape = selectedShape.clone();
            newShape.size = currentSize;

            // Установка цвета заливки после клонирования
            String fillType = fillTypeComboBox.getValue();
            if ("solid".equals(fillType)) {
                newShape.color = solidColorPicker.getValue();
            } else if ("gradient".equals(fillType)) {
                newShape.color = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, gradientColorPicker1.getValue()), new Stop(1, gradientColorPicker2.getValue()));
            } else if ("pattern".equals(fillType)) {
                newShape.color = new ImagePattern(new Image("file:/C:/Учёба/3 курс 5 семестр/java/JL-VSTU/LT-6/src/main/resources/com/example/task6/orig.jpg"));
            }

            // Применение декораторов
            newShape = new ColorOutlineDecorator(newShape, outlineColorPicker.getValue()); // Цвет контура
            newShape = new SizeOutlineDecorator(newShape, Double.parseDouble(outlineSizeField.getText())); // Размер контура

            newShape.x = event.getX();
            newShape.y = event.getY();

            // Устанавливаем значение hasAnimation для новой фигуры
            newShape.setHasAnimation(animationCheckBox.isSelected());

            newShape.draw(canvas.getGraphicsContext2D(), event.getX(), event.getY(), animationCheckBox.isSelected() ? opacity : 1.0);

            shapeStack.push(newShape);
        } else {
            System.out.println("No shape selected.");
        }
    }

    @FXML
    public void applySize() {
        try {
            currentSize = Double.parseDouble(sizeField.getText());
            System.out.println("Size applied: " + currentSize);
        } catch (NumberFormatException e) {
            System.out.println("Invalid size input.");
        }
    }

    public void cleanCan() {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        shapeStack.clear();
        redoStack.clear();
    }

    @FXML
    public void undo() {
        if (!shapeStack.isEmpty()) {
            Shape lastShape = shapeStack.pop();
            redoStack.push(lastShape);
            redrawCanvas();
        }
    }

    @FXML
    public void redo() {
        if (!redoStack.isEmpty()) {
            Shape lastUndoneShape = redoStack.pop();
            shapeStack.push(lastUndoneShape);
            redrawCanvas();
        }
    }

    private void redrawCanvas() {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape shape : shapeStack) {
            shape.draw(gr, shape.getX(), shape.getY(), shape.hasAnimation() ? opacity : 1.0);
        }
    }

    private void updateOpacity() {
        if (increasing) {
            opacity += 0.02;
            if (opacity >= 1.0) {
                opacity = 1.0;
                increasing = false;
            }
        } else {
            opacity -= 0.02;
            if (opacity <= 0.0) {
                opacity = 0.0;
                increasing = true;
            }
        }
    }
}