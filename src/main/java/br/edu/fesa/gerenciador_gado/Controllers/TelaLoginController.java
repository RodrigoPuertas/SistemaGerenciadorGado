/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.exception.PersistenceException;
import br.edu.fesa.gerenciador_gado.DAO.ConnectionDAO;
import br.edu.fesa.gerenciador_gado.Models.LoginModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Lohan
 */
public class TelaLoginController implements Initializable {

    public void EfetuaLogin() throws PersistenceException{  

        List<String> teste = new LinkedList<String>();

        try (Connection connection = ConnectionDAO.getConexao().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                teste.add(resultSet.getString("ID_USER"));
                teste.add(resultSet.getString("LOGIN_USER"));
                teste.add(resultSet.getString("PASSWORD"));
            }


        } catch (SQLException ex) {
            throw new PersistenceException("Erro ao listar os cargos", ex);
        }
    }
    
        
    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;
    
    @FXML
    private Label lblAlertaEmail;

    @FXML
    private Label lblAlertaSenha;

    @FXML
    void actionEntrar(ActionEvent event) {
        try{
            if(txtEmail.getText().isEmpty())
            {
                lblAlertaEmail.setText("Campo vazio!");
            }
            else{
                lblAlertaEmail.setText("");
            }
            
            if(txtSenha.getText().isEmpty())
            {
                lblAlertaSenha.setText("Campo vazio!");
            }
            else{
                lblAlertaSenha.setText("");
            }
            EfetuaLogin();
            LoginModel login = new LoginModel(txtEmail.getText(),txtSenha.getText());
        }catch(Exception error){
            System.out.println(error.getMessage());
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
