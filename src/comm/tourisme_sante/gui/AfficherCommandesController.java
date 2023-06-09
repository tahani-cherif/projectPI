/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Commande;
import com.tourisme_sante.utils.TableExporter;
import comm.tourisme_sante.services.CommandeService;
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
public class AfficherCommandesController implements Initializable {

    @FXML
    private TableColumn<Commande, Long> idCommande;
    @FXML
    private TableView<Commande> tableCommandes;
    @FXML
    private TableColumn<Commande, Long> idProduit;
    @FXML
    private TableColumn<Commande, String> datePassation;
    @FXML
    private TableColumn<Commande, Integer> quantite;
    @FXML
    private TableColumn<Commande, Double> mt;
    @FXML
    private TableColumn<Commande, Long> idClient;
    @FXML
    private TextField searchField;
    
     private ObservableList<Commande> commandeList;
    private CommandeService commandeService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         commandeService = new CommandeService();
        
         try {
            commandeList = FXCollections.observableArrayList(commandeService.afficher());
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }

        idCommande.setCellValueFactory(new PropertyValueFactory<>("id"));
        idProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        datePassation.setCellValueFactory(new PropertyValueFactory<>("datePassation"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        mt.setCellValueFactory(new PropertyValueFactory<>("mt"));

        tableCommandes.setItems(commandeList);
        try {
            dynamicSearch();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommandesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   
    
    
     private void dynamicSearch() throws SQLException {
        commandeService = new CommandeService();
        ObservableList<Commande> commandeLists = FXCollections.observableArrayList();
        commandeLists.addAll(commandeService.afficher());

        FilteredList<Commande> filteredData = new FilteredList<>(commandeLists, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(g -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (g.getDatePassation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (Long.toString(g.getIdProduit()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });
        });
        SortedList<Commande> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCommandes.comparatorProperty());
        tableCommandes.setItems(sortedData);

    }


    @FXML
    private void deleteCommande(ActionEvent event) throws SQLException, IOException {
           commandeService = new CommandeService();
        
        Commande selectedCommande = tableCommandes.getSelectionModel().getSelectedItem();
        if (selectedCommande == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas de Commande séléctionnée");
            alert.setContentText("S'il vous plait de séléctionner un Commande");
            alert.showAndWait();
        } else {
            
            commandeService.supprimer(selectedCommande);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Question supprimée avec succés !");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("AfficherCommandes.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void exportExcel(ActionEvent event) throws IOException {
        
               String filePath = "C:\\Users\\User\\Desktop\\Commandes.xlsx";
        TableExporter.exportToExcel(tableCommandes, filePath);
        Desktop.getDesktop().open(new File("C:\\Users\\User\\Desktop\\Commandes.xlsx"));
    }
    
    private void listProduits(ActionEvent event) throws IOException {
        
               Parent root = FXMLLoader.load(getClass().getResource("AfficherProduits.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   
     @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        tableCommandes.getScene().setRoot(root);
    }

    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        tableCommandes.getScene().setRoot(root);
    }

    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             tableCommandes.getScene().setRoot(root);
    }

    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             tableCommandes.getScene().setRoot(root);
    }

     @FXML
    private void backReserv(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           tableCommandes.getScene().setRoot(root);
    }

    private void backOffre(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
      tableCommandes.getScene().setRoot(root);
    }

    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();   
      tableCommandes.getScene().setRoot(root);

    }

    private void gestionproduit(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      tableCommandes.getScene().setRoot(root);
    }

    @FXML
    private void gestionpanier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFront.fxml"));
            Parent root = loader.load();   
      tableCommandes.getScene().setRoot(root);
    }

      @FXML
    private void gestionutilisateur(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionClient.fxml"));
            Parent root = loader.load();   
      tableCommandes.getScene().setRoot(root);
    }

     @FXML
    private void gestionsearch(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchDate.fxml"));
            Parent root = loader.load();   
      tableCommandes.getScene().setRoot(root);
    }

    
}
