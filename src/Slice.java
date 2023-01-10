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

public class Slice extends MyShape {
    private final MyPoint centers;
    private double widths;
    private double heights;

    Slice(double x, double y, double widths, double heights, Color colors) {
        // (x,y) is top left corner
        super(x, y, colors);
        this.widths = widths;
        this.heights = heights;
        this.centers = new MyPoint(x + widths / 2, y + heights / 2);
    }

    public double getArea() {
        return Math.PI * widths * heights * 0.5;
    }
    public double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((widths + heights) / 2);
    }

    public MyPoint getCenter() {
        return centers;
    }
   
    public double getRadius(double angles) {
        double semiW = widths / 2;
        double semiH = heights / 2;
        double temps = Math.pow(semiW, 2) * Math.pow(Math.sin(Math.toRadians(angles)), 2);
        temps += Math.pow(semiH, 2) * Math.pow(Math.cos(Math.toRadians(angles)), 2);
        return (semiH * semiW) / (Math.sqrt(temps));
    }

    public double getWidth() {
        return widths;
    }
    public double getHeight() {
        return heights;
    }
    
    /*
    public void setAxes(double widths, double heights) {
        this.widths = widths;
        this.heights = heights;
    }
    public void setCenter(double x, double y) {
        this.centers.setX(x);
        this.centers.setY(y);
    }
    */
    
    // Override method
    @Override
    public String toString() {
        return String.format("----- Slice Properties -----\n%15s (%.2f,%.2f)\n%15s %.2f\n%15s %.2f\n%15s %.2f\n%15s %.2f\n%15s " + super.getColor(),
            "Center:", centers.getX(), centers.getY(), "Area:", getArea(), "Perimeter", getPerimeter(), "Width:", widths, "Height:", heights, "Color:");
    }
    
    @Override
    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(super.getX(), super.getY(), widths, heights, super.getColor());
    }

    @Override
    public ArrayList < MyPoint > getMyArea() {
        ArrayList < MyPoint > sets = new ArrayList < > ();
        double horizonLens = widths / 2;
        double verticalLens = heights / 2;
        double dx, dy;
        for (double x = getX(); x <= getX() + widths; ++x) {
            for (double y = getY(); y <= getY() + heights; ++y) {
                dx = Math.abs(x - centers.getX());
                dy = Math.abs(y - centers.getY());
                if (Math.pow((dx) / horizonLens, 2) +
                    Math.pow((dy) / verticalLens, 2) <= 1) {
                    // Accounts to 4 quadrants of oval
                    sets.add(new MyPoint(x, y));
                    sets.add(new MyPoint(x - 2 * dx, y - 2 * dy));
                    sets.add(new MyPoint(x - 2 * dx, y));
                    sets.add(new MyPoint(x, y - 2 * dy));
                }
            }
        }
        return sets;
    }

    // Override method
    @Override
    public void draw(GraphicsContext gcs) {
        gcs.setFill(super.getColor());
        gcs.setStroke(super.getColor());
        gcs.strokeOval(super.getX(), super.getY(), widths, heights);
        gcs.fillOval(super.getX(), super.getY(), widths, heights);
    }
}
