/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Produit;
import comm.tourisme_sante.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherProduitFrontController implements Initializable {

   @FXML
    private GridPane prodGrid;
    
     private List<Produit> produits;
    private ObservableList<Produit> produitList;
    private ProduitService prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              produits = new ArrayList<>(qstData());

        int columns = 0;
        int rows = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProduitSingle.fxml"));

                VBox questBox = fxmlLoader.load();
                ProduitSingleController produitSingleController = fxmlLoader.getController();
                produitSingleController.setData(produits.get(i));
                
              
                if (columns == 1) {
                    columns = 0;
                    ++rows;
                }

                prodGrid.add(questBox, columns++, rows);
                GridPane.setMargin(questBox, new Insets(10, 0, 3, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(AfficherProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
     private List<Produit> qstData() {
        prod = new ProduitService();
        try {
            produitList = FXCollections.observableArrayList(prod.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produitList;
    }
     
   
    @FXML
    private void accederPanier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

      @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        prodGrid.getScene().setRoot(root);
    }

    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        prodGrid.getScene().setRoot(root);
    }

    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             prodGrid.getScene().setRoot(root);
    }

    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             prodGrid.getScene().setRoot(root);
    }

    private void gestionoffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
         
      prodGrid.getScene().setRoot(root);
    }

    @FXML
    private void reservationgs(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           prodGrid.getScene().setRoot(root);
    }
    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();
         
      prodGrid.getScene().setRoot(root);
    }

    private void gestionproduit(ActionEvent event) throws IOException {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      prodGrid.getScene().setRoot(root);
    }

    @FXML
    private void gestionpanier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFront.fxml"));
            Parent root = loader.load();   
      prodGrid.getScene().setRoot(root);
    }

      @FXML
    private void gestionutilisateur(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionClient.fxml"));
            Parent root = loader.load();   
      prodGrid.getScene().setRoot(root);
    }

  @FXML
    private void gestionsearch(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchDate.fxml"));
            Parent root = loader.load();   
      prodGrid.getScene().setRoot(root);
    }

    
    
}
