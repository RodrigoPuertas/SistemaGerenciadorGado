package br.edu.fesa.gerenciador_gado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("telaCadastro"), 1000, 600);

        //Adiciona um icone no canto superior esquerdo da tela e na sua inicialização
        Image icon = new Image(getClass().getResourceAsStream("/br/edu/fesa/gerenciador_gado/images/cow.png"));
        stage.getIcons().add(icon);
        
        stage.setTitle("Login"); //Adiciona texto no canto superior esquerdo da tela
        
        
        stage.setResizable(false); // Impede que a janela seja redimensionada        
        
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}