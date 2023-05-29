/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;


import com.mycompany.agence_medicale.TypeInterventions;
import com.mycompany.service.Service_TypeInterventions;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.activation.DataSource;
import com.mycompany.utils.DataSourceA;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class AjouterTypeInterventionsController implements Initializable {
     private Connection cnx = DataSourceA.getInstance().getCnx();

    @FXML
    private TextField id;
    @FXML
    private TextField nomF;
    @FXML
    private TextField desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      }    

    @FXML
    private void AjouterTypeInterventions(ActionEvent event) throws IOException{
        Service_TypeInterventions sc= new  Service_TypeInterventions();
          sc.ajouter(new TypeInterventions(nomF.getText(),desc.getText()));
        JOptionPane.showMessageDialog(null, "Type Intervention ajoutée !");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherTypeInterventions.fxml"));
        Parent root = loader.load();
        nomF.getScene().setRoot(root);
       
        
        
    }
    
}
