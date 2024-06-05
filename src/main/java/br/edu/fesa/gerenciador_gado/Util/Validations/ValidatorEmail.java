/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rodrigo Puertas
 */
public class ValidatorEmail {

    public static boolean validateEmail(String email) {
        final String emailRegex = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*@([a-zA-Z0-9.]{2,})+([.])([a-zA-Z0-9]{2,})+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static ValidatorResults validateEmailFields(String email, String confirmEmail) {
        ValidatorResults results;
        if (email.isBlank() || confirmEmail.isBlank()) {
            results = new ValidatorResults(false, "Campo vazio");
            return results;
        }
        if (!validateEmail(email)) {
            results = new ValidatorResults(false, "Email inválido");
            return results;
        }
        if (!email.equals(confirmEmail)) {
            results = new ValidatorResults(false, "Emails não são iguais");
            return results;
        }

        results = new ValidatorResults(true, "");
        return results;
    }

    public static ValidatorResults validateEmailFields(String emailLogin) {
        ValidatorResults results;
        if (emailLogin.isBlank() || emailLogin.isBlank()) {
            results = new ValidatorResults(false, "Email vazio");
            return results;
        }
        if (!validateEmail(emailLogin)) {
            results = new ValidatorResults(false, "O endereço de e-mail é inválido.");
            return results;
        }
        results = new ValidatorResults(true, "");
        return results;
    }

}
