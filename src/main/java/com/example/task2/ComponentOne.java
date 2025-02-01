package com.example.task2;

import javafx.scene.control.TextField;

public class ComponentOne implements Observer {
    private TextField textField;
    private boolean isActive = false;
    private int currentTime = 0; // Внутренний счетчик времени

    public ComponentOne(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void update(Subject st) {
        if (isActive) {
            currentTime++; // Увеличиваем счетчик времени
            if (textField != null) {
                textField.setText("Прошло " + currentTime + " с"); // Выводим текущее время
            }
        }
    }

    public void start() {
        isActive = true;
    }

    public void stop() {
        isActive = false;
    }
}