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
import todolist.model.Tarefa;

/**
 * Classe responsável pela leitura e escrita das tarefas de um usuário em um
 * arquivo de texto.
 */
public class FileTasks {

    private static String getFilePath(String usuario) {
        return "tarefas_" + usuario + ".txt";
    }

    /**
     * Lê as tarefas de um arquivo de texto associado ao usuário.
     *
     * @param usuario o nome do usuário cujas tarefas serão lidas.
     * @return uma lista observável de tarefas lidas do arquivo.
     */
    public static ObservableList<Tarefa> lerTarefasDoArquivo(String usuario) {
        List<Tarefa> tarefas = new ArrayList<>();
        java.io.File file = new java.io.File(getFilePath(usuario));

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo para o usuário " + usuario + ": " + e.getMessage());
                return FXCollections.observableArrayList(tarefas);
            }
        }

        try (BufferedReader lerTarefa = new BufferedReader(new FileReader(getFilePath(usuario)))) {
            String linha;
            while ((linha = lerTarefa.readLine()) != null) {
                String[] partes = linha.split(" - ");
                if (partes.length == 2) {
                    tarefas.add(new Tarefa(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de tarefas para o usuário " + usuario + ": " + e.getMessage());
        }
        return FXCollections.observableArrayList(tarefas);
    }

    /**
     * Salva as tarefas de um usuário em um arquivo de texto.
     *
     * @param tarefas a lista de tarefas a ser salva.
     * @param usuario o nome do usuário cujas tarefas serão salvas.
     */
    public static void salvarTarefasNoArquivo(ObservableList<Tarefa> tarefas, String usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(usuario)))) {
            for (Tarefa tarefa : tarefas) {
                writer.write(tarefa.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar as tarefas do usuário " + usuario + " no arquivo: " + e.getMessage());
        }
    }
}
