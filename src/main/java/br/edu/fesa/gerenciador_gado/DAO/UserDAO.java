/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.DAO;

import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
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
public class UserDAO implements GenericDAO<User> {

    @Override
    public List<User> list() throws PersistenceException {
        List<User> users = new ArrayList<>();
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS");  ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("ID_USER"), resultSet.getString("NAME"), resultSet.getString("EMAIL"), ProfileEnum.valueOf(resultSet.getString("PROFILE_CODE"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Erro ao listar os usuários", ex);
        }
        return users;
    }

    public String getPasswordByEmail(String email) throws PersistenceException {
        String password = "";
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT PASSWORD FROM USERS WHERE EMAIL = '" + email + "'LIMIT 1");  ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                password = resultSet.getString("PASSWORD");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Erro carregar usuário", ex);
        }
        return password;
    }

    public User getUserByEmail(String email) throws PersistenceException {
        User user = new User();
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL = '" + email + "'LIMIT 1");  ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                user.setId(resultSet.getInt("ID_USER"));
                user.setName(resultSet.getString("NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setProfileCode(ProfileEnum.fromValue(resultSet.getInt("PROFILE_CODE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Erro carregar o usuário", ex);
        }
        return user;
    }

    @Override
    public void insert(User e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove(User e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User listById(User e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
