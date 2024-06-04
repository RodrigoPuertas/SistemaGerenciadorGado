/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.Models.Entities.Cattle;
import br.edu.fesa.gerenciador_gado.Models.Entities.HistoricoPesosGado;
import br.edu.fesa.gerenciador_gado.Util.Enums.CattleAplicationEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.GenderEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.RacaGadoEnum;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<HistoricoPesosGado, LocalDate> colunaDataPesagem;

    @FXML
    private TableColumn<Cattle, String> colunaDescricao;

    @FXML
    private TableColumn<HistoricoPesosGado, Integer> colunaID;

    @FXML
    private TableColumn<Cattle, Integer> colunaIDCattle;

    @FXML
    private TableColumn<Cattle, String> colunaObservacao;

    @FXML
    private TableColumn<HistoricoPesosGado, Double> colunaPeso;

    @FXML
    private TableColumn<Cattle, RacaGadoEnum> colunaRaca;

    @FXML
    private TableColumn<Cattle, GenderEnum> colunaSexo;

    @FXML
    private Pane paneLeft;

    @FXML
    private TableView<Cattle> tblCattle;

    @FXML
    private TableColumn<HistoricoPesosGado, Integer> tblID_Gado;

    @FXML
    private TableView<HistoricoPesosGado> tblWeightHistory;

    @FXML
    private DatePicker txtDataPesagem;

    @FXML
    private TextField txtPeso;

    @FXML
    void actionBack(ActionEvent event) {
        try {
            
        } catch (Exception error)  {
            
        }

    }

    @FXML
    void actionMoveToSIngUp(ActionEvent event) {
        
    }

    @FXML
    void actionShowDataCattle(MouseEvent event) {
        
    }   

    @FXML
    void actionShowDataWeight(MouseEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
