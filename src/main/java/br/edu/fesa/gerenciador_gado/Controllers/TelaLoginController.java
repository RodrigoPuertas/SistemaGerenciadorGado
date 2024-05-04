/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Models.BLL.LoginBll;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail.validateEmail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            }

        } catch (Exception error) {
            Alert alert = new Alert(AlertType.INFORMATION);
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
