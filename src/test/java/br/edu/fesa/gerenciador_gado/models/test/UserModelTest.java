package br.edu.fesa.gerenciador_gado.models.test;

import br.edu.fesa.gerenciador_gado.Models.UserModel;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 
@author Rodrigo Puertas*/


public class UserModelTest {

    @Test
    public void testGetterSetter() {
        UserModel user = new UserModel();

        // Teste para o método setId
        user.setId(1);
        assertEquals(Integer.valueOf(1), user.getId());

        // Teste para o método setNome
        user.setName("João");
        assertEquals("João", user.getName());

        // Teste para o método setEmail
        user.setEmail("joao@example.com");
        assertEquals("joao@example.com", user.getEmail());

    }
}