package todolist.exception;

/**
 * Exceção personalizada que indica falha no processo de login.
 */
public class LoginFailedException extends Exception {

    /**
     * Construtor da exceção LoginFailedException.
     *
     * @param message mensagem detalhada da exceção.
     */
    public LoginFailedException(String message) {
        super(message);
    }
}
