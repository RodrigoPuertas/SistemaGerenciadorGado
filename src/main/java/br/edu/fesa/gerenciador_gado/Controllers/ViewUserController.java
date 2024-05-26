package br.edu.fesa.gerenciador_gado.Controllers;

import br.edu.fesa.gerenciador_gado.DAO.UserDAO;
import br.edu.fesa.gerenciador_gado.Models.Entities.User;
import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;
import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<User, String> colunaProfileCode;

    @FXML
    private TableView<User> tblUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            colunaNome = new TableColumn<>("Nome");
            colunaEmail = new TableColumn<>("E-mail");
            colunaProfileCode = new TableColumn<>("Profile code");

            colunaNome.setCellValueFactory(new PropertyValueFactory<>("name"));
            colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colunaProfileCode.setCellValueFactory(new PropertyValueFactory<>("profileCode"));

            tblUsers.getColumns().addAll(colunaNome, colunaEmail, colunaProfileCode);

            UserDAO userDAO = new UserDAO();
            ObservableList<User> users = FXCollections.observableArrayList(userDAO.list());

            tblUsers.setItems(users);
        } catch (Exception error) {
            System.out.println(error.toString());
        }
    }

}
