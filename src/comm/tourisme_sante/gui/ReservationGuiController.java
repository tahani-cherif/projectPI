package comm.tourisme_sante.gui;


import com.tourisme_sante.entities.Agence;
import com.tourisme_sante.entities.Reservation;
import comm.tourisme_sante.services.ServiceAgence;
import comm.tourisme_sante.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;



public class ReservationGuiController implements Initializable{

    @FXML
    private TextField TFHotel;

    @FXML
    private TextField TFTransport;

    @FXML
    private TextField TFUser;

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

    @FXML
    private TextField TFDateDebut;

    @FXML
    private ComboBox<String> CBAgence;

    @FXML
    private TextField TFDateFin;
 Map<Integer,String> map = new HashMap<>();
    @FXML

    void ajouterOffre(ActionEvent event) {
        ServiceReservation sr = new ServiceReservation();
  for (Map.Entry ele : map.entrySet()) {
            if(ele.getValue().equals(CBAgence.getValue())){     
                  sr.ajouter(new Reservation(Date.valueOf(TFDateDebut.getText()),Date.valueOf(TFDateFin.getText()),Integer.parseInt(ele.getKey().toString()), Integer.parseInt(TFUser.getText()),Integer.parseInt(TFHotel.getText()),Integer.parseInt(TFTransport.getText())));
            }
        }
  ObservableList<Reservation> listerdv = FXCollections.observableList(sr.afficher());
       idTable.setItems(listerdv);
        JOptionPane.showMessageDialog(null, "Réservation ajoutée !");
      
       TFDateDebut.setText("");
        TFDateFin.setText("");
        TFHotel.setText("");
        TFTransport.setText("");
       TFUser.setText("");
    }

    @FXML
    void modiffierOffre(ActionEvent event) {

    }

    void returnhome(ActionEvent event) {

    }

    @FXML
    void selectionmedecin(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ServiceAgence Sa=new ServiceAgence();
        ServiceReservation sr=new ServiceReservation();
 List<Agence> m = Sa.afficher();
            
            for(int i=0 ; i<m.size();i++)
        {
           map.put(m.get(i).getId(),m.get(i).getNom());
        }
        ObservableList list = FXCollections.observableArrayList();
       for (Map.Entry ele : map.entrySet()) {
                list.add(ele.getValue());
            }

       CBAgence.setItems(list);
       
         columnDebut.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateDebut"));
        columnFin.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("dateFin"));
        columnHotel.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idHotels"));
        columnTransport.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idTransport"));
        columnUser.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idUser"));
        columnAgence.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nom"));
ObservableList<Reservation> reservationList = FXCollections.observableList(sr.afficher());
        System.out.println(sr.afficher());
      
      idTable.setItems(reservationList);
      
       TableColumn<Reservation, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>> cellFactory = new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
                  Button btn = new Button("Remove");
btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                final TableCell<Reservation, Void> cell = new TableCell<Reservation, Void>() {


                    {
                        btn.setOnAction((ActionEvent event) -> {
                             Alert alert = new Alert(AlertType.CONFIRMATION);
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

    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        idTable.getScene().setRoot(root);
    }

    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             idTable.getScene().setRoot(root);
    }

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

      @FXML
    private void gestionutilisateur(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionClient.fxml"));
            Parent root = loader.load();   
      idTable.getScene().setRoot(root);
    }
    


}
