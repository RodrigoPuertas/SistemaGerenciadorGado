/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.DAO;

import br.edu.fesa.gerenciador_gado.Models.Entities.HistoricoPesosGado;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class HistoricoPesosGadoDAO implements GenericDAO<HistoricoPesosGado> {

    @Override
    public List<HistoricoPesosGado> list() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public HistoricoPesosGado listGrouped() throws PersistenceException{
        List<HistoricoPesosGado> pesos = new ArrayList<>();
        try ( Connection connection = ConnectionDAO.getConnectionDAO().getConnection();  PreparedStatement statement = connection.prepareStatement("SELECT Data_Pesagem, sum(Peso_kg) as Peso_kg FROM historicopesosgado INNER JOIN gado ON historicopesosgado.ID_Gado = Gado.ID WHERE Gado.Aplicacao = 'Corte' OR Gado.Aplicacao = 'Misto' GROUP BY Data_Pesagem ORDER BY Data_Pesagem DESC LIMIT 1");  ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                pesos.add(new HistoricoPesosGado(0, resultSet.getDate("Data_Pesagem").toLocalDate(), 0, 0, resultSet.getDouble("Peso_kg")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException("Error while listing historicopeso", ex);
        }
        return pesos.get(0);
    }

    @Override
    public void insert(HistoricoPesosGado e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HistoricoPesosGado e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove(HistoricoPesosGado e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
