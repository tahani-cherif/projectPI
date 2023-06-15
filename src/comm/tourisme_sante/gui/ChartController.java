/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Interventions;
import com.tourisme_sante.entities.TypeInterventions;
import com.tourisme_sante.utils.Datasource;
import comm.tourisme_sante.services.Service_TypeInterventions;
import comm.tourisme_sante.services.ServicesInterventions;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javax.activation.DataSource;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class ChartController implements Initializable {
 private Connection cnx = Datasource.getInstance().getCnx();
  ServicesInterventions sc = new ServicesInterventions();
  ObservableList<Interventions>  list = FXCollections.observableArrayList();
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button TF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setCenter(buildPieChart());
    }

    @FXML
    private void handleShowBarChart() {

        borderPane.setCenter(buildBarChart());
    }

    @FXML
    private void handleShowPieChart() {
        borderPane.setCenter(buildPieChart());
    }

    private BarChart buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Type Interventions");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("En chiffres");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Types Interventions en chiffres pour l'année actuelle");

        dataSeries1.getData().add(new XYChart.Data("Chirugie esthétique", 2300));
        dataSeries1.getData().add(new XYChart.Data("Chirugie obésité", 1000));
        dataSeries1.getData().add(new XYChart.Data("Chirugie orthopedique", 986));
        dataSeries1.getData().add(new XYChart.Data("Chirugie cardio visculaire", 870));
        
        barChart.getData().add(dataSeries1);

        return barChart;
    }

    private PieChart buildPieChart() {
         

        //Preparing ObservbleList object         
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Visage", 65),
                new PieChart.Data("Prothese", 56),
                new PieChart.Data("silhouette", 48),
                new PieChart.Data("Anneau", 47),
                new PieChart.Data("Lifting", 35));
              
//             System.out.println("test");
//          ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList() ;
//            for(Interventions i :sc.afficher()){
//                pieChartData.add(new PieChart.Data(i.getNomType(),sc.getInterventions("nom")));
//            }
               

        PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart      

        //attach tooltips
        createToolTips(pieChart);

        pieChart.setTitle("Interventions %"); //Setting the title of the Pie chart
        pieChart.setClockwise(true); //setting the direction to arrange the data 
        pieChart.setLabelLineLength(50); //Setting the length of the label line 
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(true); //METTRE BAS VISIBLE 
        
        pieChart.setStartAngle(180);

        //bind value and label on each pie slice to reflect changes
        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " ")
                ));


        ContextMenu contextMenu = new ContextMenu(); //create context menu
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);


        //Add event handler to display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                        }                        
                    }
                });
               
        
        //Before Java 8
        //Add event handler to change chart type (anonymous inner class)
        miSwitchToBarChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                borderPane.setCenter(buildBarChart());
            }
        });


        //Java 8 and newer (lambda expression)
        miSwitchToBarChart.setOnAction(event -> { borderPane.setCenter(buildBarChart()); });

        
        return pieChart;
    }

    /**
     *
     */
    @FXML
    private void handleClose() throws IOException {
       System.exit(0);
    }
    
    
    /**
     * 
     */
    private void handleUpdatePieData() {
        Node node = borderPane.getCenter();
        
        if (node instanceof PieChart)
        {
            PieChart pc = (PieChart) node;
            double value = pc.getData().get(2).getPieValue();
            pc.getData().get(2).setPieValue(value * 1.10);
            createToolTips(pc);
        }
    }


    /**
     * Creates tooltips for all data entries
     * @param pc
     */
    private void createToolTips(PieChart pc) {

        for (PieChart.Data data: pc.getData()) {
            String msg = Double.toString(data.getPieValue());

            Tooltip tp = new Tooltip(msg);
            //tp.setShowDelay(Duration.seconds(0));

            Tooltip.install(data.getNode(), tp);

            //update tooltip data when changed
            data.pieValueProperty().addListener((observable, oldValue, newValue) ->
            {
                tp.setText(newValue.toString());
            });
        }
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FullInter.fxml"));
            Parent root = loader.load();
         
             TF.getScene().setRoot(root);
    }
}