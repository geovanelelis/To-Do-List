package todolist.model;

/**
 * Classe que representa uma tarefa no sistema.
 */
public class Tarefa {

    /**
     * Descrição da tarefa.
     */
    private String descricao;
    
    /**
     * Indica se a tarefa está concluída.
     */
    private String usuario;

    
    /**
     * Construtor da classe Tarefa.
     *
     * @param descricao a descrição da tarefa.
     * @param usuario o usuário relacionado à tarefa.
     */
    public Tarefa(String descricao, String usuario) {
        this.descricao = descricao;
        this.usuario = usuario;
    }

    /**
     * Retorna a descrição da tarefa.
     *
     * @return a descrição da tarefa.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o usuário relacionado à tarefa.
     *
     * @return o usuário relacionado à tarefa.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Retorna a representação em string da tarefa.
     *
     * @return a descrição da tarefa seguida pelo nome do usuário, separados por um hífen.
     */
    @Override
    public String toString() {
        return getDescricao() + " - " + getUsuario();
    }
}
