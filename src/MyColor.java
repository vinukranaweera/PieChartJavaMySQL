/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinuk
 */
import javafx.scene.paint.Color;

public enum MyColor {
    //required colors defined
    RED(255, 0, 0, 255), BLUE(0, 0, 255, 255),
        LIME(0, 255, 0, 255), CYAN(0, 255, 255, 255),
        GREEN(0, 128, 0, 255), GREY(128, 128, 128, 255),
        MAGENTA(255, 0, 255, 255), PURPLE(128, 0, 128, 255),
        VIOLET(148, 0, 211, 255), YELLOW(255, 255, 0, 255),
        WHITE(255, 255, 255, 255), BLACK(0, 0, 0, 255),
        HOTPINK(255, 105, 180, 255), ORANGE(255, 165, 0, 255),
        LIGHTGREY(200, 200, 200, 255);

    private int r1, g1, b1, a1; // Value is defined for green, red ,blue, and opacity

    MyColor(int r1, int g1, int b1, int a1) {
        this.r1 = r1;
        this.g1 = g1;
        this.b1 = b1;
        this.a1 = a1;
    }
    public Color getCol() {
        return Color.rgb(r1, g1, b1, (double)(a1 / 255));
    }
}

