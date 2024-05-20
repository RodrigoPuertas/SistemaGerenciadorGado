/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.models.test;
import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorEmail;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author Guilherme Garcia
 */
public class EmailValidationsTest {
    
    @Test
    public void testEmailValid(){
        assertTrue(ValidatorEmail.validateEmail("082210014@faculdade.cefsa.edu.br"));
        assertTrue(ValidatorEmail.validateEmail("0822_10014@faculdade.cefsa.edu.br"));
        assertTrue(ValidatorEmail.validateEmail("a0822_10014@faculdade.cefsa.edu.br"));
        assertTrue(ValidatorEmail.validateEmail("aB0822_10014@faculdade.cefsa.edu.br"));
    }
    
    @Test
    public void testEmailIsNotValid(){
        assertFalse(ValidatorEmail.validateEmail("082210014@faculdade.cefsa.edu."));
        assertFalse(ValidatorEmail.validateEmail("082210014faculdade.cefsa.edu.br"));
        assertFalse(ValidatorEmail.validateEmail("082210014@faculdade"));
        assertFalse(ValidatorEmail.validateEmail("@faculdade.com"));
        
    }
}
