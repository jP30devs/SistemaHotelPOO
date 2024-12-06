import java.io.IOException;

public interface Arquivo {

    void salvarEmArquivo(String nomeArquivo) throws IOException;
    void lerDeArquivo(String nomeArquivo) throws IOException;
}
