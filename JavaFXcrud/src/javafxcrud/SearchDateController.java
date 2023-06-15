package javafxcrud;

import java.io.IOException;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class SearchDateController {

    @FXML
    private DatePicker inputDateDebut;

    @FXML
    private DatePicker inputDateFin;
      @FXML
    private Button searchButton;

    @FXML
    void goToResult(ActionEvent event) throws IOException {
        ResultController.dateD=Date.valueOf(inputDateDebut.getValue());
                ResultController.dateF=Date.valueOf(inputDateFin.getValue());

  FXMLLoader loader = new FXMLLoader(getClass().getResource("Result.fxml"));
  
        Parent root = loader.load();
                searchButton.getScene().setRoot(root);

    }

}
