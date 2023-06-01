/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.agence_medicale.Interventions;
import com.mycompany.agence_medicale.TypeInterventions;
import com.mycompany.agence_medicale.medecins;
import com.mycompany.service.Service_TypeInterventions;
import com.mycompany.service.ServicesInterventions;
import com.mycompany.utils.DataSourceA;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class FullInterController implements Initializable {
    
     private Connection cnx = DataSourceA.getInstance().getCnx();
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
    private Button btS;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private ComboBox<String> combi;
    @FXML
    private TextField searchField;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showInterventions();
       
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

      
      
      
      
      // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Interventions> filteredData = new FilteredList<>(listType, b -> true);
		
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
				}
                                
                                
				else if (String.valueOf(Interventions.getPrix()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
                        
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
    private void SupprimerInterventions(ActionEvent event) {
          ss.supprimer(ll);
          showInterventions();
    }

    @FXML
    private void AjouterIntervention(ActionEvent event) {
        Interventions s= new Interventions(null,0,null,0,0); //lelement a ajoutet vide
        s.setNomType(nomF.getText());
        s.setDescripition(descF.getText());
        s.setPrix(Integer.parseInt(prixF.getText()));
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
    }
    


                       
                    