/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinuk
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class MyRectangle extends MyShape {
    private double widths;
    private double heights;

    // (x,y) Top Left Corners
    MyRectangle(double x, double y, double widths, double heights, Color colors) {
        super(x, y, colors);
        this.widths = widths;
        this.heights = heights;
    }

    public double getWidth() {
        return widths;
    }
    public double getHeight() {
        return heights;
    }
    public double getPerimeter() {
        return 2 * (widths + heights);
    }
    private double getArea() {
        return widths * heights;
    }

    public void setWidth(int widths) {
        this.widths = widths;
    }
    public void setHeight(int heights) {
        this.heights = heights;
    }

    @Override
    public void draw(GraphicsContext gc1) {
        gc1.setFill(super.getColor());
        gc1.setStroke(super.getColor());
        gc1.strokeRect(super.getX(), super.getY(), widths, heights);
        gc1.fillRect(super.getX(), super.getY(), widths, heights);
    }
    @Override
    public MyRectangle getMyBoundingRectangle() {
        return this;
    }
    @Override
    public ArrayList < MyPoint > getMyArea() {
        ArrayList < MyPoint > sets = new ArrayList < > ();
        // to Picks up the points from every column and the row within rectangle, starting from top left to bottom right
        for (double x = super.getX(); x <= super.getX() + widths; ++x) {
            for (double y = super.getY(); y <= super.getY() + heights; ++y) {
                sets.add(new MyPoint(x, y));
            }
        }
        return sets;
    }

    @Override
    public String toString() {
        return String.format("----- The Rectangle Properties -----\n%15s (%.2f,%.2f)\n%15s %.2f\n%15s %.2f\n%15s %.2f\n%15s %.2f\n%15s " + super.getColor(),
            "Corner Point:", super.getX(), super.getY(), "Area:", getArea(), "Perimeter:", getPerimeter(), "Width:", widths, "Height:", heights, "Colors:");
    }
}
