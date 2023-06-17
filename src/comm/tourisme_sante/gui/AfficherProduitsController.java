/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Produit;
import com.tourisme_sante.utils.TableExporter;
import comm.tourisme_sante.services.ProduitService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherProduitsController implements Initializable {

 
    @FXML
    private TableView<Produit> tableProduits;
    @FXML
    private TableColumn<Produit, Integer> idProduit;
    
      @FXML
    private TableColumn<Produit, String> nomProduit;

    @FXML
    private TableColumn<Produit, Double> prixProduit;
    @FXML
    private TableColumn<Produit, Integer> qtDisbonible;
    @FXML
    private TableColumn<Produit, Integer> qtUtilisee;
    @FXML
    private TextField searchField;
    
    
    private ObservableList<Produit> produitList;
    private ProduitService prodService;
    
    private ProdHolder holder = ProdHolder.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prodService = new ProduitService();
        
         try {
            produitList = FXCollections.observableArrayList(prodService.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        idProduit.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        qtDisbonible.setCellValueFactory(new PropertyValueFactory<>("qtDisponible"));
        qtUtilisee.setCellValueFactory(new PropertyValueFactory<>("qtUtilisee"));

        tableProduits.setItems(produitList);
        try {
            dynamicSearch();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   
    
    private void dynamicSearch() throws SQLException {
        prodService = new ProduitService();
        ObservableList<Produit> produitsLists = FXCollections.observableArrayList();
        produitsLists.addAll(prodService.afficher());

        FilteredList<Produit> filteredData = new FilteredList<>(produitsLists, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(g -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (g.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (Double.toString(g.getPrix()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProduits.comparatorProperty());
        tableProduits.setItems(sortedData);

    }



    @FXML
    private void deleteProduit(ActionEvent event) throws SQLException, IOException {
         prodService = new ProduitService();
        
        Produit selectedProduit = tableProduits.getSelectionModel().getSelectedItem();
        if (selectedProduit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas de Produit séléctionnée");
            alert.setContentText("S'il vous plait de séléctionner un Produit");
            alert.showAndWait();
        } else {
            
            prodService.supprimer(selectedProduit);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Question supprimée avec succés !");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void statButton(ActionEvent event) {
    }
    
       /************** Modifier Question *******************/
    @FXML
    void modifierProduit(ActionEvent event) throws SQLException, IOException {
        Produit selectedProduit = tableProduits.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            holder.setProduit(selectedProduit);
            
            Parent root = FXMLLoader.load(getClass().getResource("ModifierProduit.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //loadUsers();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas de Produit séléctionnée");
            alert.setContentText("S'il vous plait de séléctionner un Produit");
            alert.showAndWait();
        }

    }


    @FXML
    private void exportExcel(ActionEvent event) throws IOException {
         String filePath = "C:\\Users\\User\\Desktop\\Commandes.xlsx";
        TableExporter.exportToExcel(tableProduits, filePath);
        Desktop.getDesktop().open(new File("C:\\Users\\User\\Desktop\\Commandes.xlsx"));
    }

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void listCommandess(ActionEvent event) throws IOException {
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
        tableProduits.getScene().setRoot(root);
    }

    @FXML
    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        tableProduits.getScene().setRoot(root);
    }

   @FXML
    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             tableProduits.getScene().setRoot(root);
    }

    @FXML
    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             tableProduits.getScene().setRoot(root);
    }
    
    @FXML
    private void gestionoffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
         
      tableProduits.getScene().setRoot(root);
    }

    @FXML
    private void reservationgs(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           tableProduits.getScene().setRoot(root);
    }
    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();
         
      tableProduits.getScene().setRoot(root);
    }

    @FXML
    private void gestionproduit(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      tableProduits.getScene().setRoot(root);
    }

    @FXML
    private void gestionpanier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFront.fxml"));
            Parent root = loader.load();   
      tableProduits.getScene().setRoot(root);
    }


    
    
    
}
