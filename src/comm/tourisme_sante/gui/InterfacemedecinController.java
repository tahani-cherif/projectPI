/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Utilisateur;
import com.tourisme_sante.entities.medecins;
import com.tourisme_sante.utils.Datasource;
import comm.tourisme_sante.services.serviceMedecin;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @FXML
    private TextField idsearch;
    public boolean etat=false;
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
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier medecin");
                                alert.setHeaderText("Confirmation de modifier medeci");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       medecins data = getTableView().getItems().get(getIndex());
                                                    System.out.println("selectedData: " + data);
                                                    sm.supprimer(data);
                                                   // medecinList.remove(data);
                                                    ObservableList<medecins> medecinList = FXCollections.observableList(sm.afficher());
      
                                                   IDtable.setItems(medecinList);
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
            TableColumn<medecins, Void> colplaning = new TableColumn("Planing");
       Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>> cellFactory2 = new Callback<TableColumn<medecins, Void>, TableCell<medecins, Void>>() {
            @Override
            public TableCell<medecins, Void> call(final TableColumn<medecins, Void> param) {
                final TableCell<medecins, Void> cell = new TableCell<medecins, Void>() {

                    private final Button btn = new Button("voir planing");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                           
                       
                            try {
                                PlaningController.x=getTableView().getItems().get(getIndex());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("planing.fxml"));
                                Parent root = loader.load();
                                IDtable.getScene().setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(InterfacemedecinController.class.getName()).log(Level.SEVERE, null, ex);
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

        colplaning.setCellFactory(cellFactory2);

        IDtable.getColumns().add(colplaning);
        //fltrage
         FilteredList<medecins> filteredData = new FilteredList<>(medecinList, b -> true);
         idsearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(medecin -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (medecin.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (medecin.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                 else if (medecin.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                  else if (medecin.getSpecialite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				else if (String.valueOf(medecin.getNumero()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
                 SortedList<medecins> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(IDtable.comparatorProperty());
		IDtable.setItems(sortedData);
    }    

    @FXML
    private void ajoutermedecin(ActionEvent event) {
         if(!etat){
          String nom = TFNom.getText();
        String email = TFEmail.getText();
        String adresse = TFAdresse.getText();
        String  numero= TFNumero.getText();
        String specialite = TFSpecialite.getText();
           if (nom.isEmpty() || email.isEmpty() || adresse.isEmpty()|| numero.isEmpty()|| specialite.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();}else{
         if(valEmail(email)){
             
             Connection cnx = Datasource.getInstance().getCnx();
        Utilisateur liste= null;
        String req = "SELECT * FROM medecins where email=?;";
         try {
           PreparedStatement st = cnx.prepareStatement(req);
             st.setString(1,TFEmail.getText());
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
               Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cette emaile existe");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez changer adresse email.");
            alert.showAndWait();}else{
                         if(valTelephone(numero))
             {      sm.ajouter(new medecins(TFNom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
       ObservableList<medecins> medecinList = FXCollections.observableList(sm.afficher());
         IDtable.setItems(medecinList);
             JOptionPane.showMessageDialog(null, "Medecin ajoutée !");
       // medecinList.add(new medecins(TFNom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
        TFNom.setText("");
        TFEmail.setText("");
        TFAdresse.setText("");
        TFNumero.setText("");
        TFSpecialite.setText("");
       }
             else{
               Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Numero telephone invalide");
            alert.setHeaderText(null);
            alert.setContentText("Numero telephone invalide.");
            alert.showAndWait();
         }
            }
         
         
         
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
  }

         }else{
               Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Adresse E-mail invalide");
            alert.setHeaderText(null);
            alert.setContentText("Adresse E-mail invalide.");
            alert.showAndWait();
         }
   
    }}else{
        
     Alert alert2 = new Alert(AlertType.WARNING);
            alert2.setTitle("medecin existe");
            alert2.setHeaderText(null);
            alert2.setContentText("Medecin existe deja.");
            alert2.showAndWait();
        }
       
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
            
              String nom = TFNom.getText();
        String email = TFEmail.getText();
        String adresse = TFAdresse.getText();
        String  numero= TFNumero.getText();
        String specialite = TFSpecialite.getText();
           if (nom.isEmpty() || email.isEmpty() || adresse.isEmpty()|| numero.isEmpty()|| specialite.isEmpty()) {
            Alert alert2 = new Alert(AlertType.WARNING);
            alert2.setTitle("Champs vides");
            alert2.setHeaderText(null);
            alert2.setContentText("Veuillez remplir tous les champs.");
            alert2.showAndWait();}
           else{
         if(valEmail(email)){
              if(valTelephone(numero))
             {
                   sm.modifier(new medecins(x.getId(),TFNom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
         ObservableList<medecins> medecinList = FXCollections.observableList(sm.afficher());
        IDtable.setItems(medecinList);
         TFNom.setText("");
          TFEmail.setText("");
          TFAdresse.setText("");
          TFNumero.setText("");
          TFSpecialite.setText("");
             } else{
               Alert alert2 = new Alert(AlertType.WARNING);
            alert2.setTitle("Numero telephone invalide");
            alert2.setHeaderText(null);
            alert2.setContentText("Numero telephone invalide.");
            alert2.showAndWait();
         }
         }else{
               Alert alert2 = new Alert(AlertType.WARNING);
            alert2.setTitle("Adresse E-mail invalide");
            alert2.setHeaderText(null);
            alert2.setContentText("Adresse E-mail invalide.");
            alert2.showAndWait();
        } }}else {
             TFNom.setText("");
          TFEmail.setText("");
          TFAdresse.setText("");
          TFNumero.setText("");
          TFSpecialite.setText("");
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
        etat=true;
    }

    @FXML
    private void returnhome(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacepremiere.fxml"));
        Parent root = loader.load();
        TFEmail.getScene().setRoot(root);
    }
    public  boolean valEmail (String input)
    {

     String emailRegex= "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
     Pattern emailPat=Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
      Matcher matcher=emailPat.matcher (input);
     return matcher.find();}  
     public  boolean valTelephone (String input)
    {

     String phoneRegex= "^[+0]{0,2}(91)?[0-9]{8}$";
     Pattern phonePat=Pattern.compile(phoneRegex, Pattern.CASE_INSENSITIVE);
      Matcher matcher=phonePat.matcher (input);
     return matcher.find();}  
}


