package com.example.task2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField timerField; // Поле для ввода интервала
    @FXML
    private TextField repeatField; // Поле для ввода количества повторов
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button resetButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField textField;
    @FXML
    private Button textStartButton;
    @FXML
    private Button textStopButton;
    @FXML
    private Button bellStartButton;
    @FXML
    private Button bellStopButton;
    @FXML
    private Button clockStartButton;
    @FXML
    private Button clockStopButton;
    @FXML
    private Canvas canvas; // Добавляем поле для Canvas

    private TimeServer timeServer;
    private ComponentOne componentText;
    private ComponentTwo componentMusic;
    private ComponentThree componentAnimation;

    @FXML
    public void initialize() {
        timeServer = new TimeServer();
        componentText = new ComponentOne(textField);
        componentMusic = new ComponentTwo();
        componentAnimation = new ComponentThree(canvas.getGraphicsContext2D()); // Передаем GraphicsContext

        timeServer.attach(componentText);
        timeServer.attach(componentMusic);
        timeServer.attach(componentAnimation);
    }

    @FXML
    public void start() {
        try {
            timeServer.start();
            statusLabel.setText("Таймер активен");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Ошибка при запуске таймера: " + e.getMessage());
        }
    }

    @FXML
    public void stop() {
        timeServer.stop();
        statusLabel.setText("Таймер остановлен");
    }

    @FXML
    public void reset() {
        timeServer.reset();
        statusLabel.setText("Таймер сброшен");
    }

    @FXML
    public void startText() {
        componentText.start();
    }

    @FXML
    public void stopText() {
        componentText.stop();
    }

    @FXML
    public void startBell() {
        try {
            int interval = Integer.parseInt(timerField.getText());
            int repeatCount = Integer.parseInt(repeatField.getText());
            componentMusic.start(interval, repeatCount);
        } catch (NumberFormatException e) {
            statusLabel.setText("Ошибка: неверное значение для интервала или количества повторов");
        }
    }

    @FXML
    public void stopBell() {
        componentMusic.stop();
    }

    @FXML
    public void startClock() {
        try {
            int period = Integer.parseInt(timerField.getText()); // Получаем период из текстового поля
            componentAnimation.start(period); // Передаем период в метод start()
        } catch (NumberFormatException e) {
            statusLabel.setText("Ошибка: неверное значение для периода");
        }
    }

    @FXML
    public void stopClock() {
        componentAnimation.stop();
    }

    // Добавляем метод getAnimationRectangle
    public Canvas getAnimationRectangle() {
        return canvas;
    }
}