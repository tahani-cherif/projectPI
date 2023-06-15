/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.jfoenix.controls.JFXTimePicker;
import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.Utilisateur;
import com.tourisme_sante.entities.admin;
import com.tourisme_sante.entities.client;
import com.tourisme_sante.entities.medecins;
import com.tourisme_sante.utils.Datasource;
import comm.tourisme_sante.services.serviceMedecin;
import comm.tourisme_sante.services.serviceRDV;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    RDV x=null;
    @FXML
    private TextField idsearch;
    @FXML
    private JFXTimePicker test;
    @FXML
    private TableColumn<RDV, String> idheure;
    
    public boolean etat=false;
    Connection cnx = Datasource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//         Button btnw = new Button("", new FontAwesomeIconView(FontAwesomeIcon.TRASH));
//         btnw.setStyle("-fx-background-color:#Fb6868");
         nommedecin.setCellValueFactory(new PropertyValueFactory<RDV, String>("fullName"));
        nomuser.setCellValueFactory(new PropertyValueFactory<RDV, String>("nomuser"));
        date.setCellValueFactory(new PropertyValueFactory<RDV, Date>("dateRDV"));
           idheure.setCellValueFactory(new PropertyValueFactory<RDV, String>("heureRDV"));
         ObservableList<RDV> listerdv = FXCollections.observableList(rdv.afficher());
        table.setItems(listerdv);
         TableColumn<RDV, Void> colBtn = new TableColumn("Suprime");
       Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>> cellFactory = new Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>>() {
            @Override
            public TableCell<RDV, Void> call(final TableColumn<RDV, Void> param) {
                 FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                 deleteIcon.setStyle("-fx-fill:#ffffff;");
                 Button btn = new Button("Remove", deleteIcon);
                   btn.setStyle("-fx-background-color:#Fb6868;"+"-fx-pref-width: 100px;"+"-fx-text-fill: white");
                final TableCell<RDV, Void> cell = new TableCell<RDV, Void>() {

                
                         
                          {
                        btn.setOnAction((ActionEvent event) -> {
                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier RDV");
                                alert.setHeaderText("Confirmation de modifier RDV");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                        RDV data = getTableView().getItems().get(getIndex());
                                 System.out.println("selectedData: " + data);
                                 rdv.supprimer(data);
                                   ObservableList<RDV> rdvList = FXCollections.observableList(rdv.afficher());
                                  table.setItems(rdvList);
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
        table.getColumns().add(colBtn);
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
        //fltrage
         FilteredList<RDV> filteredData = new FilteredList<>(listerdv, b -> true);
         idsearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rdv -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (rdv.getNomuser().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (rdv.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(rdv.getDateRDV()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
                 SortedList<RDV> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
      
    }    

    @FXML
    private void daterdv(ActionEvent event) {
    }

    @FXML
    private void medecin(ActionEvent event) {
    }

    @FXML
    private void ajouterrdv(ActionEvent event) {
        if(!etat){
          Connection cnx = Datasource.getInstance().getCnx();
       String nom2 = idmedecin.getValue();
        LocalDate heure = iddate.getValue();
         LocalTime date = test.getValue();
        System.out.println(heure);
          if (nom2==null || heure==null || date==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();}else{
                String req2= "SELECT * FROM rdv where 	idmedecin=? and dateRDV=? and heureRDV=?;";
                  try {
           PreparedStatement stt = cnx.prepareStatement(req2);
           for (Map.Entry ele : map.entrySet()) {
            if(ele.getValue().equals(idmedecin.getValue())){     
               stt.setInt(1,Integer.parseInt(ele.getKey().toString()));
            }}
           stt.setDate(2,  Date.valueOf(iddate.getValue()));
           stt.setString(3, test.getValue().getHour()+":"+test.getValue().getMinute());
            ResultSet rss = stt.executeQuery();
            if(rss.next()) {
            
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("déjà rendez-vous");
            alert.setHeaderText(null);
            alert.setContentText("Changer date de rondez-vous.");
            alert.showAndWait();
            }else{
                 for (Map.Entry ele : map.entrySet()) {
            if(ele.getValue().equals(idmedecin.getValue())){     
                RDV.ajouter(new RDV(Integer.parseInt(ele.getKey().toString()), 1, Date.valueOf(iddate.getValue()),test.getValue().getHour()+":"+test.getValue().getMinute()));
            }
        }
   //get utilisateur by id and envoi de mail
       
        Utilisateur liste= null;
        
        String req = "SELECT * FROM Utilisateur where id=?;";
        try {
           PreparedStatement st = cnx.prepareStatement(req);
             st.setInt(1,Integer.valueOf("1"));
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Utilisateur x;
             liste=new client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getInt("number"),rs.getString("role"));
               // Envoi de l'e-mail
    String host = "smtp.live.com";
    final String user = "cheriftahani92@gmail.com"; // Remplacez par votre adresse e-mail Outlook/Hotmail
    final String password = "tahani123"; // Remplacez par votre mot de passe Outlook/Hotmail
 
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
        message.setFrom(new InternetAddress("cheriftahani92@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(liste.getEmail()));
        message.setSubject("Confirmation de Rendez-vous");


// Définir le contenu du message au format HTML
     message.setContent( "<div><p>Confirmation de Rendez-vous de "
          +liste.getNom()+" " + liste.getPrenom()+" a "+iddate.getValue()+" "+test.getValue().getHour()+":"+test.getValue().getMinute()+"</div>", "text/html; charset=utf-8");

        // Envoi du message
        Transport.send(message);

        System.out.println("E-mail envoyé avec succès !");
        ObservableList<RDV> listerdv = FXCollections.observableList(rdv.afficher());
       table.setItems(listerdv);
        JOptionPane.showMessageDialog(null, "RDV ajoutée !");
        
       iddate.setValue(null);
       idmedecin.setValue("");
       test.setValue(null);
    } catch (MessagingException e) {
        e.printStackTrace();
    }

 
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
            // end envoi de mail
 
                }
                
            }}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
            // end envoi de mail
 
                }
               
              
       }

    }
        else{
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("déjà rendez-vous");
            alert.setHeaderText(null);
            alert.setContentText(" rondez-vous existe deja. ");
            alert.showAndWait();
        }
    }

    @FXML
    private void modifer(ActionEvent event) {
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de modifier RDV");
                                alert.setHeaderText("Confirmation de modifier RDV");
                                alert.setContentText("Êtes-vous sûr?");

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK){
                                    
                                    
                                     String req2= "SELECT * FROM rdv where 	idmedecin=? and dateRDV=? and heureRDV=?;";
                  try {
           PreparedStatement stt = cnx.prepareStatement(req2);
           for (Map.Entry ele : map.entrySet()) {
            if(ele.getValue().equals(idmedecin.getValue())){     
               stt.setInt(1,Integer.parseInt(ele.getKey().toString()));
            }}
           stt.setDate(2,  Date.valueOf(iddate.getValue()));
           stt.setString(3, test.getValue().getHour()+":"+test.getValue().getMinute());
            ResultSet rss = stt.executeQuery();
            if(rss.next()) {
            
             Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("déjà rendez-vous");
            alert2.setHeaderText(null);
            alert2.setContentText("Changer date de rondez-vous.");
            alert2.showAndWait();
            }else{
                                       for (Map.Entry ele : map.entrySet()) {
                                        if(ele.getValue().equals(idmedecin.getValue())){     
                                            RDV.modifier(new RDV(x.getId(),Integer.parseInt(ele.getKey().toString()), 1, Date.valueOf(iddate.getValue()),test.getValue().getHour()+":"+test.getValue().getMinute()));
                                        }
                                          
                                    }
                                        //get utilisateur by id and envoi de mail
        Utilisateur liste= null;
        
        String req = "SELECT * FROM Utilisateur where id=?;";
        try {
           PreparedStatement st = cnx.prepareStatement(req);
             st.setInt(1,Integer.valueOf("1"));
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Utilisateur x;
             liste=new client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getInt("number"),rs.getString("role"));
               // Envoi de l'e-mail
    String host = "smtp.live.com";
    final String user = "cheriftahani92@gmail.com"; // Remplacez par votre adresse e-mail Outlook/Hotmail
    final String password = "tahani123"; // Remplacez par votre mot de passe Outlook/Hotmail
 
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
        message.setFrom(new InternetAddress("cheriftahani92@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(liste.getEmail()));
        message.setSubject("Confirmation de Rendez-vous");


// Définir le contenu du message au format HTML
message.setContent( "<div><p>Confirmation de Rendez-vous de "
        +liste.getNom()+" " + liste.getPrenom()+" a "+iddate.getValue()+" "+test.getValue().getHour()+":"+test.getValue().getMinute()+"</div>", "text/html; charset=utf-8");

        // Envoi du message
        Transport.send(message);

        System.out.println("E-mail envoyé avec succès !");
    } catch (MessagingException e) {
        e.printStackTrace();
    }

 
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
            // end envoi de mail
 
                } etat=false;
                                     ObservableList<RDV> listerdv = FXCollections.observableList(rdv.afficher());
                                    table.setItems(listerdv);
                                   iddate.setValue(null);
                                   idmedecin.setValue("");
                                   test.setValue(null);
            } }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
            // end envoi de mail
 
                }   } else { etat=false;
                                     iddate.setValue(null);
                                   idmedecin.setValue("");
                                   test.setValue(null);
                                }
         
    }

    @FXML
    private void cliked(MouseEvent event) {
        x=table.getSelectionModel().getSelectedItem();
        iddate.setValue(x.getDateRDV().toLocalDate());
        idmedecin.setValue(x.getFullName());
        test.setValue(LocalTime.parse(x.getHeureRDV()));
        etat=true;
    }

    @FXML
    private void gestionrendezvous(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceRDV.fxml"));
        Parent root = loader.load();
        iddate.getScene().setRoot(root);
    }

    @FXML
    private void gestionmedecin(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacemedecin.fxml"));
        Parent root = loader.load();
        iddate.getScene().setRoot(root);
    }
    
}
