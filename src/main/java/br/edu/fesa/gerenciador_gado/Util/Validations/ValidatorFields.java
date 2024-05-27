/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util.Validations;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Lohan
 */
public class ValidatorFields {

    public static String ValidateIsEmpty(TextField txt) {
        if (txt.getText().isBlank()) {
            return "Campo vazio";
        }
        return "";
    }

    public static String ValidateIsEmpty(ComboBox cbo) {
        if (cbo.getValue() == null) {
            return "Nenhum campo selecionado";
        }
        return "";
    }
    
}
