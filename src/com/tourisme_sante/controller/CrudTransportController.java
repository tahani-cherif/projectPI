/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tourisme_sante.controller;
import com.tourisme_sante.entities.Transport.TypeTransport;
import com.tourisme_sante.entities.Transport;
import com.tourisme_sante.entities.Agence;
import com.tourisme_sante.entities.Hotel;
import javafx.util.converter.FloatStringConverter;

import comm.tourisme_sante.services.ServiceTransport;
import comm.tourisme_sante.services.ServiceAgence;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import static java.lang.Integer.parseInt;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;
                  import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author 21626
 */
public class CrudTransportController implements Initializable {

    @FXML
    private TextField idMatricule;
    @FXML
    private TextField idPrix;
    @FXML
   // private ComboBox<TypeTransport> comoType;
    private    ComboBox<TypeTransport> comoType = new ComboBox<>();
    @FXML
    private ComboBox<String> comoAgence;
    @FXML
    private TableView<Transport> tableTransport;
    @FXML
    private TableColumn<Transport, String> colMatricule;
    @FXML
    private TableColumn<Transport, String> colType;
    @FXML
    private TableColumn<Transport, Float> colPrix;
    @FXML
    private TableColumn<Transport, String> colAgence;
    @FXML
    private TextField searchid;
        ServiceTransport st = new ServiceTransport();
    Map<Integer, String> map = new HashMap<>();
    ServiceAgence sa = new ServiceAgence();
// Define a variable to store the selected enum value
private TypeTransport transportType;
   Transport T = null;

// ...



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        colAgence.setCellValueFactory(new PropertyValueFactory<Transport, String>("idAgence"));
        colType.setCellValueFactory(new PropertyValueFactory<Transport, String>("x"));
        colMatricule.setCellValueFactory(new PropertyValueFactory<Transport, String>("matricule"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Transport, Float>("prix"));
        
   colAgence.setCellValueFactory(cellData -> {
    int agenceId = cellData.getValue().getIdAgence();
    String agenceNom = map.get(agenceId);
    return new SimpleStringProperty(agenceNom);
});
        // Wrap the ObservableList in a FilteredList (initially display all data).
        
                
       ObservableList<Transport> listeT = FXCollections.observableList(st.afficher());
        tableTransport.setItems(listeT);
        
        FilteredList<Transport> filteredData = new FilteredList<>(listeT, b -> true);
        
        
        
        	// 2. Set the filter Predicate whenever the filter changes.
		searchid.textProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println(newValue);
			filteredData.setPredicate(transport -> {
				// If filter text is empty, display all transport.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (transport.getMatricule().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches 
				} else if(String.valueOf(transport.getIdAgence()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches
				} else if(String.valueOf(transport.getTransportType()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches 
				}
				else if (String.valueOf(transport.getPrix()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
        
        
        
        
        	// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Transport> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableTransport.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableTransport.setItems(sortedData);
        
      
        
        TableColumn<Transport, Void> colBtn = new TableColumn("Suprime");

        Callback<TableColumn<Transport, Void>, TableCell<Transport, Void>> cellFactory = new Callback<TableColumn<Transport, Void>, TableCell<Transport, Void>>() {
            @Override
            public TableCell<Transport, Void> call(final TableColumn<Transport, Void> param) {
                
                             FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                 deleteIcon.setStyle("-fx-fill:#ffffff;");
                 Button btn = new Button("Remove", deleteIcon);
                   btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                 
                final TableCell<Transport, Void> cell = new TableCell<Transport, Void>() {


                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de supprimer transport");
                            alert.setHeaderText("Confirmation de supprimer transport");
                            alert.setContentText("Êtes-vous sûr?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                Transport data = getTableView().getItems().get(getIndex());
                                System.out.println("selectedData: " + data);
                                st.supprimer(data);
                                ObservableList<Transport> transportListe = FXCollections.observableList(st.afficher());
                                tableTransport.setItems( transportListe );
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
        tableTransport.getColumns().add(colBtn);
        List<Agence> listAgence = sa.afficher();

        for (int i = 0; i < listAgence.size(); i++) {
            map.put(listAgence.get(i).getId(), listAgence.get(i).getNom());
        }
        ObservableList list = FXCollections.observableArrayList();
        for (Map.Entry ele : map.entrySet()) {
            list.add(ele.getValue());
        }

        comoAgence.setItems(list);
        

        
                comoType.getItems().addAll(TypeTransport.values());
comoType.setConverter(new StringConverter<TypeTransport>() {
    @Override
    public String toString(TypeTransport myEnum) {
        // Convert the enum value to a string representation
        return myEnum.getDisplayName();
    }

    @Override
    public TypeTransport fromString(String string) {
        // Convert the string back to the corresponding enum value
        return TypeTransport.getByDisplayName(string);
    }

});
// Retrieving the selected enum value

comoType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    // Update the selectedTransportType variable with the new value
    transportType = newValue;
});



    }    

    @FXML
    private void AjouterTransport(ActionEvent event) {
                  if  ( idPrix.getText().trim().equals("") || 
                        idMatricule.getText().trim().equals("")) {

    Alert alert2 = new Alert(Alert.AlertType.WARNING);
    alert2.setTitle("Champs vides");
    alert2.setHeaderText(null);
    alert2.setContentText("Veuillez remplir tous les champs.");
    alert2.showAndWait();

} else
        if (!validatePrix(idPrix.getText())) {
 
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle(" prix invalide l");
                        alert.setHeaderText(null);
                        alert.setContentText("prix invalide l");
                        alert.showAndWait();
                    } 
                   else
                
                    if (!validateMatricule(idMatricule.getText())) {
 
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Nom invalide");
                        alert.setHeaderText(null);
                        alert.setContentText("Nom invalide.");
                        alert.showAndWait();
                    } 
                       else if (comoAgence.getSelectionModel().isEmpty())
        {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" vous devez choisir agencel");
                        alert2.setHeaderText(null);
                        alert2.setContentText("vous devez choisir agence l");
                        alert2.showAndWait();
        }else if  (comoType.getSelectionModel().isEmpty()) {
 Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" vous devez choisir type!");
                        alert2.setHeaderText(null);
                        alert2.setContentText("vous devez choisir type l");
                        alert2.showAndWait();
} else  {

         for (Map.Entry ele : map.entrySet()) {
            if (ele.getValue().equals(comoAgence.getValue())) {
                System.out.println(transportType+"ttttt");
                st.ajouter(new Transport(idMatricule.getText(), transportType,  Float.parseFloat(idPrix.getText()), Integer.parseInt(ele.getKey().toString())));

            }
         }
                    
      
        ObservableList<Transport> listeTransport = FXCollections.observableList(st.afficher());
        tableTransport.setItems(listeTransport);
        JOptionPane.showMessageDialog(null, "Transport  ajoutée !");

        comoAgence.setValue("");
                        }
                            initialize(null, null); // Call the initialize method before adding

    }
    
    @FXML
    private void modifierTransport(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier Transport");
        alert.setHeaderText("Confirmation de modifier Transport");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
                  if  ( idPrix.getText().trim().equals("") || 
                        idMatricule.getText().trim().equals("")) {

    Alert alert2 = new Alert(Alert.AlertType.WARNING);
    alert2.setTitle("Champs vides");
    alert2.setHeaderText(null);
    alert2.setContentText("Veuillez remplir tous les champs.");
    alert2.showAndWait();

} else 
                    if (!validatePrix(idPrix.getText())) {
 
                        Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" prix invalide l");
                        alert2.setHeaderText(null);
                        alert2.setContentText("prix invalide l");
                        alert2.showAndWait();
                    } else 
                        if (!validateMatricule(idMatricule.getText())) {
 
                        Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" matricule invalide l");
                        alert2.setHeaderText(null);
                        alert2.setContentText("matricule invalide l");
                        alert2.showAndWait();
                    }                 else if (comoAgence.getSelectionModel().isEmpty())
        {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" vous devez choisir agencel");
                        alert2.setHeaderText(null);
                        alert2.setContentText("vous devez choisir agence l");
                        alert2.showAndWait();
        } else if  (comoType.getSelectionModel().isEmpty()) {
 Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" vous devez choisir type!");
                        alert2.setHeaderText(null);
                        alert2.setContentText("vous devez choisir type l");
                        alert2.showAndWait();
} else  {
  
            for (Map.Entry ele : map.entrySet()) {
                if (ele.getValue().equals(comoAgence.getValue())) {
                    
                    st.modifier(new Transport(T.getId(),idMatricule.getText(),transportType ,Float.parseFloat(idPrix.getText()), Integer.parseInt(ele.getKey().toString())));
                }
            }
            ObservableList<Transport> listetransports = FXCollections.observableList(st.afficher());
            tableTransport.setItems(listetransports);
                      System.out.println(listetransports);

        JOptionPane.showMessageDialog(null, "Transport  modifiée!");

     comoAgence.setValue("");
            idMatricule.setText("");
                 comoType.getItems().addAll(TypeTransport.values());

            idPrix.setText("");
       
        }
                    
        }
                  else {
           comoAgence.setValue("");
            idMatricule.setText("");
                 comoType.getItems().addAll(TypeTransport.values());

            idPrix.setText("");
        
        }
                initialize(null, null); // Call the initialize 

        
    }


@FXML
    private void cliked(MouseEvent event) {
        T = tableTransport.getSelectionModel().getSelectedItem();
        idMatricule.setText(T.getMatricule());
        comoType.setValue(T.getTransportType());

        idPrix.setText(String.valueOf(T.getPrix()));
        comoAgence.setValue(T.getNomAgence());
    }
    private boolean validatePrix( String prix )  {
    return prix.matches("\\d*(\\.\\d{0,2})?") && Double.parseDouble(prix) > 0;
}
      private boolean validateMatricule(String mat) {

        return mat.length() >= 5; // Exemple : l'adresse doit avoir au moins 5 caractères
    }
 @FXML
    private void gestiontransport(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("../views/CrudTransport.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gestionagence(ActionEvent event) throws IOException {
  
               Parent root = FXMLLoader.load(getClass().getResource("../views/CrudAgence.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        @FXML
    private void gestionhotel(ActionEvent event) throws IOException {
  
               Parent root = FXMLLoader.load(getClass().getResource("../views/CrudHotel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
