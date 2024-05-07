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

            ControllerHelper.alertWarningGeneric("Logged out successfully!");

            App.setRoot("viewLogin");

        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionMoveToViewSignUp(ActionEvent event) {
        try {
            App.setRoot("viewSingUp");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

}
