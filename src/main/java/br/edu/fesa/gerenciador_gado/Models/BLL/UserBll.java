/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.BLL;

import br.edu.fesa.gerenciador_gado.DAO.UserDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Rodrigo Puertas
 */
public class UserBll {

    public void UserRegister(String userName, String email, String password, ProfileEnum profileCode) throws NoSuchAlgorithmException, PersistenceException{
        
        // Criptografando a senha antes de salvar no banco de dados
        String senhaCriptografada = PasswordHasher.hashPassword(password);

        // Criando um objeto de usuário com os dados fornecidos pelo usuário
        User newUser = new User(userName, email, senhaCriptografada, profileCode );

        // Inserindo o novo usuário no banco de dados
        UserDAO userDAO = new UserDAO();
        userDAO.insert(newUser);


        // Exibindo uma mensagem de sucesso
        ControllerHelper.alertSucessGeneric("User registered successfully!");
    }  
}
