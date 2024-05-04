/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.BLL;

import br.edu.fesa.gerenciador_gado.DAO.UserDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Util.UserSession;

/**
 *
 * @author paulo
 */
public class LoginBll {

    private UserDAO userDao;

    public String performLogin(String email, String viewPassword) {
        try {
            userDao = new UserDAO();
            String databasePassword;

            databasePassword = userDao.getPasswordByEmail(email);
            if (databasePassword.equals("")) //usuário não existe ou senha incorreta
            {
                throw new Exception("Email ou senha incorretos!");
            } else if (!PasswordHasher.passwordEquals(databasePassword, viewPassword)) //usuário existe e a senha está incorreta
            {
                throw new Exception("Email ou senha incorretos!");
            } else {
                User user = userDao.getUserByEmail(email);
                UserSession.getInstace(user);
                return "Logado com sucesso";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
