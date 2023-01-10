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
import java.util.HashSet;

// implements MyShapeInterface
public abstract class MyShape implements MyShapeInterface {
    private MyPoint points; // The Point of shape of pixels
    private Color colors;

    MyShape(double x, double y, Color colors) {
        this.points = new MyPoint(x, y);
        this.colors = colors;
    }

    public double getX() {
        return points.getX();
    }
    public double getY() {
        return points.getY();
    }
    public Color getColor() {
        return colors;
    }
    public void setX(int x) {
        this.points.setX(x);
    }
    public void setY(int y) {
        this.points.setY(y);
    }
    public void setColor(Color colors) {
        this.colors = colors;
    }

    //overide method
    @Override
    public ArrayList < MyPoint > overlapMyShapes(MyShape shape) {
        ArrayList < MyPoint > overlaps = new ArrayList < > ();
        HashSet < String > pointStrs = new HashSet < > ();
        for (MyPoint points: shape.getMyArea()) {
            pointStrs.add(points.toString());
        }
        for (MyPoint points: this.getMyArea()) {
            if (pointStrs.contains(points.toString())) {
                overlaps.add(points);
            }
        }
        return overlaps;
    }

    public abstract String toString();
    public abstract void draw(GraphicsContext gc1);
}
