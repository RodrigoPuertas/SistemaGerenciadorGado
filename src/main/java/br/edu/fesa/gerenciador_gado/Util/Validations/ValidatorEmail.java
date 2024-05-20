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

        final String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@(?=[A-Za-z0-9-]+\\.[A-Za-z]{2,})(?!-)[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String validateEmailFields(String email, String confirmEmail) {

        if (email.isBlank() || confirmEmail.isBlank()) {
            return "Campo vazio";
        }
        if (!validateEmail(email)) {
            return "Email inválido";
        }
        if (!email.equals(confirmEmail)) {
            return "Emails não são iguais";
        }

        return "";
    }

    public static String validateEmailFields(String emailLogin) {
        if (emailLogin.isBlank() || emailLogin.isBlank()) {
            return "Campo vazio";
        }
        if (!validateEmail(emailLogin)) {
            return "O endereço de e-mail é inválido.";
        }
        return "";
    }

}
