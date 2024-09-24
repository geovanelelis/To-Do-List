package todolist.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import todolist.controller.GerenciadorDeTarefas;
import todolist.model.NomeUsuario;
import todolist.model.Tarefa;

/**
 * Controlador para a tela principal de lista de tarefas. Responsável por
 * gerenciar a exibição e manipulação das tarefas do usuário.
 */
public class TelaTodoListController implements Initializable {

    /**
     * Gerenciador de tarefas para o usuário logado.
     */
    private GerenciadorDeTarefas gerenciador;

    /**
     * Label que exibe o nome do usuário logado.
     */
    @FXML
    private Label nomeUsuario;

    /**
     * ListView que exibe a lista de tarefas do usuário.
     */
    @FXML
    private ListView<Tarefa> listaDeTarefas;

    /**
     * Campo de texto para adicionar uma nova tarefa.
     */
    @FXML
    private TextField novaTarefa;

    /**
     * Método de inicialização da tela, carregando o nome do usuário e suas
     * tarefas.
     *
     * @param url o caminho do recurso.
     * @param rb o pacote de recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nomeUsuarioAtual = NomeUsuario.getNomeUsuario();
        nomeUsuario.setText(NomeUsuario.getNomeUsuario() + "!");

        gerenciador = new GerenciadorDeTarefas(nomeUsuarioAtual);

        listaDeTarefas.setItems(gerenciador.getTarefas());
    }

    /**
     * Adiciona uma nova tarefa à lista do usuário. Verifica se o campo de texto
     * não está vazio, cria uma nova tarefa e a adiciona à lista.
     *
     * @param event o evento de clique do botão.
     */
    @FXML
    void btnAdicionarTarefa(ActionEvent event) {
        String descricao = novaTarefa.getText();
        if (!descricao.isEmpty()) {
            gerenciador.adicionarTarefa(new Tarefa(descricao, NomeUsuario.getNomeUsuario()));
            listaDeTarefas.setItems(gerenciador.getTarefas());
            novaTarefa.clear();
        }
        nomeUsuario.setText(TelaCadastroController.usuarioCadastrado.getNome() + "!");
    }

    /**
     * Remove a tarefa selecionada da lista do usuário. Verifica se uma tarefa
     * foi selecionada antes de tentar removê-la.
     *
     * @param event o evento de clique do botão.
     */
    @FXML
    void btnExcluirTarefa(ActionEvent event) {
        Tarefa tarefaSelecionada = listaDeTarefas.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada != null) {
            gerenciador.removerTarefa(tarefaSelecionada);
            listaDeTarefas.setItems(gerenciador.getTarefas());
        }
    }
}
