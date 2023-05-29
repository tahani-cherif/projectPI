/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Graphique.gui;

import com.tourisme_sante.entities.Client;
import com.tourisme_sante.entities.Utilisateur;
import comm.tourisme_sante.services.serviceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class GestionAdminController implements Initializable {

  
    @FXML
    private Button aj1;
    @FXML
    private Button md2;
    @FXML
    private TableView<Client> tab;
    @FXML
    private TableColumn<Client, String> cl1;
    @FXML
    private TableColumn<Client, String> cl2;
    @FXML
    private TableColumn<Client, String> cl3;
    @FXML
    private TableColumn<Client, String> cl4;
    @FXML
    private TableColumn<Client, String> cl5;
    @FXML
    private TableColumn<Client, Integer> cl6;
    @FXML
    private TableColumn<Client, String> cl7;
    @FXML
    private TableColumn<Client, String> cl8;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField MDP;
    @FXML
    private TextField nb;
    @FXML
    private TextField sx;
    @FXML
    private TextField adr;
    Client Clickedclient=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        cl1.setCellValueFactory(new PropertyValueFactory<Client,String>("Nom"));
        cl2.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        cl3.setCellValueFactory(new PropertyValueFactory<Client,String>("Email"));
        cl4.setCellValueFactory(new PropertyValueFactory<Client,String>("MDP"));
        cl5.setCellValueFactory(new PropertyValueFactory<Client,String>("Role"));
        cl6.setCellValueFactory(new PropertyValueFactory<Client,Integer>("Number"));
        cl7.setCellValueFactory(new PropertyValueFactory<Client,String>("sex"));
        cl8.setCellValueFactory(new PropertyValueFactory<Client,String>("Adresse"));
        
        
          serviceUtilisateur x= new serviceUtilisateur();
          ObservableList<Client> a = FXCollections.observableArrayList();//relation avec base de données
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(x.afficher());
        System.out.println(lc); 
     for(Utilisateur u: lc)
     { 
     if (u instanceof Client) {
      a.add((Client)u);    
     }
         tab.setItems(a); 
     }
 TableColumn<Client, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de Supprimer Client");
                                alert.setHeaderText("Confirmation de Supprimer Client");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       Client data = getTableView().getItems().get(getIndex());//connaitre la ligne sup
                                                    System.out.println("selectedData: " + data);
                                                    x.supprimer(data);
                                                   
                                                   ObservableList<Client> a = FXCollections.observableArrayList();//relation avec base de données
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(x.afficher());// mis a jr base de dn
        System.out.println(lc); 
     for(Utilisateur u: lc)
     { 
     if (u instanceof Client) {
      a.add((Client)u);    
     }
         tab.setItems(a); 
     }
                                
                                } else {
                                    // ... user chose CANCEL or closed the dialog
                                }
                         
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tab.getColumns().add(colBtn);    
    }   

    @FXML
   private void ajouter(ActionEvent event){
     serviceUtilisateur u = new serviceUtilisateur();
     
        u.ajouter(new Client (Integer.parseInt(nb.getText()) , sx.getText(),adr.getText(),nom.getText(),prenom.getText(),email.getText(),MDP.getText(),"client"));
       JOptionPane.showMessageDialog(null, "client ajouté !");
        ObservableList<Client> a = FXCollections.observableArrayList();//relation avec base de données
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(u.afficher());
        System.out.println(lc); 
     for(Utilisateur x: lc)
     { 
     if (x instanceof Client) {
      a.add((Client)x);    
     }
         tab.setItems(a); 
     }
     
    }

    @FXML
    private void modifier(ActionEvent event) {
         serviceUtilisateur u = new serviceUtilisateur();
      Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier Client");
                                alert.setHeaderText("Confirmation de modifier Client");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                     
      u.modifier(new Client (Integer.parseInt(nb.getText()) , sx.getText(),adr.getText(),Clickedclient.getId(),nom.getText(),prenom.getText(),email.getText(),MDP.getText(),"client"));
      
        ObservableList<Client> a = FXCollections.observableArrayList();//relation avec base de données
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(u.afficher());
        System.out.println(lc); 
     for(Utilisateur x: lc)
     { 
     if (x instanceof Client) {
      a.add((Client)x);    
     }
         tab.setItems(a); 
     }
                                
                                } else {
                                    // ... user chose CANCEL or closed the dialog
                                }
                         
                        
      
    }
    

    @FXML
    private void clicked(MouseEvent event) {
        Clickedclient= tab.getSelectionModel().getSelectedItem();
        nom.setText(Clickedclient.getNom());
         prenom.setText(Clickedclient.getPrenom());
          email.setText(Clickedclient.getEmail());
           MDP.setText(Clickedclient.getMDP());
            nb.setText(Integer.toString(Clickedclient.getNumber()));
             sx.setText(Clickedclient.getSex());
              adr.setText(Clickedclient.getAdresse());
        
    }
 
   



    
   
        
    }
    

