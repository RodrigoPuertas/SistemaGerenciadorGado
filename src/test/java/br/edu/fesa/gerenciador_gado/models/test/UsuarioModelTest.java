package br.edu.fesa.gerenciador_gado.models.test;

import br.edu.fesa.gerenciador_gado.Models.UsuarioModel;
import br.edu.fesa.gerenciador_gado.Models.PerfilUsuarioModel;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Rodrigo Puertas
 */


public class UsuarioModelTest {
    
    @Test
    public void testGetterSetter() {
        UsuarioModel usuario = new UsuarioModel();
        
        // Teste para o método setId
        usuario.setId(1);
        assertEquals(Integer.valueOf(1), usuario.getId());
        
        // Teste para o método setNome
        usuario.setNome("João");
        assertEquals("João", usuario.getNome());
        
        // Teste para o método setEmail
        usuario.setEmail("joao@example.com");
        assertEquals("joao@example.com", usuario.getEmail());
        
        // Teste para o método setRegistro
        usuario.setRegistro("123456789");
        assertEquals("123456789", usuario.getRegistro());
    }
}

