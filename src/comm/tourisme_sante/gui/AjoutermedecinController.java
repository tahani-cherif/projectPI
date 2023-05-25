/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.serviceMedecin;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutermedecinController implements Initializable {

    @FXML
    private TextField TFNom;
    @FXML
    private TextField TFPrenom;
    @FXML
    private TextField TFEmail;
    @FXML
    private TextField TFAdresse;
    @FXML
    private TextField TFNumero;
    @FXML
    private TextField TFSpecialite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutermedecin(ActionEvent event) throws IOException {
        
        serviceMedecin sm=new serviceMedecin();
        sm.ajouter(new medecins(TFNom.getText()+" "+TFPrenom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
        JOptionPane.showMessageDialog(null, "Medecin ajoutée !");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("affichemedecin.fxml"));
        Parent root = loader.load();
        TFNom.getScene().setRoot(root);
        AffichemedecinController dpc = loader.getController();
    }
    
}
