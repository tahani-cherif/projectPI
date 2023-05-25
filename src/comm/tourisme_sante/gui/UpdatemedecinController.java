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
public class UpdatemedecinController implements Initializable {
    public static medecins x=null;

    @FXML
    private TextField updfullname;
    @FXML
    private TextField updatemail;
    @FXML
    private TextField updatadresse;
    @FXML
    private TextField updatnumero;
    @FXML
    private TextField updatspecialite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("test"+x);
        updfullname.setText(x.getFullName());
        updatadresse.setText(x.getAdresse());
         updatemail.setText(x.getEmail());
          updatnumero.setText(String.valueOf(x.getNumero()));
           updatspecialite.setText(x.getSpecialite());
           
        
    }    

    @FXML
    private void update(ActionEvent event) throws IOException {
         serviceMedecin sm=new serviceMedecin();
        sm.modifier(new medecins(x.getId(),updfullname.getText(),updatemail.getText(),updatadresse.getText(),parseInt(updatnumero.getText()),updatspecialite.getText()));
        JOptionPane.showMessageDialog(null, "Medecin modifier !");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("affichemedecin.fxml"));
        Parent root = loader.load();
        updatspecialite.getScene().setRoot(root);
        AffichemedecinController dpc = loader.getController();
    }
    
}
