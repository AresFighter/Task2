package com.example.task2;

public interface Subject {
    void notifyAllObservers();   // Уведомить всех наблюдателей
    void attach(Observer obs); // Добавить наблюдателя
    void detach(Observer obs); // Удалить наблюдателя
}