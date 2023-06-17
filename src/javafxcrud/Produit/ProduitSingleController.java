/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxcrud.Produit;

import entities.Panier;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.PanierService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author omarb
 */
public class ProduitSingleController implements Initializable {

    @FXML
    private Text prodNom;
    @FXML
    private Label qtDisponible;
    @FXML
    private Button btnReply;
    @FXML
    private Label prixProduit;

      private ProduitService quest;
     private  ProdHolder holderQuest = ProdHolder.getInstance();
     private  ProdHolder holder = ProdHolder.getInstance();
    private Produit CurrentQuestion = holder.getProduit();
    private Produit currentProduit;

    
    public void setData(Produit p) throws ParseException {
        quest = new ProduitService();
        
        currentProduit=p;
        
        prodNom.setText(p.getNom());
        qtDisponible.setText(String.valueOf(p.getQtDisponible()));
        prixProduit.setText(String.valueOf(p.getPrix()));
        
 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherReponse(ActionEvent event) throws IOException {
             
        holder.setProduit(currentProduit);
        Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ajouterPanier(ActionEvent event) throws IOException {
        holder.setProduit(currentProduit);
        
         /*********** TODO : Replace userID (32) By GetCurrentUser ************/
            Panier p = new Panier(currentProduit.getId(),1,currentProduit.getPrix() ,1);
            PanierService sp = new PanierService();

            try {
                sp.ajouter(p);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Produit ajouter");
            alert.setHeaderText(null);
            alert.setContentText("Produit Ajouter au panier");
            alert.showAndWait();
            
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }

    }
    
}
