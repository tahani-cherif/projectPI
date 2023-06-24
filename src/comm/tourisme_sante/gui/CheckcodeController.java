/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CheckcodeController implements Initializable {

  @FXML
    private TextField tfcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void check(ActionEvent event) throws IOException {
        if (Integer.parseInt(tfcode.getText())== MotdepasseController.nbr) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Nouveau.fxml"));
                            Parent root = loader.load();
                            tfcode.getScene().setRoot(root);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Code invalide");
            alert.setHeaderText(null);
            alert.setContentText("Code invalide");
            alert.showAndWait();
            } }
    
    
    
}
