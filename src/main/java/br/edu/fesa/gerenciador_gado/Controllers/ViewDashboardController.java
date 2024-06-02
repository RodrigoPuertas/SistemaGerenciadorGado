/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.DAO.HistoricoPesosGadoDAO;
import br.edu.fesa.gerenciador_gado.DAO.PriceCattleDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.HistoricoPesosGado;
import br.edu.fesa.gerenciador_gado.Models.Entities.PriceCattle;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ViewDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            createPieChart();
            createTable();
            createBarChart();
        } catch (Exception e) {


        }

    }

    @FXML
    private Button btnBack;

    @FXML
    private BarChart barChart;

    @FXML
    private PieChart pieChart;

    @FXML

    void actionBack(ActionEvent event) {
        try {
            App.setRoot("viewHome");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    private void createBarChart() throws PersistenceException {
        PriceCattleDAO priceCattleDAO = new PriceCattleDAO();
        HistoricoPesosGadoDAO historicoPesosDAO = new HistoricoPesosGadoDAO();

        List<PriceCattle> listPriceCattle = priceCattleDAO.list();
        HistoricoPesosGado historicoPesos = historicoPesosDAO.listGrouped();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Patrimônio");

        for (PriceCattle priceCattle : listPriceCattle) {
            series1.getData().add(new XYChart.Data(priceCattle.getDtPrice().toString(), priceCattle.getPrice() * historicoPesos.getPesoKg()));
        }

        barChart.getData().addAll(series1);

    }

    private void createPieChart(){
//        CattleDAO cattleDao = new CattleDAO();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");

        pieChart.setData(pieChartData);
        pieChart.setTitle("Test");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setStartAngle(180);

    }

    private void createTable() {

    }
}
