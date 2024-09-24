package todolist.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import todolist.exception.LoginFailedException;
import todolist.model.NomeUsuario;

/**
 * Controlador para a tela de login da aplicação. Responsável por gerenciar o
 * processo de autenticação do usuário e navegação entre as telas de login e
 * cadastro.
 */
public class TelaLoginController {

    /**
     * Campo de texto para o nome do usuário.
     */
    @FXML
    private TextField nomeLogin;

    /**
     * Campo de texto para a senha do usuário.
     */
    @FXML
    private PasswordField senhaLogin;

    /**
     * Método responsável por autenticar o usuário. Verifica se o nome de
     * usuário e senha correspondem a um usuário registrado. Em caso de sucesso,
     * redireciona para a tela principal de tarefas. Em caso de falha, exibe uma
     * mensagem de erro.
     *
     * @param event o evento de clique do botão.
     * @throws IOException se ocorrer um erro ao ler o arquivo de usuários ou carregar a tela.
     */
    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        String nome = nomeLogin.getText();
        String senha = senhaLogin.getText();

        boolean sucessoNoLogin = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] usuarioData = linha.split(",");
                if (usuarioData[0].equals(nome) && usuarioData[1].equals(senha)) {
                    sucessoNoLogin = true;
                    break;
                }
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Leitura");
            alert.setHeaderText("Não foi possível ler os dados dos usuários.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

        NomeUsuario.setNomeUsuario(nome);

        try {
            if (sucessoNoLogin) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login bem-sucedido!");
                alert.setHeaderText("Login realizado com sucesso!");
                alert.setContentText("Nome: " + nome);
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("/todolist/gui/TelaTodoList.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                throw new LoginFailedException("Usuário ou senha incorretos!");
            }
        } catch (LoginFailedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tente novamente!");
            alert.setHeaderText("Erro no Login");
            alert.setContentText("Usuário ou senha incorretos!");
            alert.showAndWait();
        }
    }

    /**
     * Método que redireciona o usuário para a tela de cadastro.
     *
     * @param event o evento de clique do botão.
     * @throws IOException se ocorrer um erro ao carregar o arquivo FXML.
     */
    @FXML
    void btnCadastrar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/todolist/gui/TelaCadastro.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
