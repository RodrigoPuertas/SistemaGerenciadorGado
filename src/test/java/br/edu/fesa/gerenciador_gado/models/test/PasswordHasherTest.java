/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.models.test;
import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author USER
 */
public class PasswordHasherTest {
    
    @Test
    public void passwordHasherValid() throws Exception{
        //test password 'Teste@123'
        assertEquals("a971ea05a8ca96b660b4892b1de9ef8ded0a5f737f286110fe13e2787af4c8be", PasswordHasher.hashPassword("Teste@123"));
        
    }
}
