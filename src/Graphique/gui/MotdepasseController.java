/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Graphique.gui;

import comm.tourisme_sante.services.serviceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
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
 * @author hamza
 */
public class MotdepasseController implements Initializable {
    
    
 
serviceUtilisateur u = new serviceUtilisateur();

    @FXML
    private TextField tfmail;
    
    public static int nbr = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoimail(ActionEvent event) throws IOException {
        if (u.isvalidmail(tfmail.getText())) {
            NouveauController.z = tfmail.getText() ;
        int max = 9999; 
        int min = 1000; 
        int range = (max - min) + 1;     
        nbr = (int)(Math.random() * range) + min;
        String host = "smtp.live.com";
        final String user = "sabrina.aloui@live.fr"; 
        final String password = "Inae 96681158"; 
        
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(user, password);
        }
    });

    try {
     
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sabrina.aloui@live.fr"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(tfmail.getText()));
        message.setSubject("code envoyé");
        
        String Content = "Bonjour,votre code de sécurité pour le mot de pass oublié est : " + nbr  + "<br> </br> Cordialement.";

        message.setContent(Content, "text/html; charset=utf-8");

       
        Transport.send(message);

        System.out.println("E-mail envoyé avec succès !");
        JOptionPane.showMessageDialog(null, "Mail envoyé avec succès !");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Checkcode.fxml"));
        Parent root = loader.load();
        tfmail.getScene().setRoot(root);
    } catch (MessagingException e) {
        e.printStackTrace();
        }
    } 
    else { 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mail n'existe pas");
            alert.setHeaderText(null);
            alert.setContentText("mail n'existe pas");
            alert.showAndWait(); 
            } }
 
}
    

