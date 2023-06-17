/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxcrud.Produit;

import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author omarb
 */
public class ModifierProduitController implements Initializable {

    @FXML
    private TextField nomProduit;
    @FXML
    private TextField prixProduit;
    @FXML
    private TextField qtDisponible;
    @FXML
    private TextField qtUtilisee;
    
    private ProduitService produitService;
    private  ProdHolder holder = ProdHolder.getInstance();
    private Produit CurrentProduit = holder.getProduit();

    
    
     public void initData(Produit prod) {
        CurrentProduit = prod;
        holder.setProduit(CurrentProduit);
        
        nomProduit.setText(CurrentProduit.getNom());
        prixProduit.setText(String.valueOf(CurrentProduit.getPrix()));
        qtDisponible.setText(String.valueOf(CurrentProduit.getQtDisponible()));
        qtUtilisee.setText(String.valueOf(CurrentProduit.getQtUtilisee()));

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.initData(CurrentProduit);
    }    

    @FXML
    private void modifierProduit(ActionEvent event) {
        
         try {

            String nomProduitv = nomProduit.getText();
            String prixProduitv = prixProduit.getText();
            String qtDisponiblev = qtDisponible.getText();
            String qtUtiliseev = qtUtilisee.getText();

            if (nomProduitv.isEmpty() || prixProduitv.isEmpty()|| qtDisponiblev.isEmpty()|| qtUtiliseev.isEmpty()) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Erreur");
                al.setContentText("Veuillez remplir tous les champs !");
                al.show();
                return;
            }
            if (prixProduitv.length() < 0 ) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Erreur de données");
                al.setContentText("Le champ 'Prix' doit etre sup a 0 !");
                al.show();
                return;
            }

            CurrentProduit.setNom(nomProduitv);
            CurrentProduit.setPrix(Double.parseDouble(prixProduitv));
            CurrentProduit.setQtDisponible(Integer.parseInt(qtDisponiblev));
            CurrentProduit.setQtUtilisee(Integer.parseInt(qtUtiliseev));

            ProduitService service = new ProduitService();
            service.modifier(CurrentProduit);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification de Produit");
            alert.setHeaderText(null);
            alert.setContentText("Le Produit a été modifiée avec succès !");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la modification de Produit !");
            alert.showAndWait();
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
    
     @FXML
      private void listCommandes(ActionEvent event) throws IOException {
        
               Parent root = FXMLLoader.load(getClass().getResource("AfficherCommandes.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
