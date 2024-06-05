module br.edu.fesa.gerenciador_gado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires org.jsoup;
    requires java.desktop;

    opens br.edu.fesa.gerenciador_gado to javafx.fxml;
    opens br.edu.fesa.gerenciador_gado.Controllers to javafx.fxml;

    exports br.edu.fesa.gerenciador_gado;
    exports br.edu.fesa.gerenciador_gado.Controllers;
    exports br.edu.fesa.gerenciador_gado.Models.BLL;
    exports br.edu.fesa.gerenciador_gado.Models.Entities;
    exports br.edu.fesa.gerenciador_gado.DAO;
    exports br.edu.fesa.gerenciador_gado.Util.Enums;
    exports br.edu.fesa.gerenciador_gado.Util.Exceptions;
    exports br.edu.fesa.gerenciador_gado.Util.Validations;
}
