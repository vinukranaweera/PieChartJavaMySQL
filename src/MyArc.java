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
import javafx.scene.shape.ArcType;
import java.util.ArrayList;

public class MyArc extends MyShape {
    private final Slice BASE;
    private double startAngles;
    private final double SHIFT;

    // Defines the Start point, which is (x1,y1) and end point is (x2,y2)
    // startAngles and shift are in degrees
    public MyArc(double angs1, double shifts, Slice bases, Color colors) {
        super(bases.getCenter().getX(), bases.getCenter().getY(), colors);
        SHIFT = shifts;
        BASE = bases;
        startAngles = angs1;
        // Keeping the angles between 0 and 360 that is inclusive
        while (startAngles < 0) startAngles += 360;
    }

    // Override method
    @Override
    public MyRectangle getMyBoundingRectangle() {
        return null;
    }

    // Override method
    @Override
    public ArrayList < MyPoint > getMyArea() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("----- Arc Properties -----\n%15s (%.2f,%.2f)\n%15s %.2f\n%15s %.2f\n%15s " + super.getColor(),
            "Center:", BASE.getCenter().getX() - BASE.getWidth() / 2,
            BASE.getCenter().getY() - BASE.getHeight() / 2,
            "Start Angle:", startAngles, "End Angle:", startAngles + SHIFT, "Colors:");
    }

    // Override method
    @Override
    public void draw(GraphicsContext gcs) {
        final double centerXs = BASE.getCenter().getX() - BASE.getWidth() / 2;
        final double centerYs = BASE.getCenter().getY() - BASE.getHeight() / 2;
        gcs.setFill(super.getColor());
        gcs.setStroke(super.getColor());
        gcs.strokeArc(centerXs, centerYs, BASE.getWidth(), BASE.getHeight(), startAngles, SHIFT, ArcType.ROUND);
        gcs.fillArc(centerXs, centerYs, BASE.getWidth(), BASE.getHeight(), startAngles, SHIFT, ArcType.ROUND);
    }
}
