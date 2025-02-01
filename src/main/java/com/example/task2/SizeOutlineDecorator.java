package com.example.task2;

import javafx.scene.canvas.GraphicsContext;

public class SizeOutlineDecorator extends ShapeDecorator{
    private double outlineSize;

    public SizeOutlineDecorator(Shape decoratedShape, double outlineSize) {
        super(decoratedShape);
        this.outlineSize = outlineSize;
    }

    @Override
    public void draw(GraphicsContext gr, double x, double y, double opacity) {
        gr.setLineWidth(outlineSize);
        super.draw(gr, x, y, opacity);
        gr.stroke();
    }
}