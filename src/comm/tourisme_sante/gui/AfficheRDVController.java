/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.serviceMedecin;
import comm.tourisme_sante.services.serviceRDV;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AfficheRDVController implements Initializable {

    @FXML
    private TableView<RDV> idtabRDV;
    @FXML
    private TableColumn<RDV, Integer> idmedecin;
    @FXML
    private TableColumn<RDV, Integer> idclient;
    @FXML
    private TableColumn<RDV, Date> iddate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        idmedecin.setCellValueFactory(new PropertyValueFactory<RDV,Integer>("idmedecin"));
        idclient.setCellValueFactory(new PropertyValueFactory<RDV,Integer>("iduser"));
        iddate.setCellValueFactory(new PropertyValueFactory<RDV,Date>("dateRDV"));
         serviceRDV sm=new serviceRDV();
      ObservableList<RDV> rdvList = FXCollections.observableList(sm.afficher());
      idtabRDV.setItems(rdvList);
       TableColumn<RDV, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>> cellFactory = new Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>>() {
            @Override
            public TableCell<RDV, Void> call(final TableColumn<RDV, Void> param) {
                final TableCell<RDV, Void> cell = new TableCell<RDV, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            RDV data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            sm.supprimer(data);
                              ObservableList<RDV> rdvList = FXCollections.observableList(sm.afficher());
                             idtabRDV.setItems(rdvList);
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
        idtabRDV.getColumns().add(colBtn);

    }    
    
}
