/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Commande;
import com.tourisme_sante.entities.Produit;
import comm.tourisme_sante.services.CommandeService;
import comm.tourisme_sante.services.PanierService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PanierController implements Initializable {

  
    @FXML
    private GridPane panierGrid;
    
    
        
    private PanierService panierserv;
    private CommandeService commandeService;
    private List<Produit> reponses;
    private ObservableList<Produit> panierList;
    @FXML
    private Label mt;

    
    
    
    private void setInit() throws ParseException, SQLException{
         
        reponses = new ArrayList<>(qstData());

        int columns = 0;
        int rows = 1;
        try {
            for (int i = 0; i < reponses.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProduitSinglePanier.fxml"));

                VBox questBox = fxmlLoader.load();
                ProduitSinglePanierController produitSinglePanierController = fxmlLoader.getController();
                produitSinglePanierController.setData(reponses.get(i));
              
                if (columns == 1) {
                    columns = 0;
                    ++rows;
                }

                panierGrid.add(questBox, columns++, rows);
                GridPane.setMargin(questBox, new Insets(10, 0, 3, 10));
                mt.setText(String.valueOf(calculMT()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    
    
    private List<Produit> qstData() {
        panierserv = new PanierService();
        try {
             
            panierList = FXCollections.observableArrayList(panierserv.getPanierByUser(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return panierList;
    } 
    
    
    
    
    
   public Double calculMT() {
       AtomicReference<Double> montant = new AtomicReference<>(0.0);
    this.qstData().forEach(p -> {
           try {
               montant.set(montant.get() + (p.getPrix() * panierserv.getQtByProduit(p.getId())));
           } catch (SQLException ex) {
               Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
           }
    });
    double roundedNumber = Math.round(montant.get() * 100.0) / 100.0;
    return roundedNumber;
}

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.setInit();
           

        } catch (ParseException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    

    @FXML
    private void clickMe(ActionEvent event) throws IOException {
        commandeService = new CommandeService();
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        
        this.qstData().forEach(d->{
            int qt=1;
            try {
                qt = panierserv.getQtByProduit(d.getId());
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Commande c = new Commande();
            c.setDatePassation(formattedDate);
            c.setMt(qt);
            c.setIdProduit(d.getId());
            c.setIdClient(1);
            c.setQuantite(qt);
            try {
                commandeService.ajouter(c);
                 panierserv.supprimer(1,d.getId());
                 
            } catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Commande passé");
            alert.setHeaderText(null);
            alert.setContentText("Le Panier va etre vide !");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherProduitFront.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherProduitFront.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

       @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        panierGrid.getScene().setRoot(root);
    }

    @FXML
    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        panierGrid.getScene().setRoot(root);
    }

   @FXML
    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             panierGrid.getScene().setRoot(root);
    }

    @FXML
    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             panierGrid.getScene().setRoot(root);
    }

 
    @FXML
    private void gestionoffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
         
      panierGrid.getScene().setRoot(root);
    }

    @FXML
    private void reservationgs(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           panierGrid.getScene().setRoot(root);
    }
    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();
         
      panierGrid.getScene().setRoot(root);
    }

    @FXML
    private void gestionproduit(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      panierGrid.getScene().setRoot(root);
    }

   @FXML
    private void gestionpanier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFront.fxml"));
            Parent root = loader.load();   
      panierGrid.getScene().setRoot(root);
    }

    
}
