/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class ChatboxController implements Initializable {

    @FXML
    private ListView<String> messageListView;
    @FXML
    private TextField userInputField;
    @FXML
    private Button sendButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sendButton.setOnAction(event -> sendMessage());
        userInputField.setOnAction(event -> sendMessage());
    }
    private void sendMessage() {
        String userInput = userInputField.getText();
        messageListView.getItems().add("You: " + userInput);
        userInputField.clear();

        // Logique pour générer une réponse du chatbot
        String response = generateChatbotResponse(userInput);
        messageListView.getItems().add("Chatbot: " + response);
    }
    
private String generateChatbotResponse(String userInput) {
        // Logique pour générer une réponse du chatbot
        String response;

        if (userInput.contains("tarif")) {
            response = "Les tarifs des interventions esthétiques dépendent de plusieurs facteurs tels que le type d'intervention, la région, et le praticien. Il est préférable de prendre rendez-vous pour une consultation afin d'obtenir des informations précises sur les tarifs.";
        } else if (userInput.contains("intervention")) {
            response = "Les interventions esthétiques courantes comprennent la rhinoplastie, l'augmentation mammaire, la liposuccion, le lifting du visage, et bien d'autres. Chaque intervention a ses propres spécificités et tarifs. Quelle intervention souhaitez-vous connaître ?";
        }  else if (userInput.contains("date disponible")) {
            response = "Veuillez contacter votre medecin";
          } else {
            response = "Je suis désolé, je ne peux répondre qu'aux questions concernant les tarifs des interventions esthétiques. Pouvez-vous être plus précis dans votre question ?";
        }

        return response;
    }

    @FXML
    private void BackToHome(ActionEvent event)  throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Fullcrud.fxml"));
          
          
        Parent root = loader.load();
        userInputField.getScene().setRoot(root);
    }
}

