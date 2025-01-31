package com.example.task2;

import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker {
    private final Deque<Memento> mementoStack = new ArrayDeque<>();

    // Сохраняем состояние
    public void saveState(Memento state) {
        mementoStack.push(state);
    }

    // Откатываем последнее сохранённое состояние
    public Memento retrieveState() {
        return mementoStack.isEmpty() ? null : mementoStack.pop();
    }
}
