/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.agence_medicale.Interventions;
import com.mycompany.agence_medicale.TypeInterventions;
import com.mycompany.service.Service_TypeInterventions;
import com.mycompany.utils.DataSourceA;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class FullcrudController implements Initializable {
     private Connection cnx = DataSourceA.getInstance().getCnx();
     Service_TypeInterventions sc= new  Service_TypeInterventions();
    int index=-1;
    int id=0;
    TypeInterventions tt=null;
    


    @FXML
    private TextField nomField;
    @FXML
    private TextField descField;
    @FXML
    private TableView<TypeInterventions> tb;
   
    @FXML
    private TableColumn<TypeInterventions, String> colNom;
    @FXML
    private TableColumn<TypeInterventions, String> colDesc;
     @FXML
     private Button chatButton;
    @FXML
    private TextField searchId;
    @FXML
    private TextField stat;
    @FXML
    private TextField stat2;
    
    
   
  
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTypeInterventions();
        
         TableColumn<TypeInterventions, Void> colBtn = new TableColumn("Supprimer");
         Callback<TableColumn<TypeInterventions, Void>, TableCell<TypeInterventions, Void>> cellFactory = new Callback<TableColumn<TypeInterventions, Void>, TableCell<TypeInterventions, Void>>() {
            @Override
            public TableCell<TypeInterventions, Void> call(final TableColumn<TypeInterventions, Void> param) {
//                FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//                 deleteIcon.setStyle("-fx-fill:#ffffff;");
//                 Button btn = new Button("Supprimer", deleteIcon);
//                 btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                final TableCell<TypeInterventions, Void> cell = new TableCell<TypeInterventions, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de suppression");
                                alert.setHeaderText("Confirmation de suppression");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                     sc.supprimer(getTableView().getItems().get(getIndex()));
                                     showTypeInterventions();
                                   
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
tb.getColumns().add(colBtn);
        
        
        
        
    }    
    
  
    public void showTypeInterventions(){
        
        ObservableList<TypeInterventions> listType = FXCollections.observableArrayList(sc.afficher());
       
        colNom.setCellValueFactory(new PropertyValueFactory<TypeInterventions, String>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<TypeInterventions, String>("deescripition"));
        tb.setItems(listType);
        nomField.setText("");
        descField.setText("");
                
                
   
    
    
    
    
    
           

      // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<TypeInterventions> filteredData = new FilteredList<>(listType, b -> true);
	        FilteredList<TypeInterventions> filterbyNom = new FilteredList<>(listType, b -> true);
	        FilteredList<TypeInterventions> filterbyDesc = new FilteredList<>(listType, b -> true);


		// 2. Set the filter Predicate whenever the filter changes.
		searchId.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(TypeInterventions -> {
				// If filter text is empty, display all persons.
				
                                if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (TypeInterventions.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches medecin.
                                }else if (String.valueOf(TypeInterventions.getDeescripition()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                             
				     else  
				    	 return false; // Does not match.
                                 
                                
			});
                        // filter by nom
                        filterbyNom.setPredicate(TypeInterventions -> {
				// If filter text is empty, display all persons.
				
                                                	   //System.out.println(tb.getItems().size());

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (TypeInterventions.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches medecin.
                                }else  
				    	 return false; // Does not match.
                                 
                                
			});
                        // filter by Description
                        filterbyDesc.setPredicate(TypeInterventions -> {
				// If filter text is empty, display all persons.
				
                                                	   //System.out.println(tb.getItems().size());

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (TypeInterventions.getDeescripition().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches medecin.
                                }else  
				    	 return false; // Does not match.
                                 
                                
			});
                       stat.setText(""+filterbyNom.size());
                       stat2.setText(""+filterbyDesc.size());

                  

                        
                     });
			
				
                                
                                
				
			
		  
                        
                     
		  
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<TypeInterventions> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tb.comparatorProperty());
                
		
		// 5. Add sorted (and filtered) data to the table.
		tb.setItems(sortedData);
               

    }
                                    
      
    
               
                        
    
   

    
    

    @FXML
    private void AjouterType(ActionEvent event) {
       
         if (nomField.getText().isEmpty())    
        { JOptionPane.showMessageDialog(null,"Erreur! champ obligatoire, vous devez remplir un nom");
         return; 
        } 
        
         if ( !nomField.getText().matches("[a-zA-Z]+")) 
           
        {  JOptionPane.showMessageDialog(null,"Vous devez inserer seulement des lettres");
         return; 
        } 
        if (descField.getText().isEmpty())
        { JOptionPane.showMessageDialog(null,"Erreur! champ obligatoire, vous devez remplir une descripition");
           return;
        }
        
        if ( !descField.getText().matches("[a-zA-Z]+")) 
           
        {  JOptionPane.showMessageDialog(null,"Vous devez inserer seulement des lettres");
         return; 
        }
        sc.ajouter(new TypeInterventions(nomField.getText(),descField.getText()));
        JOptionPane.showMessageDialog(null, "Type Intervention ajoutée !");
         showTypeInterventions();
         
        
    }
    
    
    
    
    


    @FXML
    private void detailsTypeInterventions(MouseEvent event) throws IOException {
        index=tb.getSelectionModel().getSelectedIndex();
        
        System.out.println(index);
        if(index<=-1)
            return;
        tt =tb.getSelectionModel().getSelectedItem();
        
        System.out.println(tt);
        id=tt.getId(); //recupere id element selectioné
        System.out.println("id : "+ id);
        
        
        nomField.setText(colNom.getCellData(index));
        descField.setText(colDesc.getCellData(index));

                }
    @FXML
    private void ModifierType(ActionEvent event) {
         sc.modifier(new TypeInterventions(id,nomField.getText(),descField.getText()));
          showTypeInterventions();
    }


    @FXML
    private void OpenChat(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("chatbox.fxml"));
            Parent root = loader.load();
         
             descField.getScene().setRoot(root);
    }

    @FXML
    private void gestionrendezvous(ActionEvent event) {
    }

    @FXML
    private void gestionmedecin(ActionEvent event) {
    }

    @FXML
    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             descField.getScene().setRoot(root);
    }

    @FXML
    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             descField.getScene().setRoot(root);
    }
    }

    