/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Models.BLL.LoginBll;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.DTO.ActionReturnDTO;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail.validateEmail;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail.validateEmailFields;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorPassword.validatePasswordFields;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lohan
 * @author Rodrigo Puertas
 * @author Paulo Tristão
 */
public class TelaLoginController implements Initializable {

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Label lblAlertaEmail;

    @FXML
    private Label lblAlertaSenha;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnUser;

    @FXML
    private void actionEntrar(ActionEvent event) {
        try {
            lblAlertaEmail.setText(validateEmailFields(txtEmail.getText()));

            lblAlertaSenha.setText(validatePasswordFields(txtSenha.getText()));

            if (validateEmail(txtEmail.getText()) && !txtSenha.getText().isEmpty()) {
                String encryptedViewPassword = PasswordHasher.hashPassword(txtSenha.getText());
                LoginBll loginBll = new LoginBll();

                ActionReturnDTO returnDTO = loginBll.performLogin(txtEmail.getText(), encryptedViewPassword);

                if (returnDTO.getReturnAction()) {
                    App.setRoot("/br/edu/fesa/gerenciador_gado/viewHome");
                } else {
                    ControllerHelper.alertWarningGeneric(returnDTO.getMessage());
                }
            }

        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtEmail.setText("082210017@faculdade.cefsa.edu.br");
        txtSenha.setText("1234");
    }   
}
