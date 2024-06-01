/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util.Validations;

import java.time.LocalDate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Lohan
 */
public class ValidatorFields {

    public static ValidatorResults ValidateIsEmpty(TextField txt) {
        if (txt.getText().isBlank()) {
            ValidatorResults results = new ValidatorResults(false, "Campo vazio");
            return results;
        }
        ValidatorResults results = new ValidatorResults(true, "");
        return results;
    }

    public static ValidatorResults ValidateIsEmpty(ComboBox cbo) {
        if (cbo.getValue() == null) {
            ValidatorResults results = new ValidatorResults(false, "Nenhum campo selecionado");
            return results;
        }
        ValidatorResults results = new ValidatorResults(true, "");
        return results;
    }

    public static ValidatorResults ValidateIsEmpty(TextArea txtArea) {
        if (txtArea.getText().isBlank()) {
            ValidatorResults results = new ValidatorResults(false, "Campo vazio");
            return results;
        }
        ValidatorResults results = new ValidatorResults(true, "");
        return results;
    }

    public static ValidatorResults ValidateIsEmpty(DatePicker txtData) {
        if (txtData.getValue() == null) {
            ValidatorResults results = new ValidatorResults(false, "Campo vazio");
            return results;
        }
        if (txtData.getValue().isAfter(LocalDate.now())) {
            ValidatorResults results = new ValidatorResults(false, "Data inválida");
            return results;
        }

        ValidatorResults results = new ValidatorResults(true, "");
        return results;
    }

}
