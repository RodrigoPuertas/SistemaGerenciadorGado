/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.DAO.UserDAO;
import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail.validateEmailFields;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorPassword.validatePasswordFields;
import static br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorFields.ValidateIsEmpty;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lohan
 * @author Rodrigo Puertas
 */
public class TelaCadastroController implements Initializable {

    @FXML
    private Button btnEntrar;

    @FXML
    private ComboBox<ProfileEnum> cboPerfil;

    @FXML
    private Label lblAlertaEmail;

    @FXML
    private Label lblAlertaNome;

    @FXML
    private Label lblAlertaPerfil;

    @FXML
    private Label lblAlertaRegistro;

    @FXML
    private Label lblAlertaSenha;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtConfirmaEmail;

    @FXML
    private Button btnBack;

    @FXML
    void actionBack(ActionEvent event) {
        try {
            App.setRoot("viewHome");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionSingUp(ActionEvent event) {
        try {
            // Validando os campos e exibindo mensagens de alerta, se necessário
            String nomeAlerta = ValidateIsEmpty(txtNome);
            String perfilAlerta = ValidateIsEmpty(cboPerfil);
            String emailAlerta = validateEmailFields(txtEmail.getText(), txtConfirmaEmail.getText());
            String senhaAlerta = validatePasswordFields(txtPassword.getText(), txtConfirmPassword.getText());

            lblAlertaNome.setText(nomeAlerta);
            lblAlertaPerfil.setText(perfilAlerta);
            lblAlertaEmail.setText(emailAlerta);
            lblAlertaSenha.setText(senhaAlerta);

            // Verificando se houve algum alerta
            if (nomeAlerta.isEmpty() && perfilAlerta.isEmpty() && emailAlerta.isEmpty() && senhaAlerta.isEmpty()) {
                // Criptografando a senha antes de salvar no banco de dados
                String senhaCriptografada = PasswordHasher.hashPassword(txtPassword.getText());

                // Criando um objeto de usuário com os dados fornecidos pelo usuário
                User newUser = new User(txtNome.getText(), txtEmail.getText(), senhaCriptografada, cboPerfil.getValue());

                // Inserindo o novo usuário no banco de dados
                UserDAO userDAO = new UserDAO();
                userDAO.insert(newUser);

                // Limpando os campos após o cadastro
                clearFields();

                // Exibindo uma mensagem de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("User registered successfully!");
                alert.showAndWait();
            }

        } catch (Exception error) {
            System.out.println(error.getMessage());
            ControllerHelper.alertWarningGeneric(error.getMessage());

        }
    }

    // Método para limpar os campos após o cadastro
    private void clearFields() {
        txtNome.clear();
        txtEmail.clear();
        txtConfirmaEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
        cboPerfil.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cboPerfil.setItems(FXCollections.observableArrayList(ProfileEnum.values()));
    }

}
