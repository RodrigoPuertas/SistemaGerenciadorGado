/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Models.BLL.LoginBll;
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
                    redirectToView("home.fxml", event);

                } else {
                    alertGeneric(returnDTO.getMessage());
                }
            }

        } catch (Exception error) {
            alertGeneric(error.getMessage());
        }
    }

    @FXML
    void actionLogOut(ActionEvent event) {
        try {
            UserSession userSession = UserSession.getInstance();
            userSession.cleanUserSession();

            alertGeneric("Logged out successfully!");

            redirectToView("telaLogin.fxml", event);

        } catch (Exception error) {
            alertGeneric(error.getMessage());
        }
    }

    @FXML
    void actionUserList(ActionEvent event) {
        try {
            redirectToView("userList.fxml", event);
        } catch (Exception error) {
            alertGeneric(error.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void alertGeneric(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void redirectToView(String screenName, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/fesa/gerenciador_gado/" + screenName));
        Parent newViewParent = loader.load();
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newViewScene);
        window.show();
    }
}
