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
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo Puertas
 */
public class ControllerHelper {
    
     public static void alertGeneric(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void redirectToView(ActionEvent event, FXMLLoader loader ) throws IOException {
        Parent newViewParent = loader.load();
        Scene newViewScene = new Scene(newViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newViewScene);
        window.show();
    }
}
