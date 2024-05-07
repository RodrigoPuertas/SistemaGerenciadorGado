/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Rodrigo Puertas
 */
public class ViewHomeController implements Initializable {

    @FXML
    private Button btnPatrimony;

    @FXML
    private Label lblAlertaSenha;

    @FXML
    private Label lblAlertaPerfil;

    @FXML
    private Button btnLogOut;

    @FXML
    private Label lblAlertaEmail;

    @FXML
    private Label lblAlertaNome;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCattle;

    @FXML
    private Label lblAlertaRegistro;

    @FXML
    private Button btnUser;

    @FXML
    void actionLogOut(ActionEvent event) {
        try {
            UserSession userSession = UserSession.getInstance();
            userSession.cleanUserSession();

            ControllerHelper.alertGeneric("Logged out successfully!");

            App.setRoot("/br/edu/fesa/gerenciador_gado/telaLogin");

        } catch (Exception error) {
            ControllerHelper.alertGeneric(error.getMessage());
        }
    }

    @FXML
    void actionSignUp(ActionEvent event) {
        try {
            App.setRoot("/br/edu/fesa/gerenciador_gado/telaCadastro");
        } catch (Exception error) {
            ControllerHelper.alertGeneric(error.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

}
