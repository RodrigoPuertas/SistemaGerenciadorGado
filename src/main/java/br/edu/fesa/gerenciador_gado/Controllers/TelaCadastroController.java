/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;


import br.edu.fesa.gerenciador_gado.DAO.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Models.LoginModel;
import br.edu.fesa.gerenciador_gado.Models.PerfilUsuarioModel;
import static br.edu.fesa.gerenciador_gado.Validations.ValidatorEmail.validateEmail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ComboBox<PerfilUsuarioModel> cboPerfil;

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
    private TextField txtConfirmaSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRegistro;

    @FXML
    private TextField txtSenha;

    @FXML
    void actionCadastrar(ActionEvent event) {
        try {
            
            if(txtNome.getText().isBlank())
            
            
            
            // Validar o e-mail
            if (validateEmail(txtEmail.getText())) {
                lblAlertaEmail.setText("");
            } else {
                lblAlertaEmail.setText("O endereço de e-mail é inválido.");
                return; // Sair do método se o e-mail for inválido
            }
            
            // Validar a senha
            if(txtSenha.getText().isBlank())
            {
                lblAlertaSenha.setText("Campo vazio!");
                return; // Sair do método se a senha estiver vazia
            } 
            else {
                lblAlertaSenha.setText("");
            }
            
            // Criptografar a senha antes de criar o objeto LoginModel
            String senhaCriptografada = PasswordHasher.hashPassword(txtSenha.getText());
            
            // Criar o objeto LoginModel com o e-mail e a senha criptografada
            LoginModel login = new LoginModel(txtEmail.getText(), senhaCriptografada);
            
            // Aqui você pode prosseguir com a lógica de login usando o objeto LoginModel
            
            
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
