/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tourisme_sante.controller;
import javafx.beans.property.SimpleStringProperty;

import com.tourisme_sante.entities.Hotel;
import com.tourisme_sante.entities.Agence;
import comm.tourisme_sante.services.ServiceHotel;
import comm.tourisme_sante.services.ServiceAgence;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static java.lang.Integer.parseInt;
import java.lang.Float;
import javafx.beans.binding.Bindings;
import com.tourisme_sante.utils.Datasource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.Comparator;
import java.util.Collections;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.Map;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.HashMap;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import java.util.Optional;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




/**
 * FXML Controller class
 *
 * @author 21626
 */
public class CrudHotelController implements Initializable {

    @FXML
    private TextField idNom;
    @FXML
    private TextField idAdresse;
    @FXML
    private TextField idClassification;
    @FXML
    private TextField idEmail;
    @FXML
    private TextField idTéléphone;
    @FXML
    private TextField idPrix;

    @FXML
    private ComboBox<String> idAgence;

    @FXML
    private TableView<Hotel> tableH;
    @FXML
    private TableColumn<Hotel, String> colNameH;
    @FXML
    private TableColumn<Hotel, String> colAdresse;
    @FXML
    private TableColumn<Hotel, Integer> colNbr;
    @FXML
    private TableColumn<Hotel, String> colEmail;
    @FXML
    private TableColumn<Hotel, Integer> colTel;
    @FXML
    private TableColumn<Hotel, Float> colPrix;
    @FXML
    private TableColumn<Hotel, Integer> colLikes;
    @FXML
    private TableColumn<Hotel, String> colAgnece;

    ServiceHotel sh = new ServiceHotel();

    Map<Integer, String> map = new HashMap<>();
    ServiceAgence sa = new ServiceAgence();
    @FXML
    private Button idLike;
    @FXML
    private Button idDislike;
    @FXML

    private Label idCount;

    private SimpleIntegerProperty likeCount = new SimpleIntegerProperty(0);

    @FXML
    private ComboBox<String> idOrderby;
    private SimpleBooleanProperty sortAscending = new SimpleBooleanProperty(true);
    private ObservableList<Hotel> hotelList;
    Hotel H = null;
    @FXML
    private Button idAddBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colNameH.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Hotel, String>("adresse"));
        colNbr.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("classification"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Hotel, String>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("telephone"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Hotel, Float>("prix"));
        colLikes.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("likes"));
        colAgnece.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nomAgence"));

       // idCount.textProperty().bind(likeCount.asString());
                idCount.textProperty().bind(Bindings.convert(likeCount));


colAgnece.setCellValueFactory(cellData -> {
    int agenceId = cellData.getValue().getIdAgence();
    String agenceNom = map.get(agenceId);
    return new SimpleStringProperty(agenceNom);
});
        ObservableList<Hotel> listeH = FXCollections.observableList(sh.afficher());
        tableH.setItems(listeH);
        TableColumn<Hotel, Void> colBtn = new TableColumn("Suprime");

        Callback<TableColumn<Hotel, Void>, TableCell<Hotel, Void>> cellFactory = new Callback<TableColumn<Hotel, Void>, TableCell<Hotel, Void>>() {
            @Override
            public TableCell<Hotel, Void> call(final TableColumn<Hotel, Void> param) {
                             
                             FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                 deleteIcon.setStyle("-fx-fill:#ffffff;");
                 Button btn = new Button("Remove", deleteIcon);
                   btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                 
                 
                final TableCell<Hotel, Void> cell = new TableCell<Hotel, Void>() {


                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de supprimer hotel");
                            alert.setHeaderText("Confirmation de supprimer hotel");
                            alert.setContentText("Êtes-vous sûr?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                Hotel data = getTableView().getItems().get(getIndex());
                                System.out.println("selectedData: " + data);
                                sh.supprimer(data);
                                ObservableList<Hotel> hotelList = FXCollections.observableList(sh.afficher());
                                tableH.setItems( hotelList );
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
        tableH.getColumns().add(colBtn);
        List<Agence> listAgence = sa.afficher();

        for (int i = 0; i < listAgence.size(); i++) {
            map.put(listAgence.get(i).getId(), listAgence.get(i).getNom());
        }
        ObservableList list = FXCollections.observableArrayList();
        for (Map.Entry ele : map.entrySet()) {
            list.add(ele.getValue());
        }

        idAgence.setItems(list);

        // Populate the ComboBox with sorting options
        ObservableList<String> sortOptions = FXCollections.observableArrayList(
                "Ascending", "Descending");
        idOrderby.setItems(sortOptions);

        // Set a listener to apply sorting when the selection changes
        idOrderby.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.equals("Ascending")) {
                        sortTableView(true);
                    } else if (newValue.equals("Descending")) {
                        sortTableView(false);
                    }
                });

        idOrderby.setOnAction(event -> {
            sortAscending.set(idOrderby.getValue().equals("Ascending"));
            sortAscending.set(idOrderby.getValue().equals("Desendent"));

            updateTableViewSorting();

        });

    }

    @FXML
    private void likebtn(ActionEvent event) {
        likeCount.set(likeCount.get() + 1);

    }

 
