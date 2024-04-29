/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.DAO;

import br.edu.fesa.gerenciador_gado.Models.UserModel;
import br.edu.fesa.gerenciador_gado.Models.UserProfileModel;
import br.edu.fesa.gerenciador_gado.exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulo
 */
public class UserDAO implements GenericDAO<UserModel>{

    @Override
    public List<UserModel> list() throws PersistenceException {
        List<UserModel> users = new ArrayList<>();
        try (Connection connection = ConnectionDAO.getConnectionDAO().getConnection();
             PreparedStatement statement = connection.prepareStatement("select id_user, name, email, u.id_profile, description from users u\n" +
                "left join profiles p on u.id_profile = p.id_profile ");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {                
                users.add(new UserModel(resultSet.getInt("id_user"), resultSet.getString("name"), resultSet.getString("email"), new UserProfileModel(resultSet.getInt("id_profile"), resultSet.getString("description"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Erro ao listar os cargos", ex);
        }
        return users;
    }

    @Override
    public void insert(UserModel e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(UserModel e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove(UserModel e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserModel listById(UserModel e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}