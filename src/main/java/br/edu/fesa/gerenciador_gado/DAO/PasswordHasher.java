package br.edu.fesa.gerenciador_gado.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rodrigo Puertas
 */
public class PasswordHasher {
     private static final String SALT = "90b55b4160ff56779ded3b772b314efb"; // Sal padrão (altere para um valor aleatório)

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        String saltedPassword = password + SALT;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(saltedPassword.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
