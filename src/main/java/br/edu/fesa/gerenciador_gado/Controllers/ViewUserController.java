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
    private TableView<User> tblUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<User, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<User, String> colunaEmail = new TableColumn<>("E-mail");
        TableColumn<User, String> colunaProfileCode = new TableColumn<>("Profile code");

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaProfileCode.setCellValueFactory(new PropertyValueFactory<>("profileCode"));

        tblUsers.getColumns().addAll(colunaNome, colunaEmail, colunaProfileCode);

        try {
            UserDAO userDAO = new UserDAO();
            ObservableList<User> users = FXCollections.observableArrayList(userDAO.list());

            tblUsers.setItems(users);
        } catch (PersistenceException error) {
            System.out.println(error.toString());
        }
    }

}
