/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.agence_medicale.Interventions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Chimou
 */
public class ChartController implements Initializable {

    @FXML
    private BorderPane borderPane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         borderPane.setCenter(buildPieChart());
    }    

    @FXML
    private void hundleShowBarChar(ActionEvent event) {
        
           borderPane.setCenter(buildBarChart());
    }

    @FXML
    private void hundleShowPieChar(ActionEvent event) {
       
    //create data 
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList (
       
        new PieChart.Data("intervention 1", 300),
        new PieChart.Data("intervention 2", 1000),
        new PieChart.Data("intervention 3", 500)
        );
    //create piechart object
    PieChart pieChart =new PieChart (pieChartData);
    pieChart.setTitle("Interventions");
    pieChart.setClockwise(true); 
    pieChart.setLabelLineLength(50);
    pieChart.setLabelsVisible(true);
    pieChart.setStartAngle(100);
            
      borderPane.setCenter(buildPieChart());
   }
    
    
    
    private BarChart buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("les Inerventions les plus utilisées");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Chiffres pour cette année");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("les Inerventions les plus utulisées");
        int valeur=0;
        dataSeries1.getData().add(new XYChart.Data("test", 2300));
        dataSeries1.getData().add(new XYChart.Data("lifting", valeur));
        dataSeries1.getData().add(new XYChart.Data("silhouette", valeur));
        dataSeries1.getData().add(new XYChart.Data("prothese", valeur));
        dataSeries1.getData().add(new XYChart.Data("comblement", valeur));
        dataSeries1.getData().add(new XYChart.Data("botox", valeur));
        barChart.getData().add(dataSeries1);

        return barChart;
    }

    private PieChart buildPieChart() {
int valeur=0;
        //Preparing ObservbleList object         
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("test", valeur),
                new PieChart.Data("lifting", valeur),
                new PieChart.Data("silhouette", valeur),
                new PieChart.Data("^rothese", valeur),
                new PieChart.Data("comblement", valeur),
                new PieChart.Data("botox", valeur));

        PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart      

        //attach tooltips
        createToolTips(pieChart);

        pieChart.setTitle(" Les interventions les plus demandées "); //Setting the title of the Pie chart
        pieChart.setClockwise(true); //setting the direction to arrange the data 
        pieChart.setLabelLineLength(50); //Setting the length of the label line 
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(false);
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

 

    @FXML
    private void hundleClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void hundleUpdateData(ActionEvent event) {
        
        Node node = borderPane.getCenter();
        
        if (node instanceof PieChart)
        {
            PieChart pc = (PieChart) node;
            double value = pc.getData().get(2).getPieValue();
            pc.getData().get(2).setPieValue(value * 1.10);
            createToolTips(pc);
        }
    }
    
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
}
