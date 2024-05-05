/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.BLL;

import br.edu.fesa.gerenciador_gado.DAO.UserDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.DTO.ActionReturnDTO;
import br.edu.fesa.gerenciador_gado.Util.PasswordHasher;
import br.edu.fesa.gerenciador_gado.Util.UserSession;

/**
 *
 * @author paulo
 */
public class LoginBll {
//Business Logic Layer

    private UserDAO userDao;

    public ActionReturnDTO performLogin(String email, String viewPassword) {
        ActionReturnDTO actionReturnDTO = new ActionReturnDTO();
        try {
            userDao = new UserDAO();
            String databasePassword;

            databasePassword = userDao.getPasswordByEmail(email);
            Boolean dataBaseAndViewPasswordIsEquals = PasswordHasher.passwordEquals(databasePassword, viewPassword);
            if (databasePassword.isEmpty() || !dataBaseAndViewPasswordIsEquals) //usuário não existe ou senha incorreta
            {
                throw new Exception("Email ou senha incorretos!");
            } else {
                User user = userDao.getUserByEmail(email);
                UserSession userSession = UserSession.getInstance();
                userSession.setUser(user);

                actionReturnDTO.setReturnAction(true);
                
                return actionReturnDTO;
            }

        } catch (Exception e) {
            actionReturnDTO.setMessage(e.getMessage());
            return actionReturnDTO;
        }
    }
}
