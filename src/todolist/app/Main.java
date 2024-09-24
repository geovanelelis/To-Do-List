package todolist.app;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principal que inicializa a aplicação JavaFX para o gerenciamento de
 * tarefas. Esta classe carrega a tela de login ao iniciar.
 */
public class Main extends Application {

    /**
     * Método que inicia a aplicação carregando a tela de login.
     *
     * @param stage a tela principal da aplicação.
     * @throws Exception se ocorrer um erro ao carregar o
     * arquivo FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/todolist/gui/TelaLogin.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("ToDo List");
        stage.show();
    }

    /**
     * Método principal para iniciar a aplicação JavaFX.
     *
     * @param args os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
