/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.serviceMedecin;
import comm.tourisme_sante.services.serviceRDV;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceRDVController implements Initializable {

    @FXML
    private DatePicker iddate;
    @FXML
    private ComboBox<String> idmedecin;
    serviceRDV RDV = new serviceRDV();
     Map<Integer,String> map = new HashMap<>();
     serviceMedecin sm = new serviceMedecin();
    @FXML
    private TableView<RDV> table;
    @FXML
    private TableColumn<RDV, String> nommedecin;
    @FXML
    private TableColumn<RDV, String> nomuser;
    @FXML
    private TableColumn<RDV, Date> date;
    serviceRDV rdv=new serviceRDV();
    RDV x=null;
    @FXML
    private TextField idsearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         nommedecin.setCellValueFactory(new PropertyValueFactory<RDV, String>("fullName"));
        nomuser.setCellValueFactory(new PropertyValueFactory<RDV, String>("nomuser"));
        date.setCellValueFactory(new PropertyValueFactory<RDV, Date>("dateRDV"));
         ObservableList<RDV> listerdv = FXCollections.observableList(rdv.afficher());
        table.setItems(listerdv);
         TableColumn<RDV, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>> cellFactory = new Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>>() {
            @Override
            public TableCell<RDV, Void> call(final TableColumn<RDV, Void> param) {
                final TableCell<RDV, Void> cell = new TableCell<RDV, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier RDV");
                                alert.setHeaderText("Confirmation de modifier RDV");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                        RDV data = getTableView().getItems().get(getIndex());
                                 System.out.println("selectedData: " + data);
                                 rdv.supprimer(data);
                                   ObservableList<RDV> rdvList = FXCollections.observableList(rdv.afficher());
                                  table.setItems(rdvList);
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
        table.getColumns().add(colBtn);
        List<medecins> m = sm.afficher();
            
            for(int i=0 ; i<m.size();i++)
        {
           map.put(m.get(i).getId(),m.get(i).getFullName());
        }
        ObservableList list = FXCollections.observableArrayList();
       for (Map.Entry ele : map.entrySet()) {
                list.add(ele.getValue());
            }

       idmedecin.setItems(list);
        //fltrage
         FilteredList<RDV> filteredData = new FilteredList<>(listerdv, b -> true);
         idsearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rdv -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (rdv.getNomuser().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (rdv.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(rdv.getDateRDV()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
                 SortedList<RDV> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
      
    }    

    @FXML
    private void daterdv(ActionEvent event) {
    }

    @FXML
    private void medecin(ActionEvent event) {
    }

    @FXML
    private void ajouterrdv(ActionEvent event) {
     
        for (Map.Entry ele : map.entrySet()) {
            if(ele.getValue().equals(idmedecin.getValue())){     
                RDV.ajouter(new RDV(Integer.parseInt(ele.getKey().toString()), 1, Date.valueOf(iddate.getValue())));
            }
        }
ObservableList<RDV> listerdv = FXCollections.observableList(rdv.afficher());
       table.setItems(listerdv);
        JOptionPane.showMessageDialog(null, "RDV ajoutée !");
        
       iddate.setValue(null);
       idmedecin.setValue("");
    }

    @FXML
    private void modifer(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier RDV");
                                alert.setHeaderText("Confirmation de modifier RDV");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       for (Map.Entry ele : map.entrySet()) {
                                        if(ele.getValue().equals(idmedecin.getValue())){     
                                            RDV.modifier(new RDV(x.getId(),Integer.parseInt(ele.getKey().toString()), 1, Date.valueOf(iddate.getValue())));
                                        }
                                    }
                                     ObservableList<RDV> listerdv = FXCollections.observableList(rdv.afficher());
                                    table.setItems(listerdv);
                                   iddate.setValue(null);
                                   idmedecin.setValue("");
                                } else {
                                     iddate.setValue(null);
                                   idmedecin.setValue("");
                                }
         
    }

    @FXML
    private void cliked(MouseEvent event) {
        x=table.getSelectionModel().getSelectedItem();
        iddate.setValue(x.getDateRDV().toLocalDate());
        idmedecin.setValue(x.getFullName());
    }

    @FXML
    private void returnhome(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacepremiere.fxml"));
        Parent root = loader.load();
        iddate.getScene().setRoot(root);
    }
    
}
