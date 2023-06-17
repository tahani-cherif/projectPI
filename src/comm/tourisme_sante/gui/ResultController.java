/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Agence;
import com.tourisme_sante.entities.Reservation;
import comm.tourisme_sante.services.ServiceAgence;
import comm.tourisme_sante.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ResultController implements Initializable {

 public static Date dateD;
  public static Date dateF;

    @FXML
    private TableView<Reservation> idTable;

    @FXML
    private TableColumn<Reservation, String> columnAgence;

    @FXML
    private TableColumn<Reservation, Integer> columnHotel;

    @FXML
    private TableColumn<Reservation, Integer> columnUser;

    @FXML
    private TableColumn<Reservation, Integer> columnTransport;

    @FXML
    private TableColumn<Reservation, Date> columnDebut;

    @FXML
    private TableColumn<Reservation, Date> columnFin;

 
 @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceAgence Sa=new ServiceAgence();
        ServiceReservation sr=new ServiceReservation();
 List<Agence> m = Sa.afficher();
            


       
         columnDebut.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateDebut"));
        columnFin.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateFin"));
        columnHotel.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idHotels"));
        columnTransport.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idTransport"));
        columnUser.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idUser"));
        columnAgence.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nom"));
ObservableList<Reservation> reservationList = FXCollections.observableList(sr.afficherParDate(dateD, dateF));
      
      idTable.setItems(reservationList);
      
       TableColumn<Reservation, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>> cellFactory = new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
                final TableCell<Reservation, Void> cell = new TableCell<Reservation, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier offre");
                                alert.setHeaderText("Confirmation de modifier offre");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       Reservation data = getTableView().getItems().get(getIndex());
                                                    System.out.println("selectedData: " + data);
                                                    sr.supprimer(data);
                                                   // medecinList.remove(data);
                                                    ObservableList<Reservation> offreList = FXCollections.observableList(sr.afficher());
      
                                                   idTable.setItems(offreList);
                                } else {
                                    // ... user chose CANCEL or closed the dialog
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

        idTable.getColumns().add(colBtn);
       
       
        
    
    
    
    
    
    
    
    
    
    }

    
   @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        idTable.getScene().setRoot(root);
    }

   @FXML
    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             idTable.getScene().setRoot(root);
    }

    @FXML
    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             idTable.getScene().setRoot(root);
    }



    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();
         
      idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionoffre(ActionEvent event) throws IOException {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
         
      idTable.getScene().setRoot(root);
    }

    @FXML
    private void reservationgs(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionproduit(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      idTable.getScene().setRoot(root);
    }

 @FXML
    private void gestionpanier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFront.fxml"));
            Parent root = loader.load();   
      idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionsearch(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchDate.fxml"));
            Parent root = loader.load();   
      idTable.getScene().setRoot(root);
    }
   

    
    
}
