/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaapplication1;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class MainGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    // FXMLLoader loader = new FXMLLoader(getClass().getResource("../comm/tourisme_sante/gui/planing.fxml"));
     FXMLLoader loader = new FXMLLoader(getClass().getResource("../comm/tourisme_sante/gui/Login.fxml"));
         // FXMLLoader loader = new FXMLLoader(getClass().getResource("../comm/tourisme_sante/gui/ReservationGui.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Tourisme sante");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
