/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Assignment 4");
        BorderPane root = new BorderPane();
        Canvas cvs = new Canvas(700,500);
        GraphicsContext gc = cvs.getGraphicsContext2D();

        int width = 250;
        int height = 250;
        
        MyPieChart chart = new MyPieChart(cvs.getWidth()/2-width/2,cvs.getHeight()/2-height/2,width,height);

        chart.dataArray();
        chart.draw(gc);

        Scene scene = new Scene(root,700,500);
        root.getChildren().add(cvs);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}