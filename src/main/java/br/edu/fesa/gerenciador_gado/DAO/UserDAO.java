package br.edu.fesa.gerenciador_gado.DAO;

import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
            throw new PersistenceException("Error while listing users", ex);
        }
        return users;
    }

    public String getPasswordByEmail(String email) throws PersistenceException {
        String password = "";
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT PASSWORD FROM USERS WHERE EMAIL = ? LIMIT 1")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                password = resultSet.getString("PASSWORD");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while loading user password", ex);
        }
        return password;
    }

    public User getUserByEmail(String email) throws PersistenceException {
        User user = new User();
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ? LIMIT 1")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getInt("ID_USER"));
                user.setName(resultSet.getString("NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setProfileCode(ProfileEnum.fromValue(resultSet.getInt("PROFILE_CODE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while loading user", ex);
        }
        return user;
    }

    @Override
    public void insert(User user) throws PersistenceException {
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (NAME, EMAIL, PASSWORD, PROFILE_CODE) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getProfileCode().getValue());
            statement.executeUpdate();
        } catch (SQLException ex) {
            
            //Exception para ver se há duas pks iguais
            if (ex instanceof SQLIntegrityConstraintViolationException) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenceException("Email já cadastrado", ex);
            } else {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenceException("Error while inserting user", ex);
            }
        }
    }

    @Override
    public void update(User user) throws PersistenceException {
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("UPDATE USERS SET NAME = ?, EMAIL = ?, PASSWORD = ?, PROFILE_CODE = ? WHERE ID_USER = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getProfileCode().getValue());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while updating user", ex);
        }
    }

    @Override
    public void remove(User user) throws PersistenceException {
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS WHERE ID_USER = ?")) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while removing user", ex);
        }
    }

    @Override
    public User listById(User user) throws PersistenceException {
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE ID_USER = ? LIMIT 1")) {
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setName(resultSet.getString("NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setProfileCode(ProfileEnum.fromValue(resultSet.getInt("PROFILE_CODE")));
            } else {
                throw new PersistenceException("User not found with provided ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while listing user by ID", ex);
        }
        return user;
    }
}
