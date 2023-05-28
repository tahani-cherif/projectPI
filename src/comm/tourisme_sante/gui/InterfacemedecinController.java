/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.serviceMedecin;
import static java.lang.Integer.parseInt;
import java.net.URL;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfacemedecinController implements Initializable {

    @FXML
    private TextField TFNom;
    @FXML
    private TextField TFEmail;
    @FXML
    private TextField TFAdresse;
    @FXML
    private TextField TFNumero;
    @FXML
    private TextField TFSpecialite;
    @FXML
    private TableView<medecins> IDtable;
    @FXML
    private TableColumn<medecins, String> Colfullname;
    @FXML
    private TableColumn<medecins, String> colmail;
    @FXML
    private TableColumn<medecins, String> coladresse;
    @FXML
    private TableColumn<medecins, String> colspecialite;
    @FXML
    private TableColumn<medecins, String>colNumero;
     serviceMedecin sm=new serviceMedecin();
    private  medecins x=null;
     ObservableList<medecins> medecinList=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          Colfullname.setCellValueFactory(new PropertyValueFactory<medecins,String>("fullName"));
        colmail.setCellValueFactory(new PropertyValueFactory<medecins,String>("email"));
        coladresse.setCellValueFactory(new PropertyValueFactory<medecins,String>("adresse"));
        colspecialite.setCellValueFactory(new PropertyValueFactory<medecins,String>("specialite"));
        colNumero.setCellValueFactory(new PropertyValueFactory<medecins,String>("numero"));
       medecinList = FXCollections.observableList(sm.afficher());
      
      IDtable.setItems(medecinList);
      
       TableColumn<medecins, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>> cellFactory = new Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>>() {
            @Override
            public TableCell<medecins, Void> call(final TableColumn<medecins, Void> param) {
                final TableCell<medecins, Void> cell = new TableCell<medecins, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier medecin");
                                alert.setHeaderText("Confirmation de modifier medeci");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       medecins data = getTableView().getItems().get(getIndex());
                                                    System.out.println("selectedData: " + data);
                                                    sm.supprimer(data);
                                                    medecinList.remove(data);
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

        IDtable.getColumns().add(colBtn);
    }    

    @FXML
    private void ajoutermedecin(ActionEvent event) {
        sm.ajouter(new medecins(TFNom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
        JOptionPane.showMessageDialog(null, "Medecin ajoutée !");
        medecinList.add(new medecins(TFNom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
        TFNom.setText("");
        TFEmail.setText("");
        TFAdresse.setText("");
        TFNumero.setText("");
        TFSpecialite.setText("");
    }

    @FXML
    private void modifiermedecin(ActionEvent event) {
        System.out.println(x);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier medecin");
        alert.setHeaderText("Confirmation de modifier medeci");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
             sm.modifier(new medecins(x.getId(),TFNom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
         ObservableList<medecins> medecinList = FXCollections.observableList(sm.afficher());
      
      IDtable.setItems(medecinList);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
      
    }

    @FXML
    private void selectionmedecin(MouseEvent event) {
        x=IDtable.getSelectionModel().getSelectedItem();
        TFNom.setText(x.getFullName());
        TFEmail.setText(x.getEmail());
        TFAdresse.setText(x.getAdresse());
        TFNumero.setText(Integer.toString(x.getNumero()));
        TFSpecialite.setText(x.getSpecialite());
    }
    
}
