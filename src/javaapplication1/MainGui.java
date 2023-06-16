/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaapplication1;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author 21626
 */
public class MainGui extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../com/tourisme_sante/views/CrudTransport.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Agence");
        primaryStage.show();
        }
             catch(IOException e) {
        e.printStackTrace();
    }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
