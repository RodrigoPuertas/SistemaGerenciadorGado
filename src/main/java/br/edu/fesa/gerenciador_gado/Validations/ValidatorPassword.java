/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rodrigo Puertas
 */
public class ValidatorPassword {
    public static boolean validateEmail(String email){
        String emailRegex = "";//criar a regra de validação da senha.
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
