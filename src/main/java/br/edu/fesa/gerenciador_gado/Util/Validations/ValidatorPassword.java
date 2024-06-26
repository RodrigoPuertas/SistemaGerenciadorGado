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
public class ValidatorPassword {

    public static boolean validatePassword(String senha) {
        final String senhaRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[\\s\\S]{8,}$";
        Pattern pattern = Pattern.compile(senhaRegex);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }

    public static String validatePasswordFields(String senha, String confirmaSenha) {
        if(senha.isBlank() || confirmaSenha.isBlank())
        {
            return "Campo vazio";
        }
        if (!validatePassword(senha)) {
            return "Senha fraca";
        }
        if (!senha.equals(confirmaSenha)) {
            return "Senhas não são iguais";
        }
        return "";
    }

    public static String validatePasswordFields(String senhaLogin) {
        if(senhaLogin.isBlank())
        {
            return "Campo vazio";
        }
        
        return "";
    }
    
   

}
