package com.example.task2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ComponentThree implements Observer {
    private GraphicsContext graphicsContext;
    private boolean isActive = false;
    private Timeline animationTimeline; // Таймлайн для анимации
    private Timeline periodicTimeline; // Таймлайн для периодического повторения
    private int period = 20; // Период по умолчанию (20 секунд)
    private int animationDuration = 7; // Длительность анимации (5 секунд)
    private double petalSize = 0; // Размер лепестков цветка
    private double progress = 0.0; // Прогресс анимации (от 0 до 1)
    private final double progressStep = 0.01; // Шаг прогресса (1% за кадр)

    public ComponentThree(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void update(Subject st) {
        if (isActive) {
            startAnimation();
        }
    }

    private void startAnimation() {
        // Очистка холста
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());

        // Сброс прогресса и размера лепестков
        progress = 0.0;
        petalSize = 0;

        // Создаем Timeline для анимации
        animationTimeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            animate();
        }));
        animationTimeline.setCycleCount(animationDuration * 25); // 20 кадров в секунду * 5 секунд = 100 кадров
        animationTimeline.setOnFinished(event -> {
            // После завершения анимации останавливаем её
            animationTimeline.stop();
        });
        animationTimeline.play();
    }

    private void animate() {
        // Центр холста
        double centerX = graphicsContext.getCanvas().getWidth() / 2;
        double centerY = graphicsContext.getCanvas().getHeight() / 2;

        // Увеличение прогресса
        progress += progressStep;
        if (progress > 1.0) {
            progress = 1.0; // Ограничиваем прогресс до 100%
        }

        // Увеличение размера лепестков
        petalSize = 50 * progress; // Максимальный размер лепестков - 50

        // Рисование цветка
        graphicsContext.setFill(Color.rgb(255, 100, 100)); // Цвет лепестков
        for (int i = 0; i < 8; i++) { // 8 лепестков
            double angle = Math.toRadians(45 * i); // Угол для каждого лепестка
            double x = centerX + Math.cos(angle) * petalSize;
            double y = centerY + Math.sin(angle) * petalSize;
            graphicsContext.fillOval(x - 10, y - 10, 20, 20); // Рисуем лепесток
        }

        // Рисование сердцевины цветка
        graphicsContext.setFill(Color.YELLOW);
        graphicsContext.fillOval(centerX - 20, centerY - 20, 40, 40);
    }

    public void start(int period) {
        this.period = period; // Устанавливаем период
        isActive = true;

        // Создаем Timeline для периодического повторения
        periodicTimeline = new Timeline(new KeyFrame(Duration.seconds(period), event -> {
            startAnimation();
        }));
        periodicTimeline.setCycleCount(Timeline.INDEFINITE);
        periodicTimeline.play();
    }

    public void stop() {
        if (animationTimeline != null) {
            animationTimeline.stop();
        }
        if (periodicTimeline != null) {
            periodicTimeline.stop();
        }
        isActive = false;
    }
}