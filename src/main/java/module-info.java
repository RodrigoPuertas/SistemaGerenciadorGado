module br.edu.fesa.gerenciador_gado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens br.edu.fesa.gerenciador_gado to javafx.fxml;
    opens br.edu.fesa.gerenciador_gado.Controllers to javafx.fxml;

    exports br.edu.fesa.gerenciador_gado;
    exports br.edu.fesa.gerenciador_gado.Controllers;
    exports br.edu.fesa.gerenciador_gado.Models;
    exports br.edu.fesa.gerenciador_gado.DAO;
}
