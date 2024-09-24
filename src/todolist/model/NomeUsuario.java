package todolist.model;

/**
 * Classe utilitária para armazenar e recuperar o nome do usuário logado.
 */
public class NomeUsuario {

    private static String nomeUsuario;

    /**
     * Retorna o nome do usuário logado.
     *
     * @return o nome do usuário.
     */
    public static String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * Define o nome do usuário logado.
     *
     * @param nome o nome do usuário.
     */
    public static void setNomeUsuario(String nome) {
        nomeUsuario = nome;
    }
}
