/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Graphique.gui;

import com.tourisme_sante.entities.Admin;
import com.tourisme_sante.entities.Utilisateur;
import comm.tourisme_sante.services.serviceUtilisateur;
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
public class GestionAdminisController implements Initializable {

  
    @FXML
    private TextField idf1;
    @FXML
    private TextField idf2;
    @FXML
    private TextField idf3;
    @FXML
    private TextField idf4;
    @FXML
    private Button ajt;
    @FXML
    private Button md;
    @FXML
    private TableView<Admin> tab2;
    @FXML
    private TableColumn<Admin, String> cln1;
    @FXML
    private TableColumn<Admin, String> cln2;
    @FXML
    private TableColumn<Admin, String> cln3;
    @FXML
    private TableColumn<Admin, String> cln4;
    @FXML
    private TableColumn<Admin, String> cln5;
    Admin ClickedAd=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviceUtilisateur x= new serviceUtilisateur();
          ObservableList<Admin> a = FXCollections.observableArrayList();//relation avec base de données
          System.out.println(a);
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(x.afficher());
        
        cln1.setCellValueFactory(new PropertyValueFactory<Admin,String>("nom"));
        cln2.setCellValueFactory(new PropertyValueFactory<Admin,String>("prenom"));
        cln3.setCellValueFactory(new PropertyValueFactory<Admin ,String>("email"));
        cln4.setCellValueFactory(new PropertyValueFactory<Admin,String>("MDP"));
        cln5.setCellValueFactory(new PropertyValueFactory<Admin,String>("role"));
      
        
          
//        System.out.println(lc); 
     for(Utilisateur u: lc)
     { 
            if (u instanceof Admin) {
             a.add((Admin)u);    
            }
         tab2.setItems(a); 
     }
 TableColumn<Admin, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<Admin, Void>, TableCell<Admin, Void>> cellFactory = new Callback<TableColumn<Admin, Void>, TableCell<Admin, Void>>() {
            @Override
            public TableCell<Admin, Void> call(final TableColumn<Admin, Void> param) {
                final TableCell<Admin, Void> cell = new TableCell<Admin, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de Supprimer Client");
                                alert.setHeaderText("Confirmation de Supprimer Client");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       Admin data = getTableView().getItems().get(getIndex());//connaitre la ligne sup
                                                    System.out.println("selectedData: " + data);
                                                    x.supprimer(data);
                                                   
                                                   ObservableList<Admin> a = FXCollections.observableArrayList();//relation avec base de données
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(x.afficher());// mis a jr base de dn
        System.out.println(lc); 
     for(Utilisateur u: lc)
     { 
     if (u instanceof Admin) {
      a.add((Admin)u);    
     }
         tab2.setItems(a); 
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

        tab2.getColumns().add(colBtn);    
    }   

    @FXML
    private void ajouter(ActionEvent event) {
     serviceUtilisateur u = new serviceUtilisateur();
     
        u.ajouter(new Admin (idf1.getText(),idf2.getText(),idf3.getText(),idf4.getText(),"Admin"));
       JOptionPane.showMessageDialog(null, "Admin ajouté !");
        ObservableList<Admin> a = FXCollections.observableArrayList();//relation avec base de données
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(u.afficher());
        System.out.println(lc); 
     for(Utilisateur x: lc)
     { 
     if (x instanceof Admin) {
      a.add((Admin)x);    
     }
         tab2.setItems(a); 
     }
     
    }

   @FXML
    private void modfier(ActionEvent event) {
         serviceUtilisateur u = new serviceUtilisateur();
      Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier Admin");
                                alert.setHeaderText("Confirmation de modifier Admin");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                     
      u.modifier(new Admin (ClickedAd.getId(),idf1.getText(),idf2.getText(),idf3.getText(),idf4.getText(),"Admin"));
      
        ObservableList<Admin> a = FXCollections.observableArrayList();//relation avec base de données
        ObservableList<Utilisateur> lc = FXCollections.observableArrayList(u.afficher());
        System.out.println(lc); 
     for(Utilisateur x: lc)
     { 
     if (x instanceof Admin) {
      a.add((Admin)x);    
     }
         tab2.setItems(a); 
     }
                                
                                } else {
                                    // ... user chose CANCEL or closed the dialog
                                }
                         
                        
      
    }
    

    
       

    
    

    

    @FXML
    private void clikedadmin(MouseEvent event) {
        ClickedAd= tab2.getSelectionModel().getSelectedItem();
        idf1.setText(ClickedAd.getNom());
         idf2.setText(ClickedAd.getPrenom());
          idf3.setText(ClickedAd.getEmail());
           idf4.setText(ClickedAd.getMDP());
           
        
    }
    
}
   