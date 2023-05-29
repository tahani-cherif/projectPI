/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
 * @author hamza
 */
public class MainGUI extends Application {
    
    //@Override
   // public void start(Stage primaryStage) throws IOException {
   //     FXMLLoader loader = new FXMLLoader(getClass().getResource("../Graphique/gui/AjouterClient.fxml"));
       // Parent root = loader.load();
       // Scene scene = new Scene(root);
        //primaryStage.setScene(scene);
        
        //primaryStage.setTitle("PIDEV");
       // primaryStage.show();
         @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Graphique/gui/Choice.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("PIDEV");
        primaryStage.show();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    
}

    

