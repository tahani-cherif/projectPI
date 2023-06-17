package comm.tourisme_sante.gui;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tourisme_sante.entities.Offre;
import comm.tourisme_sante.services.ServiceOffre;
import java.io.FileOutputStream;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField TFNom;
    @FXML
    private Pagination pagination;

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

    void returnhome(ActionEvent event) {

    }
    private void generateInvoicePDF(Offre offre) {
        try {
            // Create a new PDF document
            Document document = new Document();

            // Set the output file name and location
            String outputFile = "D:\\Documents\\PDF\\invoice_" + offre.getNom() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));

            // Open the document for writing
            document.open();
            Image logo = Image.getInstance("C:\\Users\\benbr\\OneDrive\\Images\\logo\\logo.jpg"); // Replace with the actual path to your logo image
        logo.scaleToFit(100, 100); // Adjust the width and height as desired
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);
         document.add(new Paragraph(" "));

        // Add the title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Details Offre", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add spacing before the table
        document.add(new Paragraph(" "));

            // Add data to the PDF document
            PdfPTable table = new PdfPTable(2);

        // Add data to the table
        table.addCell(createCell("Offre:", true));
        table.addCell(createCell(offre.getNom(), false));
        table.addCell(createCell("Type:", true));
        table.addCell(createCell(offre.getType(), false));
        table.addCell(createCell("Prix:", true));
        table.addCell(createCell(String.valueOf(offre.getPrix()), false));
        table.addCell(createCell("Pourcentage:", true));
        table.addCell(createCell(String.valueOf(offre.getPourcentage()), false));

        document.add(table);

            // Close the document
            document.close();

            System.out.println("PDF generated successfully for offre: " + offre.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
private PdfPCell createCell(String text, boolean isHeader) {
    PdfPCell cell = new PdfPCell(new Phrase(text, isHeader ? FontFactory.getFont(FontFactory.HELVETICA_BOLD) : FontFactory.getFont(FontFactory.HELVETICA)));
    cell.setBorderWidth(1f); 
    cell.setPadding(5);
    return cell;
}
    

    @FXML
    void selectionmedecin(MouseEvent event) {
       x=idTable.getSelectionModel().getSelectedItem();
        TFNom.setText(x.getNom());
        TFType.setText(x.getType());
        TFPrix.setText(Double.toString(x.getPrix()));
       TFPourcentage.setText(Double.toString(x.getPourcentage()));
        System.out.println(x.getNom());

    }
    private void configurePagination() {
        pagination.setPageFactory(this::createPage);
    }
    private TableView<Offre> createPage(int pageIndex) {
           columnNom.setCellValueFactory(new PropertyValueFactory<Offre,String>("nom"));
        ColumnType.setCellValueFactory(new PropertyValueFactory<Offre,String>("type"));
        ColumnPrix.setCellValueFactory(new PropertyValueFactory<Offre,String>("prix"));
        ColumnPourecntage.setCellValueFactory(new PropertyValueFactory<Offre,String>("pourcentage"));
       ObservableList<Offre> medecinList = FXCollections.observableList(so.afficherPagination(pageIndex));
      
      idTable.setItems(medecinList);
        return idTable;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurePagination();
      
       TableColumn<Offre, Void> colBtn = new TableColumn("Suprime");
              TableColumn<Offre, Void> colPrint = new TableColumn("Print");

       Callback<TableColumn<Offre, Void>, TableCell<Offre, Void>> cellFactory = new Callback<TableColumn<Offre, Void>, TableCell<Offre, Void>>() {
            @Override
            public TableCell<Offre, Void> call(final TableColumn<Offre, Void> param) {
                                      Button btn = new Button("Remove");
btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                     Button btnprint = new Button("Print");
btnprint.setStyle("-fx-background-color:white;"+"-fx-pref-width: 100px;");

                final TableCell<Offre, Void> cell = new TableCell<Offre, Void>() {


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
                                                    ObservableList<Offre> offreList = FXCollections.observableList(so.afficherPagination(5));
      
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
       
       
   Callback<TableColumn<Offre, Void>, TableCell<Offre, Void>> cellFactoryprint = new Callback<TableColumn<Offre, Void>, TableCell<Offre, Void>>() {
            @Override
            public TableCell<Offre, Void> call(final TableColumn<Offre, Void> param) {
                final TableCell<Offre, Void> cell = new TableCell<Offre, Void>() {

                                        private final Button btnprint = new Button("Print");


                    {
                        btnprint.setOnAction((ActionEvent event) -> {
                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de print offre");
                                alert.setHeaderText("Confirmation de print offre");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                       Offre data = getTableView().getItems().get(getIndex());
                                            
      generateInvoicePDF(data);
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
                            setGraphic(btnprint);
                        }
                    }
                };
                return cell;
            }
        };
       
       

                
                colPrint.setCellFactory(cellFactoryprint);
        colBtn.setCellFactory(cellFactory);


        idTable.getColumns().add(colBtn);
                idTable.getColumns().add(colPrint);

    }    

        @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        idTable.getScene().setRoot(root);
    }

   @FXML
    private void backType(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
            Parent root = loader.load();
         
             idTable.getScene().setRoot(root);
    }

    @FXML
    private void backInter(ActionEvent event)  throws IOException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             idTable.getScene().setRoot(root);
    }



    @FXML
    private void gestioncommande(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            Parent root = loader.load();
         
      idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionoffre(ActionEvent event) throws IOException {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();
         
      idTable.getScene().setRoot(root);
    }

    @FXML
    private void reservationgs(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationGui.fxml"));
            Parent root = loader.load();
         
           idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionproduit(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root = loader.load();   
      idTable.getScene().setRoot(root);
    }

 @FXML
    private void gestionpanier(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduitFront.fxml"));
            Parent root = loader.load();   
      idTable.getScene().setRoot(root);
    }

    @FXML
    private void gestionsearch(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchDate.fxml"));
            Parent root = loader.load();   
      idTable.getScene().setRoot(root);
    }
   
    
}
