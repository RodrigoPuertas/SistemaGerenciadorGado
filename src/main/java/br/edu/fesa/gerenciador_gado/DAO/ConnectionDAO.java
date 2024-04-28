/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lohan
 * @author Rodrigo Puertas
 */
public class ConnectionDAO {
        public Connection connectionDB(){
        Connection conn = null;  
        try{
            String url = "jdbc:mysql://sistema-gerenciamento-gado.mysql.database.azure.com:3306/{SGG001}?useSSL=true";
            conn=DriverManager.getConnection(url, "user_admin", "{your_password}");
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
        }
        return conn;
    }
}
