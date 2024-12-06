import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrincipalHotel {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reserva reservas = new Reserva();
        Map<Integer, Quarto> quartosDisponiveis = new HashMap<>();
        List<Funcionario> funcionarios = new ArrayList<>();
        List<Hospede> hospedes = new ArrayList<>();
        Funcionario funcionarioLogado = null;

        while (true) {
            if (funcionarios.isEmpty()) {
                System.out.println("\nNenhum funcionário cadastrado no sistema.");
                System.out.println("Por favor, registre pelo menos um funcionário para utilizar o sistema.");
                System.out.print("Deseja registrar um funcionário agora? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                if (resposta.equals("s")) {
                    registrarFuncionario(scanner, funcionarios);
                } else {
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                }
                continue;
            }

            if (funcionarioLogado == null) {
                System.out.println("Nenhum funcionário está logado no sistema.");
                System.out.print("Deseja realizar login? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                if (resposta.equals("s")) {
                    funcionarioLogado = realizarLogin(scanner, funcionarios);
                } else {
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                }
                continue;
            }

            System.out.println("Menu do Sistema de Hotel");
            System.out.println("1. Registrar Hóspede");
            System.out.println("2. Adicionar Quarto");
            System.out.println("3. Fazer Reserva");
            System.out.println("4. Listar Hóspedes Registrados");
            System.out.println("5. Listar Quartos");
            System.out.println("6. Listar Reservas");
            System.out.println("7. Listar Reservas por Data");
            System.out.println("8. Salvar Reservas em Arquivo");
            System.out.println("9. Ler Reservas de Arquivo");
            System.out.println("10. Registrar Funcionário");
            System.out.println("11. Logout do Funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registrarHospede(scanner, hospedes);
                case 2 -> {
                    System.out.print("Número do quarto: ");
                    int numero = scanner.nextInt();
                    System.out.print("Capacidade do quarto: ");
                    int capacidade = scanner.nextInt();
                    scanner.nextLine();

                    if (quartosDisponiveis.containsKey(numero)) {
                        System.err.println("Erro: Quarto com número " + numero + " já está cadastrado!");
                    } else {
                        Quarto quarto = new Quarto(numero, capacidade);
                        quartosDisponiveis.put(numero, quarto);
                        System.out.println("Quarto adicionado com sucesso pelo funcionário " + funcionarioLogado.nome);
                    }
                }
                case 3 -> {
                    if (hospedes.isEmpty()) {
                        System.err.println("Erro: Nenhum hóspede registrado no sistema. Registre hóspedes antes de realizar reservas.");
                        break;
                    }
                    System.out.print("ID do hóspede: ");
                    String idHospede = scanner.nextLine();

                    Hospede hospede = hospedes.stream()
                            .filter(h -> h.getId().equals(idHospede))
                            .findFirst()
                            .orElse(null);

                    if (hospede == null) {
                        System.err.println("Erro: Hóspede não encontrado. Certifique-se de que o hóspede esteja registrado.");
                        break;
                    }

                    System.out.print("Número do quarto: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Quarto quarto = quartosDisponiveis.get(numero);
                        if (quarto == null) {
                            System.err.println("Erro: Quarto não encontrado!");
                        } else {
                            quarto.reservar(idHospede);
                            reservas.adicionarReserva(idHospede, quarto);
                            System.out.println("Reserva realizada com sucesso para o hóspede " + hospede.nome);
                        }
                    } catch (CapacidadeExcedidaException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 4 -> listarHospedes(hospedes);
                case 5 -> {
                    System.out.println("Lista de Quartos:");
                    if (quartosDisponiveis.isEmpty()) {
                        System.out.println("Nenhum quarto cadastrado.");
                    } else {
                        for (Quarto quarto : quartosDisponiveis.values()) {
                            System.out.println(quarto);
                        }
                    }
                }
                case 6 -> {
                    System.out.print("Digite o ID do hóspede para listar a reserva: ");
                    String idHospede = scanner.nextLine();
                    try {
                        String reserva = reservas.listarReserva(idHospede);
                        System.out.println(reserva);
                    } catch (ReservaNaoEncontradaException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case 7 -> {
                    System.out.print("Digite a data das reservas a listar (formato: DD/MM/AAAA): ");
                    String data = scanner.nextLine();
                    reservas.listarReservaPorData(data);
                }
                case 8 -> {
                    System.out.print("Nome do arquivo para salvar: ");
                    String nomeArquivo = scanner.nextLine();
                    try {
                        reservas.salvarEmArquivo(nomeArquivo);
                        System.out.println("Reservas salvas com sucesso no arquivo " + nomeArquivo);
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar reservas: " + e.getMessage());
                    }
                }
                case 9 -> {
                    System.out.print("Nome do arquivo para carregar: ");
                    String nomeArquivo = scanner.nextLine();
                    try {
                        reservas.lerDeArquivo(nomeArquivo);
                        System.out.println("Reservas carregadas com sucesso do arquivo " + nomeArquivo);
                    } catch (IOException e) {
                        System.err.println("Erro ao ler reservas: " + e.getMessage());
                    }
                }
                case 10 -> registrarFuncionario(scanner, funcionarios);
                case 11 -> {
                    System.out.println("Logout realizado com sucesso. Até logo, " + funcionarioLogado.nome);
                    funcionarioLogado = null;
                }
                case 0 -> {
                    System.out.println("Encerrando o sistema. Até logo!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void registrarFuncionario(Scanner scanner, List<Funcionario> funcionarios) {
        System.out.print("Nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do funcionário: ");
        String cpf = scanner.nextLine();
        System.out.print("Cargo do funcionário: ");
        String cargo = scanner.nextLine();
        Funcionario funcionario = new Funcionario(nome, cpf, cargo);
        funcionarios.add(funcionario);
        System.out.println("Funcionário registrado com sucesso: " + funcionario);
    }

    private static void registrarHospede(Scanner scanner, List<Hospede> hospedes) {
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do hóspede: ");
        String cpf = scanner.nextLine();
        System.out.print("ID do hóspede: ");
        String id = scanner.nextLine();
        Hospede hospede = new Hospede(nome, cpf, id);
        hospedes.add(hospede);
        System.out.println("Hóspede registrado com sucesso: " + hospede);
    }

    private static void listarHospedes(List<Hospede> hospedes) {
        System.out.println("Lista de Hóspedes Registrados:");
        if (hospedes.isEmpty()) {
            System.out.println("Nenhum hóspede registrado no sistema.");
        } else {
            for (Hospede hospede : hospedes) {
                System.out.println(hospede);
            }
        }
    }

    private static Funcionario realizarLogin(Scanner scanner, List<Funcionario> funcionarios) {
        System.out.print("Digite o CPF para login: ");
        String cpfLogin = scanner.nextLine();
        Funcionario funcionario = funcionarios.stream()
                .filter(f -> f.cpf.equals(cpfLogin))
                .findFirst()
                .orElse(null);

        if (funcionario == null) {
            System.err.println("Erro: Funcionário não encontrado!");
        } else {
            System.out.println("Login realizado com sucesso. Bem-vindo, " + funcionario.nome);
        }
        return funcionario;
    }
}
