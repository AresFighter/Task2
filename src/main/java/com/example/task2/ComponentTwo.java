package com.example.task2;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class ComponentTwo implements Observer {
    private int interval; // Интервал, задаваемый пользователем
    private int repeatCount; // Количество повторов
    private MediaPlayer mediaPlayer;
    private int lastPlayedTime = 0; // Время последнего воспроизведения
    private boolean isActive = false;
    private int playCount = 0; // Счетчик воспроизведений
    private int currentTime = 0; // Текущее время

    public ComponentTwo() {
        try {
            File file = new File("src/main/resources/com/example/task2/vivo.mp3");
            Media sound = new Media(file.toURI().toString());
            this.mediaPlayer = new MediaPlayer(sound);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка загрузки медиафайла: " + e.getMessage());
        }
    }

    @Override
    public void update(Subject st) {
        if (isActive) {
            currentTime++; // Увеличиваем текущее время

            // Проверяем, прошло ли достаточно времени с последнего воспроизведения
            if (currentTime - lastPlayedTime >= interval && playCount < repeatCount) {
                mediaPlayer.stop(); // Останавливаем, если воспроизводился ранее
                mediaPlayer.seek(Duration.ZERO); // Перематываем на начало
                mediaPlayer.play(); // Запускаем воспроизведение

                lastPlayedTime = currentTime; // Обновляем время последнего воспроизведения
                playCount++; // Увеличиваем счётчик запусков
            }

            // Если воспроизведения завершились, отключаем активность
            if (playCount >= repeatCount) {
                stop();
            }
        }
    }

    public void start(int interval, int repeatCount) {
        this.interval = interval;
        this.repeatCount = repeatCount;
        this.playCount = 0; // Сбрасываем счетчик воспроизведений
        this.lastPlayedTime = 0; // Сбрасываем время последнего воспроизведения
        this.currentTime = 0; // Сбрасываем текущее время
        isActive = true;
    }

    public void stop() {
        mediaPlayer.stop();
        isActive = false;
    }
}