package todolist.controller;

import javafx.collections.ObservableList;
import todolist.file.FileTasks;
import todolist.model.Tarefa;

/**
 * Classe responsável pelo gerenciamento das tarefas de um usuário. Permite
 * adicionar, remover e listar tarefas.
 */
public class GerenciadorDeTarefas {

    /**
     * Lista observável de tarefas associadas ao usuário.
     */
    private ObservableList<Tarefa> tarefas;

    /**
     * Nome do usuário atualmente logado.
     */
    private String usuarioAtual;

    /**
     * Construtor da classe GerenciadorDeTarefas.
     *
     * @param usuarioAtual o nome do usuário atualmente logado.
     */
    public GerenciadorDeTarefas(String usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
        this.tarefas = FileTasks.lerTarefasDoArquivo(usuarioAtual);
    }

    /**
     * Retorna a lista de tarefas do usuário.
     *
     * @return uma lista observável de tarefas.
     */
    public ObservableList<Tarefa> getTarefas() {
        return tarefas;
    }

    /**
     * Adiciona uma nova tarefa à lista e salva no arquivo.
     *
     * @param tarefa a tarefa a ser adicionada.
     */
    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        FileTasks.salvarTarefasNoArquivo(tarefas, usuarioAtual);
    }

    /**
     * Remove uma tarefa da lista e atualiza o arquivo.
     *
     * @param tarefa a tarefa a ser removida.
     */
    public void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
        FileTasks.salvarTarefasNoArquivo(tarefas, usuarioAtual);
    }
}
