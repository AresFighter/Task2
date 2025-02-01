package com.example.task2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorOutlineDecorator extends ShapeDecorator {
    private Color outlineColor;

    public ColorOutlineDecorator(Shape decoratedShape, Color outlineColor) {
        super(decoratedShape);
        this.outlineColor = outlineColor;
    }

    @Override
    public void draw(GraphicsContext gr, double x, double y, double opacity) {
        gr.setStroke(outlineColor);
        super.draw(gr, x, y, opacity);
        gr.stroke();
    }
}
