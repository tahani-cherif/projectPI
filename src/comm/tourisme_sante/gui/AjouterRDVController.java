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
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterRDVController implements Initializable {
    private medecins x;
    private Date date;
    @FXML
    private ComboBox<medecins> idmedecin;
    @FXML
    private DatePicker iddate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          serviceMedecin sm=new serviceMedecin();
      ObservableList<medecins> medecinList = FXCollections.observableList(sm.afficher());
      idmedecin.setItems(medecinList);
    }    

    @FXML
    private void daterdv(ActionEvent event) {
      //  date=iddate.getValue();
      
    }

    @FXML
    private void medecin(ActionEvent event) {
        x=idmedecin.getValue();
    }

    @FXML
    private void rdv(ActionEvent event) throws IOException {
          serviceRDV RDV=new serviceRDV();
          RDV.ajouter(new RDV(x.getId(),1,Date.valueOf(iddate.getValue())));
          JOptionPane.showMessageDialog(null, "RDV ajoutée !");
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheRDV.fxml"));
        Parent root = loader.load();
        idmedecin.getScene().setRoot(root);
        AfficheRDVController dpc = loader.getController();
    }
    
}
