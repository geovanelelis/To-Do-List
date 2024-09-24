package todolist.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import todolist.model.Usuario;

/**
 * Classe responsável pela leitura e escrita dos dados dos usuários em um arquivo de texto.
 */
public class FileUsers {

    private static final String FILE_PATH = "users.txt";

    
    /**
     * Lê os usuários de um arquivo de texto.
     *
     * @return uma lista observável de usuários lidos do arquivo, contendo o nome e a senha dos usuários.
     * @throws java.io.IOException se ocorrer um erro ao escrever no arquivo.
     */
    public static ObservableList<Usuario> lerUsuariosDoArquivo() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        java.io.File file = new java.io.File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOException("Erro ao criar o arquivo: " + e.getMessage(), e);
            }
        }

        try (BufferedReader lerUsuario = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = lerUsuario.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    usuarios.add(new Usuario(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            throw new IOException("Erro ao ler o arquivo: " + e.getMessage(), e);
        }
        return FXCollections.observableArrayList(usuarios);
    }

    /**
     * Salva um novo usuário no arquivo de texto.
     *
     * @param usuarios os dados do usuário que será salvo em um arquivo
     * @throws java.io.IOException se ocorrer um erro ao escrever no arquivo.
     */
    public static void salvarUsuarioNoArquivo(ObservableList<Usuario> usuarios) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getNome() + "," + usuario.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Erro ao escrever no arquivo: " + e.getMessage(), e);
        }
    }
}
