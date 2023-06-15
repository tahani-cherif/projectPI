/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Interventions;
import com.tourisme_sante.entities.TypeInterventions;
import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.Service_TypeInterventions;
import comm.tourisme_sante.services.ServicesInterventions;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.tourisme_sante.utils.Datasource;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
 * @author Chimou
 */
public class FullInterController implements Initializable {
    
     private Connection cnx = Datasource.getInstance().getCnx();
     ServicesInterventions ss= new  ServicesInterventions();
    int index=-1;
    int id=0;
 
    Interventions ll=null;
   Map<Integer,String> map = new HashMap<>();
    ObservableList listInterventions = FXCollections.observableArrayList();
  Map<Integer,String> mape = new HashMap<>();
    ObservableList listInter = FXCollections.observableArrayList();
   Service_TypeInterventions st= new   Service_TypeInterventions();
   


  
  
   
    @FXML
    private TextField nomF;
    @FXML
    private TextField prixF;
    @FXML
    private TextField descF;
    private TextField medecinF;
    private TextField typeF;
   
    @FXML
    private TableView<Interventions> tl;
     
    @FXML
    private TableColumn<Interventions, String> colNom;
    @FXML
    private TableColumn<Interventions, Double> colPrix;
    @FXML
    private TableColumn<Interventions, String> colDesc;
    @FXML
    private TableColumn<Interventions, String> colidm;
    @FXML
    private TableColumn<Interventions, String> coltype;
    @FXML
    private Button btA;
    @FXML
    private Button btM;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private ComboBox<String> combi;
    @FXML
    private TextField searchField;
    @FXML
    private TextField NID;
    @FXML
    private TextField PID;
    @FXML
    private TextField DID;
    @FXML
    private TextField MID;
    @FXML
    private TextField IID;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showInterventions();
       
       
       
       TableColumn<Interventions, Void> colBtn = new TableColumn("Supprimer");
       Callback<TableColumn<Interventions, Void>, TableCell<Interventions, Void>> cellFactory = new Callback<TableColumn<Interventions, Void>, TableCell<Interventions, Void>>() {
            @Override
            public TableCell<Interventions, Void> call(final TableColumn<Interventions, Void> param) {
                 FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                 deleteIcon.setStyle("-fx-fill:#ffffff;");
                 Button btn = new Button("Supprimer", deleteIcon);
                   btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                final TableCell<Interventions, Void> cell = new TableCell<Interventions, Void>() {

                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de suppression");
                                alert.setHeaderText("Confirmation de suppression");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                     ss.supprimer(getTableView().getItems().get(getIndex()));
                                     showInterventions();
                                   
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
tl.getColumns().add(colBtn);
       
        List<medecins> list = new ArrayList<>();
       
        String req = "SELECT * FROM medecins ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new medecins(rs.getInt("id"), rs.getString("fullName"),rs.getString("email"), rs.getString("adresse"),rs.getInt("numero"),rs.getString("specialite")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
     
        for(medecins inter:list)
            {
                map.put(inter.getId(), inter.getFullName());
            }
            for (Map.Entry ele : map.entrySet()) {
               listInterventions.add(ele.getValue());
            }

            combo.setItems(listInterventions);
            
            
            
            
            
               List<TypeInterventions> liste = new ArrayList<>();
        liste= st.afficher();
     
        for(TypeInterventions inter:liste)
            {
                mape.put(inter.getId(), inter.getNom());
            }
            for (Map.Entry ele : mape.entrySet()) {
              listInter.add(ele.getValue());
            }

            combi.setItems(listInter);
     
     
    }
    
    
    
    
    
    
    
    
      public void showInterventions(){
        ObservableList<Interventions> listType = FXCollections.observableArrayList(ss.afficher());
       
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomType"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descripition"));
        colidm.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("nomTypeIntervention"));
        tl.setItems(listType);

      
      
      
      //filtrage 
      // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Interventions> filteredData = new FilteredList<>(listType, b -> true);
         FilteredList<Interventions> filterbyNom = new FilteredList<>(listType, b -> true);
          FilteredList<Interventions> filterbyPrix = new FilteredList<>(listType, b -> true);
	
