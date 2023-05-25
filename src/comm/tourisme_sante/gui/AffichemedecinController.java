/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.serviceMedecin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AffichemedecinController implements Initializable {

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
       // colupdate.setCellValueFactory(new PropertyValueFactory<medecins,Button>("button"));
       //colupdate.setCellFactory(new Buttoncell());
        
     serviceMedecin sm=new serviceMedecin();
      ObservableList<medecins> medecinList = FXCollections.observableList(sm.afficher());
      IDtable.setItems(medecinList);
       TableColumn<medecins, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>> cellFactory = new Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>>() {
            @Override
            public TableCell<medecins, Void> call(final TableColumn<medecins, Void> param) {
                final TableCell<medecins, Void> cell = new TableCell<medecins, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            medecins data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            sm.supprimer(data);
                              ObservableList<medecins> medecinList = FXCollections.observableList(sm.afficher());
                             IDtable.setItems(medecinList);
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
         TableColumn<medecins, Void> updateBtn = new TableColumn("update");
       Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>> cellFactory2 = new Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>>() {
            @Override
            public TableCell<medecins, Void> call(final TableColumn<medecins, Void> param) {
                final TableCell<medecins, Void> cell = new TableCell<medecins, Void>() {

                    private final Button btn = new Button("update");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                                medecins data = getTableView().getItems().get(getIndex());
                                System.out.println("selectedData: " + data);
                                UpdatemedecinController.x=data;
                                  FXMLLoader loader = new FXMLLoader(getClass().getResource("Updatemedecin.fxml"));
                                Parent root = loader.load();
                                IDtable.getScene().setRoot(root);
                                UpdatemedecinController dpc = loader.getController();
                                
                                
                            } catch (IOException ex) {
                                Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
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

        updateBtn.setCellFactory(cellFactory2);
         IDtable.getColumns().add(updateBtn);
      
        
    }    
 /*  public void test(){
                             medecins data = (medecins) IDtable.getItems();
                                System.out.println("selectedData: " + data);
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Updatemedecin.fxml"));
                              /*  Parent root = loader.load();
                                IDtable.getScene().setRoot(root);
                                UpdatemedecinController x=loader.getController();
} */
}


