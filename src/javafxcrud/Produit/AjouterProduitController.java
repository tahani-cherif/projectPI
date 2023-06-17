/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxcrud.Produit;

import com.tourisme_sante.entities.Produit;
import comm.tourisme_sante.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjouterProduitController implements Initializable {

   
    @FXML
    private TextField nomProduit;
    @FXML
    private TextField prixProduit;
    @FXML
    private TextField qtDisponible;
    @FXML
    private TextField qtUtilisee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException {
         System.out.println("on envoyer taped..");

        if (nomProduit.getText().isEmpty() || prixProduit.getText().isEmpty() || qtDisponible.getText().isEmpty() || qtUtilisee.getText().isEmpty() ) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        } else {
           
            
            /*********** TODO : Replace userID (32) By GetCurrentUser ************/
            Produit p = new Produit(nomProduit.getText(), Double.parseDouble(prixProduit.getText()),Integer.parseInt(qtDisponible.getText()),Integer.parseInt(qtUtilisee.getText()));
            ProduitService sp = new ProduitService();

            try {
                sp.ajouter(p);
                Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }

        }
    }

    @FXML
    private void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      private void listCommandes(ActionEvent event) throws IOException {
        
               Parent root = FXMLLoader.load(getClass().getResource("AfficherCommandes.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


     @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        qtUtilisee.getScene().setRoot(root);
    }

    @FXML
    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        qtUtilisee.getScene().setRoot(root);
    }

   @FXML
    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             qtUtilisee.getScene().setRoot(root);
    }

    @FXML
    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             qtUtilisee.getScene().setRoot(root);
    }

     @FXML
    private void backReserv(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           qtUtilisee.getScene().setRoot(root);
    }

    @FXML
    private void backOffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
         
      qtUtilisee.getScene().setRoot(root);
    }

    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();
         
      qtUtilisee.getScene().setRoot(root);
    }

    @FXML
    private void gestionproduit(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      qtUtilisee.getScene().setRoot(root);
    }
}
