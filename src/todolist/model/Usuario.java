package todolist.model;

/**
 * Classe que representa um usuário do sistema.
 */
public class Usuario {

    /**
     * Nome do usuário.
     */
    private String nome;
    
    /**
     * Senha do usuário.
     */
    private String senha;

    /**
     * Construtor da classe Usuario.
     *
     * @param nome: Nnome do usuário.
     * @param senha: Senha do usuário.
     */
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return o nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return a senha do usuário.
     */
    public String getPassword() {
        return senha;
    }

    public void setPassword(String password) {
        this.senha = password;
    }

    /**
     * Verifica se o nome de usuário e a senha fornecidos correspondem aos dados do usuário atual.
     *
     * @param nome Nome de usuário a ser verificado.
     * @param senha Senha a ser verificada.
     * @return true se o nome de usuário e a senha correspondem; caso contrário, @return false.
     */
    public boolean verificaLogin(String nome, String senha) {
        return (this.nome.equals(nome)) && (this.senha.equals(senha));
    }
}
