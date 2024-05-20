/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo Puertas
 */
public class ControllerHelper {

    public static void alertWarningGeneric(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(message);
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/br/edu/fesa/gerenciador_gado/images/cow.png")); // Substitua "icone.png" pelo caminho para o seu ícone
        alert.showAndWait();
    }

    public static void alertSucessGeneric(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/br/edu/fesa/gerenciador_gado/images/cow.png")); // Substitua "icone.png" pelo caminho para o seu ícone
        alert.showAndWait();
    }

    public static void alertErrorGeneric(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/br/edu/fesa/gerenciador_gado/images/cow.png")); // Substitua "icone.png" pelo caminho para o seu ícone
        alert.showAndWait();
    }

    public static void alertNoFuctionsEvents() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setContentText("Desculpe, essa funcionalidade ainda não foi adicionada!"
                + "\n\nEspere por novidades!!");
        alert.setHeaderText(null);
        
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/br/edu/fesa/gerenciador_gado/images/cow.png")); // Substitua "icone.png" pelo caminho para o seu ícone
        
        alert.showAndWait();
    }
}
