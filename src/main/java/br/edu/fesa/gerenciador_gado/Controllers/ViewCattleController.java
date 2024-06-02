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
import java.net.URL;
import java.time.LocalDate;
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
    private Label lblAlertaEmail;

    @FXML
    private Label lblAlertaNome;

    @FXML
    private Label lblAlertaPerfil;

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

    }

    @FXML
    void actionShowData(MouseEvent event) {

    }

    @FXML
    void actionUpdate(ActionEvent event) {

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
}
