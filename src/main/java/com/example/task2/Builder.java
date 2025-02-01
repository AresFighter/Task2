package com.example.task2;

public interface Builder {
    void reset(); // Сбрасывает текущее состояние
    void setIndicator(int totalSlides, int currentSlide); // Устанавливает параметры индикатора
    void linePaint(int currentSlide);
    Indicator build(); // Создает объект индикатора
}
