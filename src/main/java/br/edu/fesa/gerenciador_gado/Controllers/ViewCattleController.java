/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.DAO.CattleDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.Cattle;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.CattleAplicationEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.GenderEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.RacaGadoEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Lohan
 */
public class ViewCattleController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSingUp;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<CattleAplicationEnum> cboAplicacao;

    @FXML
    private ComboBox<RacaGadoEnum> cboRaca;

    @FXML
    private ComboBox<GenderEnum> cboSexo;

    @FXML
    private TableColumn<Cattle, CattleAplicationEnum> colunaAplicacao;

    @FXML
    private TableColumn<Cattle, LocalDate> colunaDataNascimento;

    @FXML
    private TableColumn<Cattle, String> colunaDescricao;

    @FXML
    private TableColumn<Cattle, Integer> colunaID;

    @FXML
    private TableColumn<Cattle, String> colunaObservacao;

    @FXML
    private TableColumn<Cattle, RacaGadoEnum> colunaRaca;

    @FXML
    private TableColumn<Cattle, GenderEnum> colunaSexo;

    @FXML
    private Label lblAlertaAplicacao;

    @FXML
    private Label lblAlertaEmail;

    @FXML
    private Label lblAlertaNascimento;

    @FXML
    private Label lblAlertaNome;

    @FXML
    private Label lblAlertaPerfil;

    @FXML
    private Label lblAlertaPeso;

    @FXML
    private Label lblAlertaRaca;

    @FXML
    private Label lblAlertaSexo;

    @FXML
    private Pane paneLeft;

    @FXML
    private TableView<Cattle> tblCattle;

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
    void actionDelete(ActionEvent event) {
        try {
            Cattle cattle = tblCattle.getSelectionModel().getSelectedItem();
            CattleDAO dao = new CattleDAO();
            dao.remove(cattle);
            refresh();
        } catch (PersistenceException error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionMoveToSIngUp(ActionEvent event) {
        try {
            App.setRoot("viewSingUpCattle");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionShowData(MouseEvent event) {
        try {
            Cattle cattle = tblCattle.getSelectionModel().getSelectedItem();

            txtDataNascimento.setValue(cattle.getDataNascimento());
            cboSexo.setValue(cattle.getGender());
            cboAplicacao.setValue(cattle.getAplication());
            cboRaca.setValue(cattle.getRaca());
            txtDescricao.setText(cattle.getDescricao());
            txtObservacoes.setText(cattle.getObservacao());
        } catch (Exception error) {
            ControllerHelper.alertWarningGeneric(error.toString());
        }
    }

    @FXML
    void actionUpdate(ActionEvent event) {
        try {

            ValidatorResults resultNascimento = ValidatorFields.ValidateIsEmpty(txtDataNascimento);
            ValidatorResults resultSexo = ValidatorFields.ValidateIsEmpty(cboSexo);
            ValidatorResults resultAplicacao = ValidatorFields.ValidateIsEmpty(cboAplicacao);
            ValidatorResults resultRaca = ValidatorFields.ValidateIsEmpty(cboRaca);
            ValidatorResults resultPeso = ValidatorFields.ValidateIsEmpty(txtPeso);

            lblAlertaNascimento.setText(resultNascimento.getErrorMessage());
            lblAlertaSexo.setText(resultSexo.getErrorMessage());
            lblAlertaAplicacao.setText(resultAplicacao.getErrorMessage());
            lblAlertaRaca.setText(resultRaca.getErrorMessage());
            lblAlertaPeso.setText(resultPeso.getErrorMessage());

            Cattle cattle = tblCattle.getSelectionModel().getSelectedItem();

            if (resultNascimento.isIsValid() && resultAplicacao.isIsValid() && resultPeso.isIsValid()
                    && validatePeso().isIsValid() && resultRaca.isIsValid() && resultSexo.isIsValid()) {
                List<Map<LocalDate, Double>> historicoPeso = new ArrayList<Map<LocalDate, Double>>();
                Map<LocalDate, Double> primeiroPeso = new HashMap<>();
                primeiroPeso.put(LocalDate.now(), Double.parseDouble(txtPeso.getText()));
                historicoPeso.add(primeiroPeso);

                Cattle gado = new Cattle(cattle.getId(), cboAplicacao.getValue(), cboRaca.getValue(), cboSexo.getValue(), txtDataNascimento.getValue(),
                        historicoPeso, txtDescricao.getText(), txtObservacoes.getText());

                CattleDAO dao = new CattleDAO();

                dao.update(gado);
                refresh();
            }

        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTable();
        cboAplicacao.setItems(FXCollections.observableArrayList(CattleAplicationEnum.values()));
        cboRaca.setItems(FXCollections.observableArrayList(RacaGadoEnum.values()));
        cboSexo.setItems(FXCollections.observableArrayList(GenderEnum.values()));

    }

    public void createTable() {
        try {
            colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
            colunaSexo.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colunaDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
            colunaAplicacao.setCellValueFactory(new PropertyValueFactory<>("aplication"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

            tblCattle.getColumns().addAll(colunaID, colunaRaca, colunaSexo, colunaDataNascimento, colunaAplicacao, colunaDescricao, colunaObservacao);

            CattleDAO dao = new CattleDAO();
            ObservableList<Cattle> cattle = FXCollections.observableArrayList(dao.list());

            tblCattle.setItems(cattle);
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    public void refresh() {
        try {
            tblCattle.getItems().clear();
            CattleDAO dao = new CattleDAO();
            ObservableList<Cattle> cattle = FXCollections.observableArrayList(dao.list());
            tblCattle.setItems(cattle);
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }

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
