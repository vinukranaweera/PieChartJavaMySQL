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

public class MyPieChart {

    private double x;
    private double y;
    private double width;
    private double height;
    private int NumberOfStat = 6; // A B C D F W are the grades we want
    ConnectData db = new ConnectData();  
    int []result = new int[NumberOfStat];// 6 letter grades



    public MyPieChart(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void dataArray() {

        int z = 1;

        for (int j = 0; j < db.getNumberOfStudent(); j++) {
            System.out.println("Student " + z + " grade: " + db.storage[j]);  // see each student's grade
            z++;
        }
    }



    public void draw(GraphicsContext gc)
    {
        int totalStudent = db.getNumberOfStudent();
        String stringTotal =Integer.toString(totalStudent);

        Color[] color = new Color[6];
        //A green, B blue, C orange, D magenta, F red, and W black
        color[0] = Color.GREEN;
        color[1] = Color.BLUE;
        color[2] = Color.ORANGE;
        color[3] = Color.MAGENTA;
        color[4] = Color.RED;
        color[5] = Color.BLACK;

        String s;
        for(int i = 0; i<db.getNumberOfStudent();i++) // ensures each letter grade is in order
        {
            s = db.storage[i];
            if( s.equals("A") )
            {
                result[0] ++;
            }
            else if (s.equals("B"))
            {
                result[1] ++;
            }
            else if(s.equals("C"))
            {
                result[2]++;
            }
            else if(s.equals("D"))
            {
                result[3]++;
            }
            else if(s.equals("F"))
            {
                result[4]++;
            }
            else if(s.equals("W"))
            {
                result[5]++;
            }

        }
        String[] stringResult = new String[NumberOfStat];
        for(int j = 0; j<NumberOfStat;j++)
        {
            stringResult[j] = String.valueOf(result[j]);
        }


    float []prob = new float[NumberOfStat];
        for(int i = 0; i<NumberOfStat;i++)
        {
            float nums = db.getNumberOfStudent(); 
            prob[i] = result[i]/nums;
            System.out.println(prob[i]);
        }



        String[] stringProb = new String[NumberOfStat];
        for(int j = 0; j<NumberOfStat;j++)
        {
            stringProb[j] = String.valueOf(prob[j]);
        }


        //grades

        String[] letter= new String[NumberOfStat];
        letter[0] = "A";
        letter[1] = "B";
        letter[2] = "C";
        letter[3] = "D";
        letter[4] = "F";
        letter[5] = "W";


        int startAngle = 90;
        int squareW = 10;
        int squareH = 10;
        for(int j = 0; j < NumberOfStat; j++)
        {
            gc.setFill(color[j]);
            gc.fillArc(x,y,width,height,startAngle,-(prob[j]*360), ArcType.ROUND);
            startAngle-= (prob[j]*360);
            gc.fillRect(x/20,y/2*j,squareW,squareH);
            gc.fillText(stringResult[j]+" Students: ",x/9,y/2*j+squareH);
            gc.fillText("      "+letter[j],x/3,y/2*j+squareH);
            gc.fillText(stringProb[j],x/2,y/2*j+squareH);
        }

        gc.setFill(Color.WHITE);
    }
}