         FilteredList<Interventions> filterbyDesc = new FilteredList<>(listType, b -> true);
          FilteredList<Interventions> filterbyMedecin = new FilteredList<>(listType, b -> true);
         FilteredList<Interventions> filterbyNomType = new FilteredList<>(listType, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Interventions -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Interventions.getNomType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches  name.
				} else if (Interventions.getDescripition().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches descripition.
				} else if (Interventions.getNomTypeIntervention().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches medecin.
				}  else if (Interventions.getMedecin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches medecin.
                                }else if (String.valueOf(Interventions.getPrix()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
                        
                        
                         // filter by nom
                        filterbyNom.setPredicate(Interventions -> {
				// If filter text is empty, display all persons.
				
                                                	   //System.out.println(tb.getItems().size());

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (Interventions.getNomType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; //
                                }else  
				    	 return false; // Does not match.
                                
                        
                        });
                        
                        
                       
                        
                        // filter by Prix
                        filterbyPrix.setPredicate(Interventions -> {
				// If filter text is empty, display all persons.
				
                                                	  

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (String.valueOf(Interventions.getPrix()).indexOf(lowerCaseFilter)!=-1) {
					return true; 
                                }else  
				    	 return false; // Does not match.
                                 
                                
			});
                        
                        
                         // filter by descripition
                        filterbyDesc.setPredicate(Interventions -> {
				// If filter text is empty, display all persons.
				
                                                	   //System.out.println(tb.getItems().size());

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (Interventions.getDescripition().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; //
                                }else  
				    	 return false; // Does not match.
                                
                        
                        });
                        
                        
                        // filter by medecin
                       filterbyMedecin.setPredicate(Interventions -> {
				// If filter text is empty, display all persons.
				
                                                	   //System.out.println(tb.getItems().size());

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (Interventions.getMedecin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; //
                                }else  
				    	 return false; // Does not match.
                                
                        
                        });
                        
                        
                        // filter by Nomtype intervention
                        filterbyNomType.setPredicate(Interventions -> {
				// If filter text is empty, display all persons.
				
                                                	   

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                   
				
				 if (Interventions.getNomTypeIntervention().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; //
                                }else  
				    	 return false; // Does not match.
                                
                        
                        });
                        
                       NID.setText(""+filterbyNom.size());
                       PID.setText(""+filterbyPrix.size());
                       DID.setText(""+filterbyDesc.size());
                       MID.setText(""+filterbyMedecin.size());
                       IID.setText(""+filterbyNomType.size());
                           
                        
                        
                        
                        
                        
                        
                        
                        
                        
            });         
		  
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Interventions> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tl.setItems(sortedData);
    }
               
                        
    

  
    

   


  

    @FXML
    private void AjouterIntervention(ActionEvent event) {
        Interventions s= new Interventions(null,0,null,0,0); //lelement a ajoutet vide
        
        
        if (nomF.getText().isEmpty())    
        { JOptionPane.showMessageDialog(null,"Erreur! champ obligatoire, vous devez remplir un nom");
         return; 
        } 
        
         if ( !nomF.getText().matches("[a-zA-Z]+")) 
           
        {  JOptionPane.showMessageDialog(null,"Vous devez inserer que des lettres au niceau du champs (Nom)");
         return; 
        }     s.setNomType(nomF.getText()); // saisi non fini
        
      
          if (descF.getText().isEmpty())    
        { JOptionPane.showMessageDialog(null,"Erreur! champ obligatoire, vous devez remplir une descripition");
         return; 
        } 
        
         if ( !descF.getText().matches("[a-zA-Z]+")) 
           
        {  JOptionPane.showMessageDialog(null,"Vous devez inserer que des lettres au niceau du champs (Descripition)");
         return; 
        }     s.setDescripition(descF.getText()); // saisi desc fini
        
            
         if (prixF.getText().isEmpty())    
        { JOptionPane.showMessageDialog(null,"Erreur! champ obligatoire, vous devez remplir un prix");
         return; 
        } 
        
         if ( !prixF.getText().matches(".*[0-9].*")) 
           
        {  JOptionPane.showMessageDialog(null,"Vous devez inserer que des chiffres au niveau du champs (Prix)");
         return; 
        }     s.setPrix(Integer.parseInt(prixF.getText())); // saisi prix fini
       
        
        for (Map.Entry ele : map.entrySet())
        {  
            if(ele.getValue().equals(combo.getValue())){ 
                s.setIdmedecin(Integer.parseInt(ele.getKey().toString()));
            }
        }
        if (combo.getValue()==null)    
        { JOptionPane.showMessageDialog(null,"Erreur! champ obligatoire, vous devez preciser un medecin");
         return; 
        } // saisi medecin fini
        
         for (Map.Entry ele : mape.entrySet()) {
            if(ele.getValue().equals(combi.getValue())){ 
                s.setIdtypeintervention(Integer.parseInt(ele.getKey().toString()));
               
            }
            
        }
         
        if (combi.getValue()==null)    
        { JOptionPane.showMessageDialog(null,"Erreur! champ obligatoire, vous devez preciser un type intervention");
         return; 
        } 
            
        
         
        
        
        
       
         ss.ajouter(s);

  JOptionPane.showMessageDialog(null, " Intervention ajoutée !");
         showInterventions();
       
     }
    private void detailsInterventions(MouseEvent event) throws IOException {
        index=tl.getSelectionModel().getSelectedIndex();
        
        System.out.println(index);
        if(index<=-1)
            return;
        ll =tl.getSelectionModel().getSelectedItem();
     
        System.out.println(ll);
        id=ll.getId(); //recupere id element selectioné
        System.out.println("id : "+ id);
        
        
        nomF.setText(colNom.getCellData(index));
        prixF.setText(colNom.getCellData(index));
        descF.setText(colNom.getCellData(index));
        medecinF.setText(colNom.getCellData(index));
        typeF.setText(colDesc.getCellData(index));

    }

    @FXML
    private void ModifierInterventions(ActionEvent event) {
        
          Interventions s= new Interventions(0,null,0,null,0,0); //lelement a ajoutet vide
        s.setId(id);
        s.setNomType(nomF.getText());
        s.setDescripition(descF.getText());
        s.setPrix((int)Double.parseDouble(prixF.getText()));
        for (Map.Entry ele : map.entrySet()) {
            if(ele.getValue().equals(combo.getValue())){ 
                s.setIdmedecin(Integer.parseInt(ele.getKey().toString()));
               
            }
            
        }
        
        for (Map.Entry ele : mape.entrySet()) {
            if(ele.getValue().equals(combi.getValue())){ 
                s.setIdtypeintervention(Integer.parseInt(ele.getKey().toString()));
               
            }
            
            
        }
          ss.modifier(s);
          showInterventions();
    }

    @FXML
    private void showdetails(MouseEvent event) {
        
        index=tl.getSelectionModel().getSelectedIndex();
        
        System.out.println(index);
        if(index<=-1)
            return;
      ll =tl.getSelectionModel().getSelectedItem();
      
        System.out.println(ll);
        id=ll.getId();
        System.out.println("id : "+ id);
        
        
        nomF.setText(colNom.getCellData(index));
        prixF.setText(colPrix.getCellData(index).toString());
        descF.setText(colDesc.getCellData(index));
        combo.setValue(colidm.getCellData(index));
        combi.setValue(coltype.getCellData(index));
    }

    @FXML
    private void Openchat(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
          Parent root = loader.load();
          descF.getScene().setRoot(root);
         
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chart.fxml"));
          Parent root = loader.load();
          descF.getScene().setRoot(root);
    }

    @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        descF.getScene().setRoot(root);
    }

    @FXML
    private void gestionmedecin(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        descF.getScene().setRoot(root);
    }

    @FXML
    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
            descF.getScene().setRoot(root);
    }

    @FXML
    private void backInter(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             descF.getScene().setRoot(root);
    }
    }
    


                       
                    