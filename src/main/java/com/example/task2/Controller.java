package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField fieldsides;
    @FXML
    private Canvas canvas;
    @FXML
    private Label shapeLabel;

    @FXML
    public void addPikcher() {
        GraphicsContext gr = canvas.getGraphicsContext2D();
        String input = fieldsides.getText();

        try {
            int numberOfSides = Integer.parseInt(input);
            ShapeFactory shapeFactory = new ShapeFactory();
            Shape shape = shapeFactory.createShape(numberOfSides);

            if (shape != null) {
                gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                shape.draw(gr);

                shapeLabel.setText("Данная фигура - " + shape.descriptor());
            } else {
                showError("Недопустимое количество сторон!");
            }
        } catch (NumberFormatException e) {
            showError("Введите корректное число.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
