/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.CattleAplicationEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.GenderEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.RacaGadoEnum;
import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorFields;
import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorResults;
import java.net.URL;
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
    private Label lblAlertaEmail;

    @FXML
    private Label lblAlertaNome;

    @FXML
    private Label lblAlertaPerfil;

    @FXML
    private Label lblAlertaSenha;

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
            App.setRoot("viewSingUpCattle");
        } catch(Exception error)  {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionSingUpCattle(ActionEvent event) {
        
       
        //ValidatorResults results ValidatorFields.ValidateIsEmpty(txtPeso);
        ValidatorFields.ValidateIsEmpty(txtObservacoes);
        ValidatorFields.ValidateIsEmpty(txtDescricao);
        ValidatorFields.ValidateIsEmpty(txtDataNascimento);
        ValidatorFields.ValidateIsEmpty(txtPeso);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboAplicacao.setItems(FXCollections.observableArrayList(CattleAplicationEnum.values()));
        cboRaca.setItems(FXCollections.observableArrayList(RacaGadoEnum.values()));
        cboSexo.setItems(FXCollections.observableArrayList(GenderEnum.values()));
        
    }

}
