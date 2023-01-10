/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author vinuk
 */
import java.util.ArrayList;

public interface MyShapeInterface {
    public abstract MyRectangle getMyBoundingRectangle();
    public abstract ArrayList < MyPoint > getMyArea();
    public ArrayList < MyPoint > overlapMyShapes(MyShape shape);
}

