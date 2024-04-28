/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.Models.LoginModel;
import java.net.URL;
import java.util.ArrayList;
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

        List<LoginModel> teste = new LinkedList<>();

        // Crie os objetos LoginModel
        LoginModel login1 = new LoginModel("@teste1", "111");
        LoginModel login2 = new LoginModel("@teste2", "222");
        LoginModel login3 = new LoginModel("@teste3", "333");
        LoginModel login4 = new LoginModel("@teste4", "444");
        LoginModel login5 = new LoginModel("@teste5", "555");

        
/*
// Adicione os objetos à lista
        
        teste.add(login1);
        teste.add(login2);
        teste.add(login3);
        teste.add(login4);
        teste.add(login5); 

//não sei porque não adiciona*/
    
        
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
