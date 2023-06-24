/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import comm.tourisme_sante.services.serviceUtilisateur;
import java.io.IOException;
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
 * @author User
 */
public class NouveauController implements Initializable {
   @FXML
    private TextField tfnouveau;
    @FXML
    private TextField tfconfirm;
    
    public static String z;
    serviceUtilisateur u = new serviceUtilisateur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nouveaumotdepass(ActionEvent event) throws IOException {

        String password = tfnouveau.getText();
        String confirmPassword = tfconfirm.getText();

        if (password.equals(confirmPassword)) {
            System.out.println("Mot de passe valide !");
            u.modifpassword(password, z);
            JOptionPane.showMessageDialog(null, "Mot de pass modifié avec succès !");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
             Parent root = loader.load();
             tfnouveau.getScene().setRoot(root);
        } else {
            System.out.println("Les mots de passe ne correspondent pas.");
              JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas!");
        }
    }
}
