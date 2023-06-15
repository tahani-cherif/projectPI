/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Panier;
import com.tourisme_sante.entities.Produit;
import comm.tourisme_sante.services.PanierService;
import comm.tourisme_sante.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProduitSinglePanierController implements Initializable {
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
    
     private PanierService panierserv;
    @FXML
    private TextField qtParProduit;

    
    public void setData(Produit p) throws ParseException, SQLException {
        quest = new ProduitService();
        panierserv = new PanierService();
        
        int qt = panierserv.getQtByProduit(p.getId());
        currentProduit=p;
        
        prodNom.setText(p.getNom());
        qtDisponible.setText(String.valueOf(p.getQtDisponible()));
        prixProduit.setText(String.valueOf(p.getPrix()));
        qtParProduit.setText(String.valueOf(qt));
        
 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.onChangeQt();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitSinglePanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void ajouterPanier(ActionEvent event) throws IOException {
        holder.setProduit(currentProduit);
        
         /*********** TODO : Replace userID (32) By GetCurrentUser ************/
            Panier p = new Panier(currentProduit.getId(),1,currentProduit.getPrix() ,1);
            PanierService sp = new PanierService();

            try {
                sp.ajouter(p);
                
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }

    }
    
    
    
    private void onChangeQt() throws SQLException{
        panierserv =  new PanierService();
        qtParProduit.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("a"+newValue);
            try {
                panierserv.updateQtByProduit(currentProduit.getId(), Integer.parseInt(newValue));
            } catch (SQLException ex) {
                Logger.getLogger(ProduitSinglePanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
}
