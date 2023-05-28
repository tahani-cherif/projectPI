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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
        JOptionPane.showMessageDialog(null, "RDV ajoutée !");
       iddate.setValue(null);
       idmedecin.setValue("");
    }

    @FXML
    private void modifer(ActionEvent event) {
    }
    
}
