# Task2. Фабричный метод

## Описание
Это приложение позволяет пользователю рисовать различные геометрические фигуры на холсте на основе введенного числа сторон. Пользователь вводит количество сторон фигуры, и программа автоматически рисует соответствующую фигуру:
- **0** - круг;
- **1** - отрезок;
- **2** - прямой угол;
- **3** - треугольник;
- **4** - квадрат;
- **5** - пятиугольник.

## Возможности

- Рисование фигур по введенному числу сторон;
- Вывод ниаменования каждой фигуры после её создания;
- Обработка ошибкод ввода (например, если введено недопустимое число или текст).

## Используемые технологии

- **JavaFX** - для графического интерфейса и рисования фигур на холсте;
- Паттерн [**Фабричный метод**](https://sites.google.com/view/study-pattern/%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F/%D0%B7%D0%B0%D0%B4%D0%B0%D1%87%D0%B8/task-2-%D1%84%D0%B0%D0%B1%D1%80%D0%B8%D1%87%D0%BD%D1%8B%D0%B9-%D0%BC%D0%B5%D1%82%D0%BE%D0%B4?authuser=0) для создания объектов различных фигур.

## Результат 

![Вывод на экран тругоника](https://github.com/AresFighter/Task2/blob/master/%D0%A0%D0%B5%D0%B7%D1%83%D0%BB%D1%8C%D1%82%D0%B0%D1%82_task2.png)

## Архитектура
![Вывод на экран Диаграммы Классов](https://github.com/AresFighter/Task2/blob/master/ClassDiagram_task2.png)

