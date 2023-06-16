/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField TFNom;
    @FXML
    private TextField TFType;
    @FXML
    private TextField TFPourcentage;
    @FXML
    private TextField TFPrix;
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<?> idTable;
    @FXML
    private TableColumn<?, ?> columnNom;
    @FXML
    private TableColumn<?, ?> ColumnType;
    @FXML
    private TableColumn<?, ?> ColumnPrix;
    @FXML
    private TableColumn<?, ?> ColumnPourecntage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterOffre(ActionEvent event) {
    }

    @FXML
    private void modiffierOffre(ActionEvent event) {
    }

    @FXML
    private void selectionmedecin(MouseEvent event) {
    }
    
}
