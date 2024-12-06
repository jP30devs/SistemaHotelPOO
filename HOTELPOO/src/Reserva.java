import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Reserva extends OperacaoReserva implements Arquivo {

    private Map<String, Quarto> reservas;

    public Reserva() {
        reservas = new HashMap<>();
    }

    public void adicionarReserva(String idHospede, Quarto quarto) {
        reservas.put(idHospede, quarto);
    }

    public String listarReserva(String idHospede) throws ReservaNaoEncontradaException {
        Quarto quarto = reservas.get(idHospede);
        if (quarto == null) {
            throw new ReservaNaoEncontradaException("Nenhuma reserva encontrada para o ID: " + idHospede);
        }
        return "Reserva para o ID " + idHospede + ": " + quarto;
    }

    @Override
    public void executar() {
        System.out.println("Processando reservas...");
    }

    @Override
    public void salvarEmArquivo(String nomeArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (var entrada : reservas.entrySet()) {
                writer.write("Hospede ID: " + entrada.getKey() + ", Quarto: " + entrada.getValue() + "\n");
            }
        }
    }

    @Override
    public void lerDeArquivo(String nomeArquivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Reservas:\n");
        for (var entrada : reservas.entrySet()) {
            sb.append("Hospede ID: ").append(entrada.getKey())
                    .append(", Quarto: ").append(entrada.getValue()).append("\n");
        }
        return sb.toString();
    }
    public void adicionarReserva(String idHospede, Quarto quarto, String data) {
        reservas.put(idHospede, quarto);
        System.out.println("Reserva para o hóspede " + idHospede + " no quarto " + quarto.getNumero() + " na data " + data);
    }

    public void listarReservaPorData(String data) {
    }
}
