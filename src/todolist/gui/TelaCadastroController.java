package todolist.gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
import todolist.model.Usuario;

/**
 * Controlador para a tela de cadastro de novos usuários. Responsável por
 * gerenciar o processo de registro de usuários e navegação entre as telas de
 * cadastro e login.
 */
public class TelaCadastroController {

    /**
     * Campo de texto para o nome do usuário.
     */
    @FXML
    private TextField nomeCadastro;

    /**
     * Campo de texto para a senha do usuário.
     */
    @FXML
    private PasswordField senhaCadastro;

    /**
     * Instância do usuário cadastrado.
     */
    public static Usuario usuarioCadastrado;

    /**
     * Método responsável por cadastrar um novo usuário. O método verifica se os
     * campos de nome e senha estão preenchidos, salva o usuário em um arquivo
     * texto e exibe um alerta de sucesso.
     *
     * @param event o evento de clique do botão.
     * @throws IOException se ocorrer um erro ao salvar o usuário no arquivo.
     */
    @FXML
    void btnCadastrar(ActionEvent event) throws IOException {

        String nome = nomeCadastro.getText();
        String senha = senhaCadastro.getText();

        if (!nome.isEmpty() && !senha.isEmpty()) {
            usuarioCadastrado = new Usuario(nome, senha);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                writer.write(nome + "," + senha);
                writer.newLine();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao salvar");
                alert.setHeaderText("Não foi possível salvar o usuário.");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro bem-sucedido");
            alert.setHeaderText("Usuário cadastrado com sucesso!");
            alert.setContentText("Nome: " + nome);
            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("/todolist/gui/TelaLogin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no Cadastro");
            alert.setHeaderText("Erro ao cadastrar o usuário!!!");
            alert.setContentText("Você precisa preencher os campos de Nome e Senha!");
            alert.showAndWait();
        }
    }

    /**
     * Método que redireciona o usuário para a tela de login.
     *
     * @param event o evento de clique do botão.
     * @throws IOException se ocorrer um erro ao carregar o arquivo FXML.
     */
    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/todolist/gui/TelaLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
