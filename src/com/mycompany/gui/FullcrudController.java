/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.agence_medicale.TypeInterventions;
import com.mycompany.service.Service_TypeInterventions;
import com.mycompany.utils.DataSourceA;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class FullcrudController implements Initializable {
     private Connection cnx = DataSourceA.getInstance().getCnx();
     Service_TypeInterventions sc= new  Service_TypeInterventions();
    int index=-1;
    int id=0;
    TypeInterventions tt=null;
    


    @FXML
    private TextField nomField;
    @FXML
    private TextField descField;
    @FXML
    private TableView<TypeInterventions> tb;
   
    @FXML
    private TableColumn<TypeInterventions, String> colNom;
    @FXML
    private TableColumn<TypeInterventions, String> colDesc;
    
    
   
  
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTypeInterventions();
    }    
    
  
    public void showTypeInterventions(){
        
        ObservableList<TypeInterventions> listType = FXCollections.observableArrayList(sc.afficher());
       
        colNom.setCellValueFactory(new PropertyValueFactory<TypeInterventions, String>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<TypeInterventions, String>("deescripition"));
        tb.setItems(listType);
    }
    
    
//    private void executeQuery(String query) {
//     Connection cnx = DataSourceA.getInstance().getCnx();
//     Statement st;
//     try{
//         st= cnx.createStatement();
//         st.executeQuery(query);
//     }catch(Exception ex){
//         ex.printStackTrace();
//     }
    
    
    

    @FXML
    private void AjouterType(ActionEvent event) {
        
         
          sc.ajouter(new TypeInterventions(nomField.getText(),descField.getText()));
        JOptionPane.showMessageDialog(null, "Type Intervention ajoutée !");
         showTypeInterventions();
       
        
    }
    @FXML
    private void detailsTypeInterventions(MouseEvent event) throws IOException {
        index=tb.getSelectionModel().getSelectedIndex();
        
        System.out.println(index);
        if(index<=-1)
            return;
        tt =tb.getSelectionModel().getSelectedItem();
        
        System.out.println(tt);
        id=tt.getId(); //recupere id element selectioné
        System.out.println("id : "+ id);
        
        
        nomField.setText(colNom.getCellData(index));
        descField.setText(colDesc.getCellData(index));

                }
    @FXML
    private void ModifierType(ActionEvent event) {
         sc.modifier(new TypeInterventions(id,nomField.getText(),descField.getText()));
          showTypeInterventions();
    }

    @FXML
    private void SupprimerType(ActionEvent event) {
         sc.supprimer(tt);
          showTypeInterventions();
    }
    }

    

