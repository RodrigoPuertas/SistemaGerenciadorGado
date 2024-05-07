/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Models.BLL.LoginBll;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.DTO.ActionReturnDTO;
import br.edu.fesa.gerenciador_gado.Util.UserSession;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail.validateEmail;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail.validateEmailFields;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorPassword.validatePasswordFields;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    void actionEntrar(ActionEvent event) {
        try {
            lblAlertaEmail.setText(validateEmailFields(txtEmail.getText()));

            lblAlertaSenha.setText(validatePasswordFields(txtSenha.getText()));

            if (validateEmail(txtEmail.getText()) && !txtSenha.getText().isEmpty()) {
                String encryptedViewPassword = PasswordHasher.hashPassword(txtSenha.getText());
                LoginBll loginBll = new LoginBll();

                ActionReturnDTO returnDTO = loginBll.performLogin(txtEmail.getText(), encryptedViewPassword);

                if (returnDTO.getReturnAction()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/fesa/gerenciador_gado/viewHome.fxml"));
                    ControllerHelper.redirectToView(event, loader);

                } else {
                    ControllerHelper.alertGeneric(returnDTO.getMessage());
                }
            }

        } catch (Exception error) {
            ControllerHelper.alertGeneric(error.getMessage());
        }
    }

    @FXML
    void actionLogOut(ActionEvent event) {
        try {
            UserSession userSession = UserSession.getInstance();
            userSession.cleanUserSession();

            ControllerHelper.alertGeneric("Logged out successfully!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/fesa/gerenciador_gado/telaLogin.fxml"));
            ControllerHelper.redirectToView(event, loader);

        } catch (Exception error) {
            ControllerHelper.alertGeneric(error.getMessage());
        }
    }

    @FXML
    void actionUserList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/fesa/gerenciador_gado/userList.fxml"));
            ControllerHelper.redirectToView(event, loader);
        } catch (Exception error) {
            ControllerHelper.alertGeneric(error.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}
