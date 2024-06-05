/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.models.test;

import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorPassword;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Guilherme Garcia
 */
public class PasswordValidationsTest {
        
    @Test
    public void testUserStrongPassword(){
        //The password must contain: A capital letter, a number, 
        //a special character ($*&@#) and at least 8 characters 
        
        //This password is valid
        assertTrue(ValidatorPassword.validatePassword("Test@123"));
        
    }
    
    @Test
    public void testUserNotStrongPassword(){
        //The password must contain: A capital letter, a number, 
        //a special character ($*&@#) and at least 8 characters 
               
        //This password is not valid
        assertFalse(ValidatorPassword.validatePassword("test~123"));
    }
    
    @Test
    public void testPasswordFieldValid(){
        //these passwords are correct, they must return a empty string
        assertEquals("", ValidatorPassword.validatePasswordFields("Teste@123", "Teste@123"));
        assertEquals("", ValidatorPassword.validatePasswordFields("Teste@1234567890daadsagbxczdç", "Teste@1234567890daadsagbxczdç"));
    }
    
    @Test
    public void testPasswordFieldNotValid(){
        //these passwords are not correct, they must return a message
        assertEquals("Campo vazio", ValidatorPassword.validatePasswordFields(""));
        assertEquals("Campo vazio", ValidatorPassword.validatePasswordFields("", "a"));
        assertEquals("Senha fraca", ValidatorPassword.validatePasswordFields("a", "a"));
        assertEquals("Senha fraca", ValidatorPassword.validatePasswordFields("a", "b"));
        assertEquals("Senhas não são iguais", ValidatorPassword.validatePasswordFields("Teste@123", "a"));
    }
}
