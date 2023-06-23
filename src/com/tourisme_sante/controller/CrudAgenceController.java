/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tourisme_sante.controller;

import com.tourisme_sante.entities.Agence;
import com.tourisme_sante.utils.Datasource;
import comm.tourisme_sante.services.ServiceAgence;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import javafx.application.Application;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.Optional;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import javafx.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.Node;


/**
 * FXML Controller class
 *
 * @author 21626
 */
public class CrudAgenceController implements Initializable {
  
    
    @FXML
    private TableView<Agence> tableID;
    @FXML
    private TextField nameAgence;
    @FXML
    private TextField adresseAgence;
    @FXML
    private TextField emailAgence;
    @FXML
    private TextField telAgence;
    @FXML
    private TableColumn<Agence, String> colName;
    @FXML
    private TableColumn<Agence, String> colAdresse;
    @FXML
    private TableColumn<Agence, String> colEmail;
    @FXML
    private TableColumn<Agence, Integer> colTel;
 
    private  Agence ag =null;

    
                 ServiceAgence sa = new ServiceAgence();


    
          @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //tableID.setEditable(true);
        
          
          colName.setCellValueFactory(new PropertyValueFactory<Agence,String>("nom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Agence,String>("email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Agence,String>("adresse"));
        colTel.setCellValueFactory(new PropertyValueFactory<Agence,Integer>("telephone"));

        
             ObservableList<Agence> List = FXCollections.observableList(sa.afficher());
      tableID.setItems(List);
         

      
   TableColumn<Agence, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<Agence, Void>, TableCell<Agence, Void>> cellFactory = new Callback<TableColumn<Agence, Void>, TableCell<Agence, Void>>() {
            @Override
            public TableCell<Agence, Void> call(final TableColumn<Agence, Void> param) {
                             FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                 deleteIcon.setStyle("-fx-fill:#ffffff;");
                 Button btn = new Button("Remove", deleteIcon);
                   btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                 
                final TableCell<Agence, Void> cell = new TableCell<Agence, Void>() {

      
                    {
                         Alert alert = new Alert(AlertType.CONFIRMATION);
    
        
                        btn.setOnAction((ActionEvent event) -> {
                                alert.setTitle("Confirmation de supprimer");
        alert.setHeaderText("Confirmation ");
        alert.setContentText("Êtes-vous sûr?");
                            Agence data = getTableView().getItems().get(getIndex());
                                Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
                            sa.supprimer(data);
                   ObservableList<Agence> List = FXCollections.observableList(sa.afficher());
      tableID.setItems(List);
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

        tableID.getColumns().add(colBtn);
 
    }

    



    @FXML
     private void AjouterAgence(ActionEvent event) throws IOException  {
         
  
     //  int number = Integer.parseInt(telAgence.getText());
          
 if (nameAgence.getText().trim().equals("") ||
        adresseAgence.getText().trim().equals("") ||
        telAgence.getText().trim().equals("") ||
        emailAgence.getText().trim().equals("")) {

    Alert alert2 = new Alert(AlertType.WARNING);
    alert2.setTitle("Champs vides");
    alert2.setHeaderText(null);
    alert2.setContentText("Veuillez remplir tous les champs.");
    alert2.showAndWait();

} else
    if (!isValidEmail(emailAgence.getText())) {
        // Email is valid, perform the desired action

        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Email invalide");
        alert.setHeaderText(null);
        alert.setContentText("Email invalide.");
        alert.showAndWait();
    } else
        if (!isUniqueEmail(emailAgence.getText())) {
              Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Email non unique");
            alert.setHeaderText(null);
            alert.setContentText("L'email n'est pas unique.");
            alert.showAndWait();
        } else
            // Email is valid and unique, perform the desired action
          //  emailSet.add(emailAgence.getText());

            if (!isValidPhoneNumber(telAgence.getText())) {
                // Phone number is valid, perform the desired action

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Numéro de téléphone invalide");
                alert.setHeaderText(null);
                alert.setContentText("Numéro de téléphone invalide.");
                alert.showAndWait();
            } else
                if (!validateAddress(adresseAgence.getText())) {
                     
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Adresse invalide");
                    alert.setHeaderText(null);
                    alert.setContentText("Adresse invalide.");
                    alert.showAndWait();
                } else
                
                    if (!isValidName(nameAgence.getText())) {
 
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Nom invalide");
                        alert.setHeaderText(null);
                        alert.setContentText("Nom invalide.");
                        alert.showAndWait();
                    } else {

                        sa.ajouter(new Agence(nameAgence.getText(), adresseAgence.getText(), Integer.parseInt(telAgence.getText()), emailAgence.getText()));
                     Alert successAlert = new Alert(AlertType.INFORMATION);
                        successAlert.setTitle("Succès");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Agence ajoutée !");
                        successAlert.showAndWait();                        
                        ObservableList<Agence> agenceList = FXCollections.observableList(sa.afficher());
                        tableID.setItems(agenceList);
                        nameAgence.setText("");
                        emailAgence.setText("");
                        telAgence.setText("");
                        adresseAgence.setText("");
                    
                    }
                 initialize(null, null); // Call the initialize 

          }
     ///// **************/ *******************/ *******************/ ***********************************   modifier agence *********/ *******************/ *******************/ *******************/ *************************************************************/////
        @FXML
    private void modifierAgence(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier agence");
        alert.setHeaderText("Confirmation de modifier agence");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
          
 if (nameAgence.getText().trim().equals("") ||
        adresseAgence.getText().trim().equals("") ||
        telAgence.getText().trim().equals("") ||
        emailAgence.getText().trim().equals("")) {

    Alert alert2 = new Alert(AlertType.WARNING);
    alert2.setTitle("Champs vides");
    alert2.setHeaderText(null);
    alert2.setContentText("Veuillez remplir tous les champs.");
    alert2.showAndWait();

} else
    if (!isValidEmail(emailAgence.getText())) {
        // Email is valid, perform the desired action
        Alert alert2 = new Alert(AlertType.WARNING);
        alert2.setTitle("Email invalide");
        alert2.setHeaderText(null);
        alert2.setContentText("Email invalide.");
        alert2.showAndWait();
    } else
        if (!isUniqueEmail(emailAgence.getText())) {
              Alert alert2 = new Alert(AlertType.WARNING);
            alert2.setTitle("Email non unique");
            alert2.setHeaderText(null);
            alert2.setContentText("L'email n'est pas unique.");
            alert2.showAndWait();
        } else
            // Email is valid and unique, perform the desired action
          //  emailSet.add(emailAgence.getText());

            if (!isValidPhoneNumber(telAgence.getText())) {

                Alert alert2 = new Alert(AlertType.WARNING);
                alert2.setTitle("Numéro de téléphone invalide");
                alert2.setHeaderText(null);
                alert2.setContentText("Numéro de téléphone invalide.");
                alert2.showAndWait();
            } else
                if (!validateAddress(adresseAgence.getText())) {
                     
                    Alert alert2 = new Alert(AlertType.WARNING);
                    alert2.setTitle("Adresse invalide");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Adresse invalide.");
                    alert.showAndWait();
                } else
                
                    if (!isValidName(nameAgence.getText())) {
                        Alert alert2 = new Alert(AlertType.WARNING);
                        alert2.setTitle("Nom invalide");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Nom invalide.");
                        alert2.showAndWait();
                    }  else {
                    

                        // All validations passed, perform the desired action
                        sa.modifier(new Agence(ag.getId(),nameAgence.getText(), adresseAgence.getText(), Integer.parseInt(telAgence.getText()), emailAgence.getText()));
                           ObservableList<Agence> agenceList = FXCollections.observableList(sa.afficher());
                        tableID.setItems(agenceList);
                     Alert successAlert = new Alert(AlertType.INFORMATION);
                        successAlert.setTitle("Succès");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Agence modifiée !");
                        successAlert.showAndWait();                        
                     
                        nameAgence.setText("");
                        emailAgence.setText("");
                        telAgence.setText("");
                        adresseAgence.setText("");
                    
                    }}
                        initialize(null, null); // Call the initialize 

    }
    @FXML
    
    private void clicked(MouseEvent event) {
        ag=tableID.getSelectionModel().getSelectedItem();
        nameAgence.setText(ag.getNom());
        emailAgence.setText(ag.getEmail());
        adresseAgence.setText(ag.getAdresse());
        telAgence.setText(Integer.toString(ag.getTelephone()));
        
    }

     

        private boolean isValidEmail(String email) {
        // Simple email validation using a regular expression
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

   private boolean isValidPhoneNumber(String phoneNumber) {
        // Simple phone number validation using a regular expression
        // Accepts a 10-digit number starting with 0 or 1
        String phoneRegex = "^[925]\\d{7}$";
        return phoneNumber.matches(phoneRegex);
    }
   
       private boolean isValidName(String name) {
        // Simple name validation using a regular expression
        // Accepts alphabetic characters and spaces, minimum length of 2
        String nameRegex = "^[A-Za-z\\s]{4,}$";
        return name.matches(nameRegex);
    }
  
private boolean isUniqueEmail(String email) {
    Connection cnx = Datasource.getInstance().getCnx();
    String req = "SELECT * FROM hotel WHERE email=?";
    
    try (PreparedStatement st = cnx.prepareStatement(req)) {
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        
        return !rs.next(); // Return true if no rows were found, indicating that the email is unique
    } catch (SQLException e) {
        // Handle any exceptions that may occur
        e.printStackTrace();
        return false; // Return false in case of an error
    }
}
      private boolean validateAddress(String address) {

        return address.length() >= 5; // Exemple : l'adresse doit avoir au moins 5 caractères
    }
          @FXML
    private void gestiontransport(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/CrudTransport.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gestionagence(ActionEvent event) throws IOException {
  
               Parent root = FXMLLoader.load(getClass().getResource("../com/tourisme_sante/views/CrudAgence.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        @FXML
    private void gestionhotel(ActionEvent event) throws IOException {
  
               Parent root = FXMLLoader.load(getClass().getResource("../com/tourisme_sante/views/CrudHotel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}



////
/*           
    
     
     private void editData(){
        colName.setCellFactory(TextFieldTableCell.<Agence>forTableColumn());
        colName.setOnEditCommit(event ->{
            Agence data = event.getTableView().getItems().get(event.getTablePosition().getRow());
            data.setNom(event.getNewValue());
                        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier Agence");
        alert.setHeaderText("Confirmation ");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
      
      sa.modifier(data);
        JOptionPane.showMessageDialog(null, "Agence  modifiée !");
        }
        });

        colAdresse.setCellFactory(TextFieldTableCell.<Agence>forTableColumn());
        colAdresse.setOnEditCommit(event ->{
            Agence ag = event.getTableView().getItems().get(event.getTablePosition().getRow());
            ag.setAdresse(event.getNewValue());
                        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier Agence");
        alert.setHeaderText("Confirmation ");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
      
      sa.modifier(ag);
        JOptionPane.showMessageDialog(null, "Agence  modifiée !");
        }
        });
       colEmail.setCellFactory(TextFieldTableCell.<Agence>forTableColumn());
        colEmail.setOnEditCommit(event ->{
                                         System.out.println(event);

            Agence ag = event.getTableView().getItems().get(event.getTablePosition().getRow());
            ag.setEmail(event.getNewValue());
                              Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier Agence");
        alert.setHeaderText("Confirmation ");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
      
      sa.modifier(ag);
        JOptionPane.showMessageDialog(null, "Agence  modifiée !");
          ObservableList<Agence> List = FXCollections.observableList(sa.afficher());
      tableID.setItems(List);
        }
        });
        
          
colTel.setCellFactory(TextFieldTableCell.<Agence, Integer>forTableColumn(new IntegerStringConverter()));
//TableColumn<Integer, Integer> colTel = new TableColumn<>("Telephone");

             colTel.setOnEditCommit(event ->{
                 

            Agence ag = event.getTableView().getItems().get(event.getTablePosition().getRow());
           ag.setTelephone(event.getNewValue());
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier Agence");
        alert.setHeaderText("Confirmation ");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
      
      sa.modifier(ag);
        JOptionPane.showMessageDialog(null, "Agence  modifiée !");
          ObservableList<Agence> List = FXCollections.observableList(sa.afficher());
      tableID.setItems(List);
        }
        });
    
       
    }
*/

     