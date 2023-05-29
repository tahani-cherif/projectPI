package javafxcrud;

import entities.Offre;
import static java.lang.Double.parseDouble;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.ServiceOffre;

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
    private TableView<Offre> idTable;

    @FXML
    private TableColumn<Offre, String> columnNom;

    @FXML
    private TableColumn<Offre, String> ColumnType;

    @FXML
    private TableColumn<Offre, String> ColumnPrix;

    @FXML
    private TableColumn<Offre, String> ColumnPourecntage;
      ServiceOffre so=new ServiceOffre();
    private  Offre x=null;
    
   @FXML
    void ajouterOffre(ActionEvent event) {
        so.ajouter(new Offre(parseDouble(TFPourcentage.getText()),parseDouble(TFPrix.getText()),TFNom.getText(),TFType.getText()));
        JOptionPane.showMessageDialog(null, "Offre ajoutée !");
       // medecinList.add(new medecins(TFNom.getText(),TFEmail.getText(),TFAdresse.getText(),parseInt(TFNumero.getText()),TFSpecialite.getText()));
        TFNom.setText("");
        TFType.setText("");
        TFPrix.setText("");
        TFPourcentage.setText("");
         ObservableList<Offre> offreList = FXCollections.observableList(so.afficher());
         idTable.setItems(offreList);
    }

    @FXML
    void modiffierOffre(ActionEvent event) {
           System.out.println(x);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier offre");
        alert.setHeaderText("Confirmation de modifier offre");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
             so.modifier(new Offre(x.getId(),parseDouble(TFPourcentage.getText()),parseDouble(TFPrix.getText()),TFNom.getText(),TFType.getText()));
         ObservableList<Offre> medecinList = FXCollections.observableList(so.afficher());
        idTable.setItems(medecinList);
         TFNom.setText("");
          TFType.setText("");
          TFPrix.setText("");
          TFPourcentage.setText("");
        } else {
             TFNom.setText("");
          TFType.setText("");
          TFPrix.setText("");
          TFPourcentage.setText("");
        }

    }

    @FXML
    void returnhome(ActionEvent event) {

    }

    @FXML
    void selectionmedecin(MouseEvent event) {
                x=idTable.getSelectionModel().getSelectedItem();
        TFNom.setText(x.getNom());
        TFType.setText(x.getType());
        TFPrix.setText(Double.toString(x.getPrix()));
       TFPourcentage.setText(Double.toString(x.getPourcentage()));

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          columnNom.setCellValueFactory(new PropertyValueFactory<Offre,String>("nom"));
        ColumnType.setCellValueFactory(new PropertyValueFactory<Offre,String>("type"));
        ColumnPrix.setCellValueFactory(new PropertyValueFactory<Offre,String>("prix"));
        ColumnPourecntage.setCellValueFactory(new PropertyValueFactory<Offre,String>("pourcentage"));
       ObservableList<Offre> medecinList = FXCollections.observableList(so.afficher());
      
      idTable.setItems(medecinList);
      
       TableColumn<Offre, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<Offre, Void>, TableCell<Offre, Void>> cellFactory = new Callback<TableColumn<Offre, Void>, TableCell<Offre, Void>>() {
            @Override
            public TableCell<Offre, Void> call(final TableColumn<Offre, Void> param) {
                final TableCell<Offre, Void> cell = new TableCell<Offre, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier offre");
                                alert.setHeaderText("Confirmation de modifier offre");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       Offre data = getTableView().getItems().get(getIndex());
                                                    System.out.println("selectedData: " + data);
                                                    so.supprimer(data);
                                                   // medecinList.remove(data);
                                                    ObservableList<Offre> offreList = FXCollections.observableList(so.afficher());
      
                                                   idTable.setItems(offreList);
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

        idTable.getColumns().add(colBtn);
    }    

   
    
}
