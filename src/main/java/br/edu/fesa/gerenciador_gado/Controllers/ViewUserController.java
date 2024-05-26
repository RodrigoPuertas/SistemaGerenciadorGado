package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.App;
import br.edu.fesa.gerenciador_gado.DAO.UserDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.ControllerHelper;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class ViewUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<User, String> colunaEmail;

    @FXML
    private TableColumn<User, String> colunaNome;

    @FXML
    private TableColumn<User, ProfileEnum> colunaProfileCode;

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private Pane paneLeft;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<ProfileEnum> cboPerfil;

    @FXML
    private Button btnUpdate;

    @FXML
    void actionBack(ActionEvent event) {
        try {
            App.setRoot("viewHome");
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionDelete(ActionEvent event) throws PersistenceException {
        try {
            User user = tblUsers.getSelectionModel().getSelectedItem();
            UserDAO userDAO = new UserDAO();
            userDAO.remove(user);
            refresh();
        } catch (PersistenceException error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionUpdate(ActionEvent event) {
        try {

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmail(tblUsers.getSelectionModel().getSelectedItem().getEmail());
            String password = userDAO.getPasswordByEmail(tblUsers.getSelectionModel().getSelectedItem().getEmail());
            
            user.setPassword(password);
            
            user.setName(txtNome.getText());
            user.setEmail(txtEmail.getText());
            user.setProfileCode(cboPerfil.getValue());
            
            userDAO.update(user);
            refresh();
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @FXML
    void actionShowData(MouseEvent event) {
        try {
            User user = tblUsers.getSelectionModel().getSelectedItem();
            txtNome.setText(user.getName());
            txtEmail.setText(user.getEmail());
            cboPerfil.setValue(user.getProfileCode());

        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTable();
        cboPerfil.setItems(FXCollections.observableArrayList(ProfileEnum.values()));
    }

    public void createTable() {
        try {
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("name"));
            colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colunaProfileCode.setCellValueFactory(new PropertyValueFactory<>("profileCode"));

            tblUsers.getColumns().addAll(colunaNome, colunaEmail, colunaProfileCode);

            UserDAO userDAO = new UserDAO();
            ObservableList<User> users = FXCollections.observableArrayList(userDAO.list());

            tblUsers.setItems(users);
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }
    }

    public void refresh() {
        try {
            tblUsers.getItems().clear();

            UserDAO userDAO = new UserDAO();
            ObservableList<User> users = FXCollections.observableArrayList(userDAO.list());
            tblUsers.setItems(users);
        } catch (Exception error) {
            ControllerHelper.alertErrorGeneric(error.getMessage());
        }

    }

}
