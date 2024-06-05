/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.DAO.CattleDAO;
import br.edu.fesa.gerenciador_gado.DAO.HistoricoPesosGadoDAO;
import br.edu.fesa.gerenciador_gado.DAO.PriceCattleDAO;
import br.edu.fesa.gerenciador_gado.Models.BLL.DashboardBll;
import br.edu.fesa.gerenciador_gado.Models.Entities.Cattle;
import br.edu.fesa.gerenciador_gado.Models.Entities.HistoricoPesosGado;
import br.edu.fesa.gerenciador_gado.Models.Entities.PriceCattle;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.net.URL;
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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Guilherme Garcia
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
            createLabelTotalAsset();
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
    private Label lblPatrimonio;
    
    @FXML

    void actionBack(ActionEvent event) {
        try {
            App.setRoot("viewHome");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    private void createLabelTotalAsset(){
        DashboardBll dashBll = new DashboardBll();
        lblPatrimonio.setText(dashBll.ReturnTotalAssets());
    }
    
    
    private void createBarChart() throws PersistenceException {
        PriceCattleDAO priceCattleDAO = new PriceCattleDAO();
        HistoricoPesosGadoDAO historicoPesosDAO = new HistoricoPesosGadoDAO();

        List<PriceCattle> listPriceCattle = priceCattleDAO.list();
        HistoricoPesosGado historicoPesos = historicoPesosDAO.listGrouped();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Assets");
        final double valorArroba = 14.688;
        for (PriceCattle priceCattle : listPriceCattle) {
            series1.getData().add(new XYChart.Data(priceCattle.getDtPrice().toString(), (priceCattle.getPrice() * historicoPesos.getPesoKg()) / valorArroba));
        }

        barChart.getData().addAll(series1);

    }

    private void createPieChart() throws PersistenceException{
        CattleDAO cattleDao = new CattleDAO();
        List<Cattle> listCattle = cattleDao.list();
        long qtdCorte = listCattle.stream().filter(c -> c.getAplication().getValue() == "Corte").count();
        long qtdMisto = listCattle.stream().filter(c -> c.getAplication().getValue() == "Misto").count();
        long qtdLeiteiro = listCattle.stream().filter(c -> c.getAplication().getValue() == "Leiteiro").count();
        long qtdReprodutor = listCattle.stream().filter(c -> c.getAplication().getValue() == "Reprodução").count();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("BEEF", qtdCorte),
                        new PieChart.Data("MIXED", qtdMisto),
                        new PieChart.Data("MILKING", qtdLeiteiro),
                        new PieChart.Data("BREEDING", qtdReprodutor));
                        
        final PieChart chart = new PieChart(pieChartData);
        

        pieChart.setData(pieChartData);
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setStartAngle(180);


    }

    private void createTable() {

    }
}
