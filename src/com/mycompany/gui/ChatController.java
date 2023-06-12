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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class ChatController implements Initializable {

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

        if (userInput.contains("nom des medecins")) {
            response = " Dr Jmal, Dr Balti, Dr Yachar";
        } else if (userInput.contains("Type Intervention")) {
            response = "lifting, renoplastique, prothese mamaire..";
        }  else if (userInput.contains("date disponible")) {
            response = "Veuillez contacter votre medecin";
          } else {
            response = "Je suis désolé, je ne peux répondre qu'aux questions concernant les tarifs des interventions esthétiques. Pouvez-vous être plus précis dans votre question ?";
        }

        return response;
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("fullInter.fxml"));
          
          
        Parent root = loader.load();
        userInputField.getScene().setRoot(root);
    }
}