@FXML
private void dislikeBtn(ActionEvent event) {
    int count = likeCount.get(); // Get the current count value
    
    if (count > 0) {
        count--; // Decrement the count
        likeCount.set(count); // Update the likeCount property with the new value
        
        idCount.setText(Integer.toString(count)); // Update the idCount label with the new count value
    }
}

    @FXML
    private void AJouterHotel(ActionEvent event) {
System.out.println("ajouter");
          if (idNom.getText().trim().equals("") ||
        idAdresse.getText().trim().equals("") ||
        idClassification.getText().trim().equals("") || 
        idTéléphone.getText().trim().equals("") ||
        idPrix.getText().trim().equals("")||
        idEmail.getText().trim().equals("")) {

    Alert alert2 = new Alert(Alert.AlertType.WARNING);
    alert2.setTitle("Champs vides");
    alert2.setHeaderText(null);
    alert2.setContentText("Veuillez remplir tous les champs.");
    alert2.showAndWait();

} else
    if (!isValidEmail(idEmail.getText())) {
        // Email is valid, perform the desired action

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Email invalide");
        alert.setHeaderText(null);
        alert.setContentText("Email invalide.");
        alert.showAndWait();
    } else
        if (!isUniqueEmail(idEmail.getText())) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email non unique");
            alert.setHeaderText(null);
            alert.setContentText("L'email n'est pas unique.");
            alert.showAndWait();
        } else
            // Email is valid and unique, perform the desired action
          //  emailSet.add(emailAgence.getText());

            if (!isValidPhoneNumber(idTéléphone.getText())) {
                // Phone number is valid, perform the desired action

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Numéro de téléphone invalide");
                alert.setHeaderText(null);
                alert.setContentText("Numéro de téléphone invalide.");
                alert.showAndWait();
            } else
                if (!validateAddress(idAdresse.getText())) {
                     
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Adresse invalide");
                    alert.setHeaderText(null);
                    alert.setContentText("Adresse invalide.");
                    alert.showAndWait();
                } else
                
                    if (!isValidName(idNom.getText())) {
 
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Nom invalide");
                        alert.setHeaderText(null);
                        alert.setContentText("Nom invalide.");
                        alert.showAndWait();
                    } else
                        
                        if (!validateClassification(idClassification.getText())) {
 
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle(" nombre Classification invalide l");
                        alert.setHeaderText(null);
                        alert.setContentText("nombre Classification invalide l");
                        alert.showAndWait();
                    } else 
                    if (!validatePrix(idPrix.getText())) {
 
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle(" prix invalide l");
                        alert.setHeaderText(null);
                        alert.setContentText("prix invalide l");
                        alert.showAndWait();
                    } 
     
        else if (idAgence.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle(" vous devez choisir agencel");
                        alert.setHeaderText(null);
                        alert.setContentText("vous devez choisir agence l");
                        alert.showAndWait();
        }else {

   // All validations passed, perform the desired action
   /*      String selectedAgence = idAgence.getValue();
        if (selectedAgence == null || selectedAgence.trim().isEmpty()) {
            idAgence.getSelectionModel().selectFirst(); // Select the first value
   */
        for (Map.Entry ele : map.entrySet()) {
            if (ele.getValue().equals(idAgence.getValue())) {
                System.out.println("ajouter3");
                sh.ajouter(new Hotel(idNom.getText(), idAdresse.getText(), parseInt(idClassification.getText()), idEmail.getText(), parseInt(idTéléphone.getText()),
                        Float.parseFloat(idPrix.getText()), parseInt(idCount.getText()), Integer.parseInt(ele.getKey().toString())));

            }
        }
                    
        ObservableList<Hotel> listeHotel = FXCollections.observableList(sh.afficher());
        tableH.setItems(listeHotel);
        
        
        
        
        
        // Envoi de l'e-mail
    String host = "smtp.live.com";
final String user = "sabrina.aloui@live.fr"; 
        final String password =  "Inae 96681158";// Remplacez par votre mot de passe Outlook/Hotmail
 
    // Configuration des propriétés JavaMail
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    // Création de la session JavaMail
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(user, password);
        }
    });

    try {
        // Création du message MIME
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sabrina.aloui@live.fr"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("maryembengarfa@gmail.com"));
        message.setSubject("Confirmation d'abonnement");

   


String content =  "Bonjour " + idNom.getText() + ",<br/><br/>"
+" Voici  les détails de votre Ajout :<br/><br/>"
        + "<p>  Adresse hotel : " + idAdresse.getText() + "</p>"
        + "<p>Classification : " + idClassification.getText() + "</p>"
        + "<p> Numer de Téléphone: " + idTéléphone.getText() + "</p>"
        + "<p>Agence: " + idAgence.getValue() + " dinars" +"</p>"
                + "<p>Prix : " + idPrix.getText() +  "</p>" 
        + "<p>Nombre de like: " + idLike.getText() +  "</p>" 
        + "Cordialement,";


// Définir le contenu du message au format HTML
message.setContent(content, "text/html; charset=utf-8");

        // Envoi du message
        Transport.send(message);

        System.out.println("E-mail envoyé avec succès !");
   //     JOptionPane.showMessageDialog(null, "Mail envoyé avec succès !");
        JOptionPane.showMessageDialog(null, "Hotel  ajoutée !");
       idAgence.setValue("");
    } catch (MessagingException e) {
        e.printStackTrace();
    }
        
                    }}
   
    private void updateTableViewSorting() {
        Comparator<Hotel> comparator = Comparator.comparingInt(Hotel::getLikes);
        if (!sortAscending.get()) {
            comparator = comparator.reversed();
        }
        tableH.getItems().sort(comparator);
        if (!sortAscending.get()) {
            Collections.reverse(tableH.getItems());
        }
    }

    private void sortTableView(boolean ascending) {
        Comparator<Hotel> comparator = Comparator.comparingInt(Hotel::getLikes);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        tableH.getItems().sort(comparator);
    }

    @FXML
    private void modifier(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modifier hotel");
        alert.setHeaderText("Confirmation de modifier hotel");
        alert.setContentText("Êtes-vous sûr?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
                  if (idNom.getText().trim().equals("") ||
        idAdresse.getText().trim().equals("") ||
        idClassification.getText().trim().equals("") || 
        idTéléphone.getText().trim().equals("") ||
        idPrix.getText().trim().equals("")||
        idEmail.getText().trim().equals("")) 
{

    Alert alert2 = new Alert(Alert.AlertType.WARNING);
    alert2.setTitle("Champs vides");
    alert2.setHeaderText(null);
    alert2.setContentText("Veuillez remplir tous les champs.");
    alert2.showAndWait();

} else
    if (!isValidEmail(idEmail.getText())) {
        // Email is valid, perform the desired action

        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setTitle("Email invalide");
        alert2.setHeaderText(null);
        alert2.setContentText("Email invalide.");
        alert2.showAndWait();
    } else
        if (!isUniqueEmail(idEmail.getText())) {
              Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Email non unique");
            alert2.setHeaderText(null);
            alert2.setContentText("L'email n'est pas unique.");
            alert2.showAndWait();
        } else
            // Email is valid and unique, perform the desired action
          //  emailSet.add(emailAgence.getText());

            if (!isValidPhoneNumber(idTéléphone.getText())) {
                // Phone number is valid, perform the desired action

                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Numéro de téléphone invalide");
                alert2.setHeaderText(null);
                alert2.setContentText("Numéro de téléphone invalide.");
                alert2.showAndWait();
            } else
                if (!validateAddress(idAdresse.getText())) {
                     
                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Adresse invalide");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Adresse invalide.");
                    alert2.showAndWait();
                } else
                
                    if (!isValidName(idNom.getText())) {
 
                        Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle("Nom invalide");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Nom invalide.");
                        alert2.showAndWait();
                    } else
                        
                        if (!validateClassification(idClassification.getText())) {
 
                        Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" nombre Classification invalide l");
                        alert2.setHeaderText(null);
                        alert2.setContentText("nombre Classification invalide l");
                        alert2.showAndWait();
                    } else 
                    if (!validatePrix(idPrix.getText())) {
 
                        Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" prix invalide l");
                        alert2.setHeaderText(null);
                        alert2.setContentText("prix invalide l");
                        alert2.showAndWait();
                    } 
        else if (idAgence.getSelectionModel().isEmpty())
        {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
                        alert2.setTitle(" vous devez choisir agencel");
                        alert2.setHeaderText(null);
                        alert2.setContentText("vous devez choisir agence l");
                        alert2.showAndWait();
        }else {
        
            for (Map.Entry ele : map.entrySet()) {
                if (ele.getValue().equals(idAgence.getValue())) {
                    sh.modifier(new Hotel(H.getId(),idNom.getText(), idAdresse.getText(), parseInt(idClassification.getText()), idEmail.getText(), parseInt(idTéléphone.getText()),
                            Float.parseFloat(idPrix.getText()), parseInt(idCount.getText()), Integer.parseInt(ele.getKey().toString())));
                }
            }
            ObservableList<Hotel> listehotels = FXCollections.observableList(sh.afficher());
            tableH.setItems(listehotels);
                      System.out.println(listehotels);
   Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Succès");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText( "Hotel  modifiée!");
                        successAlert.showAndWait();                        
                     
                    
     idAgence.setValue("");
            idAgence.setValue("");
            idTéléphone.setText("");
            idEmail.setText("");
            idPrix.setText("");
            idAdresse.setText("");
            idNom.setText("");
            idClassification.setText("");
            likeCount.set(0);
            

        } 
    }
    }
    @FXML
    private void cliked(MouseEvent event) {
        H = tableH.getSelectionModel().getSelectedItem();
        idNom.setText(H.getNom());
        idEmail.setText(H.getEmail());
//        idCount.textProperty().unbind();
//        
//        idCount.setText(H.getLikes()+"");
        idAdresse.setText(H.getAdresse());
        idTéléphone.setText(Integer.toString(H.getTelephone()));
        idPrix.setText(String.valueOf(H.getPrix()));
        idClassification.setText(Integer.toString(H.getClassification()));
        idAgence.setValue(H.getNomAgence());
    }
    
    
    
        private boolean isValidEmail(String email) {
        // Simple email validation using a regular expression
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

   private boolean isValidPhoneNumber(String phoneNumber) {
        // Simple phone number validation using a regular expression
        // Accepts a 10-digit number starting with 0 or 1
        String phoneRegex = "^[925]\\d{7}$";
        return phoneNumber.matches(phoneRegex);
    }
   
       private boolean isValidName(String name) {
        // Simple name validation using a regular expression
        // Accepts alphabetic characters and spaces, minimum length of 2
        String nameRegex = "^[A-Za-z\\s]{4,}$";
        return name.matches(nameRegex);
    }
private boolean isUniqueEmail(String email) {
    Connection cnx = Datasource.getInstance().getCnx();
    String req = "SELECT * FROM hotel WHERE email=?";
    
    try (PreparedStatement st = cnx.prepareStatement(req)) {
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        
        return !rs.next(); // Return true if no rows were found, indicating that the email is unique
    } catch (SQLException e) {
        // Handle any exceptions that may occur
        e.printStackTrace();
        return false; // Return false in case of an error
    }
}

      private boolean validateAddress(String address) {

        return address.length() >= 5; // Exemple : l'adresse doit avoir au moins 5 caractères
    }
/*      private boolean validateLikes() {
    return  Integer.parseInt(idCount.getText()) >= 0;
} */
      private boolean validateClassification( String cl) {
    return cl.matches("[1-5]");
}
private boolean validatePrix( String prix )  {
    return prix.matches("\\d*(\\.\\d{0,2})?") && Double.parseDouble(prix) > 0;
}


    @FXML
    private void gestiontransport(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/CrudTransport.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gestionagence(ActionEvent event) throws IOException {
  
               Parent root = FXMLLoader.load(getClass().getResource("../com/tourisme_sante/views/CrudAgence.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        @FXML
    private void gestionhotel(ActionEvent event) throws IOException {
  
               Parent root = FXMLLoader.load(getClass().getResource("../com/tourisme_sante/views/CrudHotel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
