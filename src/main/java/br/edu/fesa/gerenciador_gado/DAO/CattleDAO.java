/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.DAO;

import br.edu.fesa.gerenciador_gado.Models.Entities.Cattle;
import br.edu.fesa.gerenciador_gado.Util.Enums.CattleAplicationEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.GenderEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.RacaGadoEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Lohan
 */
public class CattleDAO implements GenericDAO<Cattle> {

    @Override
    public List<Cattle> list() throws PersistenceException {
        List<Cattle> cattle = new ArrayList<>();

        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT * FROM GADO");  ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                cattle.add(new Cattle(resultSet.getInt("ID"), CattleAplicationEnum.fromValue(resultSet.getString("Aplicacao")), RacaGadoEnum.fromValue(resultSet.getString("Raca")), GenderEnum.fromString(resultSet.getString("Sexo")),
                        resultSet.getDate("Data_Nascimento").toLocalDate(), resultSet.getString("Descricao"), resultSet.getString("Observacoes")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CattleDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while listing cattles", ex);
        }
        return cattle;
    }

    @Override
    public void insert(Cattle cattle) throws PersistenceException {
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Gado (Raca, Sexo, Data_Nascimento, Aplicacao,"
                + " Descricao, Observacoes) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, cattle.getRaca().getValue());
            statement.setString(2, Character.toString(cattle.getGender().getGenderChar()));
            statement.setDate(3, Date.valueOf(cattle.getDataNascimento()));
            statement.setString(4, cattle.getAplication().getValue());
            statement.setString(5, cattle.getDescricao().toString());
            statement.setString(6, cattle.getObservacao().toString());
            statement.execute();
        } catch (SQLException ex) {

            //Exception para ver se há duas pks iguais
            if (ex instanceof SQLIntegrityConstraintViolationException) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenceException("Gado já cadastrado", ex);
            } else {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenceException("Error while inserting cattle", ex);
            }
        }
    }

    @Override
    public void update(Cattle cattle) throws PersistenceException {
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement(
                "UPDATE Gado SET Raca = ?, Sexo = ?, Data_Nascimento = ?, Aplicacao = ?, Descricao = ?, Observacoes = ? WHERE ID = ?")) {
            statement.setString(1, cattle.getRaca().getValue());
            statement.setString(2, Character.toString(cattle.getGender().getGenderChar()));
            statement.setDate(3, Date.valueOf(cattle.getDataNascimento()));
            statement.setString(4, cattle.getAplication().getValue());
            statement.setString(5, cattle.getDescricao().toString());
            statement.setString(6, cattle.getObservacao().toString());
            
            statement.setInt(7, cattle.getId());
                    
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while updating user", ex);
        }
    }

    @Override
    public void remove(Cattle cattle) throws PersistenceException {
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("DELETE FROM Gado WHERE ID = ?")) {
            statement.setInt(1, cattle.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while removing cattle", ex);
        }
    }

}
