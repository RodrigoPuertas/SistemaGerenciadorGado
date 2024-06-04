/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.DAO.CattleDAO;
import br.edu.fesa.gerenciador_gado.DAO.HistoricoPesosGadoDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.Cattle;
import br.edu.fesa.gerenciador_gado.Models.Entities.HistoricoPesosGado;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.CattleAplicationEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.GenderEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.RacaGadoEnum;
import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorFields;
import br.edu.fesa.gerenciador_gado.Util.Validations.ValidatorResults;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Lohan
 */
public class ViewWeightHistoryController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Cattle, CattleAplicationEnum> colunaAplicacao;

    @FXML
    private TableColumn<Cattle, LocalDate> colunaDataNascimento;

    @FXML
    private TableColumn<Cattle, String> colunaDescricao;

    @FXML
    private TableColumn<Cattle, Integer> colunaID;

    @FXML
    private TableColumn<Cattle, Integer> colunaIDCattle;

    @FXML
    private TableColumn<Cattle, String> colunaObservacao;


    @FXML
    private TableColumn<Cattle, RacaGadoEnum> colunaRaca;

    @FXML
    private TableColumn<Cattle, GenderEnum> colunaSexo;

    @FXML
    private Pane paneLeft;

    @FXML
    private TableView<Cattle> tblCattle;

    @FXML
    private TableView<HistoricoPesosGado> tblWeightHistory;
    
    @FXML
    private TableColumn<HistoricoPesosGado, Integer> tblID_Gado;
    
    @FXML
    private TableColumn<HistoricoPesosGado, Double> colunaPeso;
    
    @FXML
    private TableColumn<HistoricoPesosGado, LocalDate> colunaDataPesagem;
    
    @FXML
    private TableColumn<HistoricoPesosGado, Integer> colunaIDHist;

    @FXML
    private DatePicker txtDataPesagem;

    @FXML
    private TextField txtPeso;
    
    @FXML
    private Label lblAlertaPeso;

    @FXML
    private Label lblAlertaDataPesagem;

    @FXML
    void actionBack(ActionEvent event) {
        try {
            App.setRoot("viewHome");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionRegister(ActionEvent event) {
        try{
            Cattle cattle = tblCattle.getSelectionModel().getSelectedItem();
            if(cattle.getId() != 0 && cattle.getId() != null)
            {
                ValidatorResults resultPeso = ValidatorFields.ValidateIsEmpty(txtPeso);
                ValidatorResults resultDataPesagem = ValidatorFields.ValidateIsEmpty(txtDataPesagem);

                lblAlertaDataPesagem.setText(resultPeso.getErrorMessage());
                lblAlertaPeso.setText(resultDataPesagem.getErrorMessage());

                if (resultPeso.isIsValid() && resultDataPesagem.isIsValid()){
                    var dataPesagem = txtDataPesagem.getValue();
                    var peso = Double.parseDouble(txtPeso.getText());
                    HistoricoPesosGado pesoGado = new HistoricoPesosGado(dataPesagem, cattle.getId(), peso);
                    HistoricoPesosGadoDAO histPesosDao = new HistoricoPesosGadoDAO();
                    histPesosDao.insert(pesoGado);
                }               
            }
            else{
               ControllerHelper.alertWarningGeneric("Selecione um gado para adicionar o peso!"); 
            }
            
        }catch(Exception error){
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionUpdate(ActionEvent event) {
        try{
            HistoricoPesosGado histSelected = tblWeightHistory.getSelectionModel().getSelectedItem();
            
            if(histSelected != null){
                
                ValidatorResults resultPeso = ValidatorFields.ValidateIsEmpty(txtPeso);
                ValidatorResults resultDataPesagem = ValidatorFields.ValidateIsEmpty(txtDataPesagem);

                lblAlertaDataPesagem.setText(resultPeso.getErrorMessage());
                lblAlertaPeso.setText(resultDataPesagem.getErrorMessage());

                if (resultPeso.isIsValid() && resultDataPesagem.isIsValid()){
                    HistoricoPesosGadoDAO histPesosDao = new HistoricoPesosGadoDAO();
                    histSelected.setDataPesagem(txtDataPesagem.getValue());
                    histSelected.setPesoKg(Double.parseDouble(txtPeso.getText()));
                    histPesosDao.update(histSelected);
                }               
            }
            else{
               ControllerHelper.alertWarningGeneric("Selecione um peso para atualizar!"); 
            }
            
            
        }catch(Exception error){
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }   
    
    @FXML
    void preencheCamposPeso(){
        HistoricoPesosGado histSelected = tblWeightHistory.getSelectionModel().getSelectedItem();
        txtDataPesagem.setValue(histSelected.getDataPesagem());
        txtPeso.setText(String.valueOf(histSelected.getPesoKg()));
    }

    @FXML
    void actionDelete(ActionEvent event) {
        try{
        HistoricoPesosGado histSelected = tblWeightHistory.getSelectionModel().getSelectedItem();
        
        if(histSelected != null ){
            HistoricoPesosGadoDAO histPesosDao = new HistoricoPesosGadoDAO();
            histPesosDao.remove(histSelected);
        }
        else{
               ControllerHelper.alertWarningGeneric("Selecione um peso para remover!");
        }
        }catch(Exception error){
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTable();
    }
    
    private void createTable() {
        try {
            colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
            colunaSexo.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colunaDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
            colunaAplicacao.setCellValueFactory(new PropertyValueFactory<>("aplication"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colunaObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));
            
            tblCattle.getColumns().clear(); 
            tblCattle.getColumns().addAll(colunaID, colunaRaca, colunaSexo, colunaDataNascimento, colunaAplicacao, colunaDescricao, colunaObservacao);
          
            CattleDAO dao = new CattleDAO();
            ObservableList<Cattle> cattle = FXCollections.observableArrayList(dao.list());

            tblCattle.setItems(cattle);
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }
    
    @FXML
    private void loadTableHistory(MouseEvent event) {
    try {
        Cattle cattle = tblCattle.getSelectionModel().getSelectedItem();
        HistoricoPesosGadoDAO histPesosDao = new HistoricoPesosGadoDAO();
        
        List<HistoricoPesosGado> listaHistorico = histPesosDao.listByIdGado(cattle.getId());
        
        // Limpa as colunas da tabela antes de adicionar novamente
        tblWeightHistory.getColumns().clear();
        
        if(listaHistorico != null){
            // Adiciona as colunas à tabela
            tblID_Gado.setCellValueFactory(new PropertyValueFactory<>("idGado"));
            colunaPeso.setCellValueFactory(new PropertyValueFactory<>("pesoKg"));
            colunaDataPesagem.setCellValueFactory(new PropertyValueFactory<>("dataPesagem"));
            colunaIDHist.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            tblWeightHistory.getColumns().addAll(colunaIDHist, tblID_Gado, colunaPeso, colunaDataPesagem);
            
            // Adiciona os itens à tabela
            ObservableList<HistoricoPesosGado> listaRetorno = FXCollections.observableArrayList(listaHistorico);
            tblWeightHistory.setItems(listaRetorno);
        }
    } catch (Exception error) {
        ControllerHelper.alertWarningGeneric(error.toString());
    }
}

}
