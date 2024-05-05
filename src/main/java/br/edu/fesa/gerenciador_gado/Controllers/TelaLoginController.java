/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Models.BLL.LoginBll;
import br.edu.fesa.gerenciador_gado.Util.UserSession;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail.validateEmail;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lohan
 * @author Rodrigo Puertas
 *
 */
public class TelaLoginController implements Initializable {

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

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
            Boolean validEmail = validateEmail(txtEmail.getText());
            if (validEmail) {
                lblAlertaEmail.setText("");
            } else {
                lblAlertaEmail.setText("O endereço de e-mail é inválido.");
            }

            if (txtSenha.getText().isEmpty()) {
                lblAlertaSenha.setText("Campo vazio!");
            } else {
                lblAlertaSenha.setText("");
            }

            if (validEmail && !txtSenha.getText().isEmpty()) {
                String encryptedViewPassword = PasswordHasher.hashPassword(txtSenha.getText());
                LoginBll loginBll = new LoginBll();
                String returnMesage = loginBll.performLogin(txtEmail.getText(), encryptedViewPassword);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Aviso");
                alert.setContentText(returnMesage);
                alert.setHeaderText(null);
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/fesa/gerenciador_gado/home.fxml"));
                Parent newViewParent = loader.load();
                Scene newViewScene = new Scene(newViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newViewScene);
                window.show();
            }

        } catch (Exception error) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setContentText(error.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    void actionLogOut(ActionEvent event) {
        try {
            UserSession userSession = UserSession.getInstance();
            userSession.cleanUserSession();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setContentText("Logged out successfully!");
            alert.setHeaderText(null);
            alert.showAndWait();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/fesa/gerenciador_gado/telaLogin.fxml"));
            Parent newViewParent = loader.load();
            Scene newViewScene = new Scene(newViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newViewScene);
            window.show();
        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setContentText(error.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    void actionUserList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/fesa/gerenciador_gado/userList.fxml"));
            Parent newViewParent = loader.load();
            Scene newViewScene = new Scene(newViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newViewScene);
            window.show();

        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setContentText(error.getMessage());
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
