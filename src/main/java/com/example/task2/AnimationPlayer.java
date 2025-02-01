package com.example.task2;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class AnimationPlayer implements Observer {
    private final Subject subject;
    private Controller controller;
    private int interval;
    private int currentTime = 0; // Внутренний счётчик времени

    public AnimationPlayer(Subject subject, Controller controller) {
        this.subject = subject;
        this.controller = controller;
    }

    public void startAnimationEvery(int interval) {
        this.interval = interval;
    }

    @Override
    public void update(Subject st) {
        currentTime++; // Увеличиваем счётчик времени
        if (currentTime % interval == 0) {
            animateRectangle();
        }
    }

    private void animateRectangle() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), controller.getAnimationRectangle());
        transition.setByX(200);
        transition.setCycleCount(2);
        transition.setAutoReverse(true);
        transition.play();
    }
}