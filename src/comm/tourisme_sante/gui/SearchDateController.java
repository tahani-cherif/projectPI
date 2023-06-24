/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SearchDateController implements Initializable {

      @FXML
    private DatePicker inputDateDebut;

    @FXML
    private DatePicker inputDateFin;
      @FXML
    private Button searchButton;

    @FXML
    void goToResult(ActionEvent event) throws IOException {
        comm.tourisme_sante.gui.ResultController.dateD=Date.valueOf(inputDateDebut.getValue());
                comm.tourisme_sante.gui.ResultController.dateF=Date.valueOf(inputDateFin.getValue());

  FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
  
        Parent root = loader.load();
                searchButton.getScene().setRoot(root);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

     @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        searchButton.getScene().setRoot(root);
    }

    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        searchButton.getScene().setRoot(root);
    }

    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             searchButton.getScene().setRoot(root);
    }

    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             searchButton.getScene().setRoot(root);
    }

     @FXML
    private void backReserv(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           searchButton.getScene().setRoot(root);
    }

    private void backOffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
      searchButton.getScene().setRoot(root);
    }

    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();   
      searchButton.getScene().setRoot(root);

    }

    private void gestionproduit(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      searchButton.getScene().setRoot(root);
    }

    @FXML
    private void gestionpanier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFront.fxml"));
            Parent root = loader.load();   
      searchButton.getScene().setRoot(root);
    }

      @FXML
    private void gestionutilisateur(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionClient.fxml"));
            Parent root = loader.load();   
      searchButton.getScene().setRoot(root);
    }

     @FXML
    private void gestionsearch(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchDate.fxml"));
            Parent root = loader.load();   
      searchButton.getScene().setRoot(root);
    }
   
}
