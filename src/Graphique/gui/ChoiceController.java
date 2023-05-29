/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Graphique.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class ChoiceController implements Initializable {

    @FXML
    private Label idch;
    @FXML
    private Button idcl;
    @FXML
    private Button idad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    

        
    

    @FXML
    private void client(ActionEvent event)throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionClient.fxml"));//cget les comp gestion client
        Parent root = loader.load();// creation scene
        idcl.getScene().setRoot(root);// ==
        
    }

    @FXML
    private void admin(ActionEvent event)throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdminis.fxml"));
        Parent root = loader.load();
        idad.getScene().setRoot(root);
    }

   
    
} 

