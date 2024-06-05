/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.DAO.CattleDAO;
import br.edu.fesa.gerenciador_gado.DAO.HistoricoPesosGadoDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.Cattle;
import br.edu.fesa.gerenciador_gado.Models.Entities.HistoricoPesosGado;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.CattleAplicationEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.GenderEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.RacaGadoEnum;
import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorFields;
import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorResults;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lohan
 */
public class ViewSingUpCattleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnBack;

    @FXML
    private Button btnCadastrar;

    @FXML
    private ComboBox<CattleAplicationEnum> cboAplicacao;

    @FXML
    private ComboBox<RacaGadoEnum> cboRaca;

    @FXML
    private ComboBox<GenderEnum> cboSexo;

    @FXML
    private Label lblAlertaAplicacao;

    @FXML
    private Label lblAlertaNascimento;

    @FXML
    private Label lblAlertaSexo;

    @FXML
    private Label lblAlertaPeso;

    @FXML
    private Label lblAlertaRaca;
    @FXML
    private DatePicker txtDataNascimento;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextArea txtObservacoes;

    @FXML
    private TextField txtPeso;

    @FXML

    void actionBack(ActionEvent event) {
        try {
            App.setRoot("viewHome");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionSingUpCattle(ActionEvent event) {
        try {
            ValidatorResults resultsPeso = validatePeso();
            ValidatorResults resultsDataNascimento = ValidatorFields.ValidateIsEmpty(txtDataNascimento);
            ValidatorResults resultsAplicacao = ValidatorFields.ValidateIsEmpty(cboAplicacao);
            ValidatorResults resultsRaca = ValidatorFields.ValidateIsEmpty(cboRaca);
            ValidatorResults resultsSexo = ValidatorFields.ValidateIsEmpty(cboSexo);

            lblAlertaNascimento.setText(resultsDataNascimento.getErrorMessage());
            lblAlertaAplicacao.setText(resultsAplicacao.getErrorMessage());
            lblAlertaRaca.setText(resultsRaca.getErrorMessage());
            lblAlertaSexo.setText(resultsSexo.getErrorMessage());

            if ((validatePeso().isIsValid() || !resultsPeso.isIsValid())) {
                lblAlertaPeso.setText(resultsPeso.getErrorMessage());
            }

            if (resultsAplicacao.isIsValid() && resultsDataNascimento.isIsValid() && resultsPeso.isIsValid()
                    && validatePeso().isIsValid() && resultsRaca.isIsValid() && resultsSexo.isIsValid()) {
                List<Map<LocalDate, Double>> historicoPeso = new ArrayList<Map<LocalDate, Double>>();
                Map<LocalDate, Double> primeiroPeso = new HashMap<>();
                primeiroPeso.put(LocalDate.now(), Double.parseDouble(txtPeso.getText()));
                historicoPeso.add(primeiroPeso);

                Cattle gado = new Cattle(cboAplicacao.getValue(), cboRaca.getValue(), cboSexo.getValue(), txtDataNascimento.getValue(),
                        historicoPeso, txtDescricao.getText(), txtObservacoes.getText());

                CattleDAO dao = new CattleDAO();
                dao.insert(gado);

                ControllerHelper.alertSucessGeneric("Gado inserido no banco de dados");
                
                Cattle gadoHistorico = dao.lastCattle();
                HistoricoPesosGado peso = new HistoricoPesosGado(gadoHistorico.getDataNascimento(), gadoHistorico.getId(), Double.parseDouble(txtPeso.getText()));
                
                HistoricoPesosGadoDAO pesosDAO = new HistoricoPesosGadoDAO();
                pesosDAO.insert(peso);
            }
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboAplicacao.setItems(FXCollections.observableArrayList(CattleAplicationEnum.values()));
        cboRaca.setItems(FXCollections.observableArrayList(RacaGadoEnum.values()));
        cboSexo.setItems(FXCollections.observableArrayList(GenderEnum.values()));
        txtPeso.setText("0.00");

        CattleDAO gado = new CattleDAO();

    }

    ValidatorResults validatePeso() {
        ValidatorResults results;

        try {
            if (Double.parseDouble(txtPeso.getText()) > 0) {
                results = new ValidatorResults(true, "");
                return results;
            }
        } catch (Exception e) {
            results = new ValidatorResults(false, "Não é um valor válido");
            return results;
        }
        results = new ValidatorResults(false, "Peso precisa ser maior que zero");
        return results;

    }

}
