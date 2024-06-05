/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author Rodrigo Puertas
 */
public class ViewHomeController implements Initializable {

   @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCattle;

    @FXML
    private Button btnCattleManagement;

    @FXML
    private Button btnLivestockManagement;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnLogOut1;

    @FXML
    private Button btnLogOut2;

    @FXML
    private Button btnPatrimony;

    @FXML
    private Button btnUser;

    @FXML
    private Button btnWeight;

    @FXML
    private Pane paneADM;

    @FXML
    private Pane paneFARMER;

    @FXML
    private Pane paneRANCHER;
    
    @FXML
    private Pane paneNews;
    

    @FXML
    void actionLogOut(ActionEvent event) {
        try {
            UserSession userSession = UserSession.getInstance();
            userSession.cleanUserSession();

            ControllerHelper.alertSucessGeneric("Logged out successfully!");

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

    @FXML
    void actionMoveToViewCattle(ActionEvent event) {
        try {
            App.setRoot("viewSingUpCattle");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionMoveToViewPatrimony(ActionEvent event) {
        try {
            App.setRoot("viewDashboard");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionMoveToViewUser(ActionEvent event) {
        try {
            App.setRoot("viewUser");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionMoveToViewCattleMan(ActionEvent event) {
        try {
            App.setRoot("viewCattle");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }
    
    @FXML
    void actionMoveToViewWeightHistory(ActionEvent event) {
        try {
            App.setRoot("viewWeightHistory");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserSession userSession = UserSession.getInstance();
        User user = userSession.getUser();

        ProfileEnum profile = user.getProfileCode();

        if (profile.getValue() == 1) {
            paneADM.setVisible(true);
            paneFARMER.setVisible(false);
            paneRANCHER.setVisible(false);
        }
        if (profile.getValue() == 2) {
            paneADM.setVisible(false);
            paneFARMER.setVisible(true);
            paneRANCHER.setVisible(false);
        }
        if (profile.getValue() == 3) {
            paneADM.setVisible(false);
            paneFARMER.setVisible(false);
            paneRANCHER.setVisible(true);
        }
    }

}
