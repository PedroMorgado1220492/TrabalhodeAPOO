import java.time.LocalDate;
import java.util.Scanner;

/**
 * 
 * @author Gustavo/Pedro
 * 
 * 
 * ControlerReservas é uma classe que gerencia um conjunto de reservas de livros.
 * Ela permite adicionar, listar e gerenciar reservas, além de verificar a disponibilidade de livros e utentes.
 */
public class ControlerReservas {
    private Reserva[] reservas; // Array fixo para armazenar reservas
    private int contador; // Contador para rastrear o número de reservas
    private ControlerLivro controlerLivro; // Controlador de livros
    private ControlerUtente controlerUtente; // Controlador de utentes
    private Scanner ler; // Scanner para entrada do usuário

    /**
     * Construtor da classe ControlerReservas.
     * Inicializa o array de reservas, o contador e os controladores de livros e utentes.
     * Também inicializa o Scanner e adiciona 3 reservas padrão.
     *
     * @param controlerLivro Controlador de livros
     * @param controlerUtente Controlador de utentes
     */
    public ControlerReservas(ControlerLivro controlerLivro, ControlerUtente controlerUtente) {
        this.reservas = new Reserva[100]; // Inicializa o array com capacidade para 100 reservas
        this.contador = 0; // Inicializa o contador
        this.controlerLivro = controlerLivro; // Recebe o controlador de livros
        this.controlerUtente = controlerUtente; // Recebe o controlador de utentes
        this.ler = new Scanner(System.in); // Inicializa o Scanner
        inicializarReservas(); // Adiciona 3 reservas por defeito
    }

    /**
     * Método para inicializar o array com 3 reservas por defeito.
     * Este método verifica se existem utentes e livros disponíveis antes de adicionar as reservas.
     */
    private void inicializarReservas() {
        // Verifica se existem utentes e livros disponíveis
        Utente utente1 = controlerUtente.getUtentes()[0]; // Supondo que já existam utentes
        Utente utente2 = controlerUtente.getUtentes()[1];
        Livro livro1 = controlerLivro.getLivros()[0]; // Supondo que já existam livros
        Livro livro2 = controlerLivro.getLivros()[1];

        // Adiciona 3 reservas por defeito
        adicionarReserva(new Reserva(contador + 1, LocalDate.now(), utente1, LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 17), new Livro[]{livro1}));
        adicionarReserva(new Reserva(contador + 1, LocalDate.now(), utente2, LocalDate.of(2023, 2, 15), LocalDate.of(2023, 2, 22), new Livro[]{livro2}));
        adicionarReserva(new Reserva(contador + 1, LocalDate.now(), utente1, LocalDate.of(2023, 3, 5), LocalDate.of(2023, 3, 12), new Livro[]{livro1, livro2}));
    }

    /**
     * Método para adicionar uma nova reserva ao array.
     *
     * @param reserva A reserva a ser adicionada
     */
    public void adicionarReserva(Reserva reserva) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            reservas[contador] = reserva; // Adiciona a reserva na posição atual do contador
            contador++; // Incrementa o contador
            System.out.println("Reserva adicionada com sucesso!");
        } else {
            System.out.println("Limite de reservas atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    /**
     * Método para gerenciar as operações relacionadas a reservas.
     * Este método exibe um menu e permite ao usuário escolher entre adicionar ou listar reservas.
     */
    public void gerenciarReservas() {
        int opcao;

        do {
            System.out.println("Menu de Reservas:");
            System.out.println("1. Adicionar Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = ler.nextInt();
            ler.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarNovaReserva();
                    break;

                case 2:
                    listarReservas(); // Lista todas as reservas
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
            }
        } while (opcao != 0);
    }

    /**
     * Método para adicionar uma nova reserva.
     * Este método coleta informações do utente e do livro, além de validar as datas da reserva.
     */
    private void adicionarNovaReserva() {
        System.out.print("Utente (Nome): ");
        String nomeUtente = ler.nextLine();

        // Verifica se o utente existe
        Utente utente = buscarUtente(nomeUtente);
        if (utente == null) {
            System.out.println("Utente não encontrado: " + nomeUtente);
            return;
        }

        System.out.print("Título do Livro: ");
        String tituloLivro = ler.nextLine();

        // Verifica se o livro está cadastrado
        Livro livro = buscarLivro(tituloLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado: " + tituloLivro);
            return;
        }

        System.out.print("Data de Início da Reserva (YYYY-MM-DD): ");
        LocalDate dataInicio = LocalDate.parse(ler.nextLine());

        System.out.print("Data de Fim da Reserva (YYYY-MM-DD): ");
        LocalDate dataFim = LocalDate.parse(ler.nextLine());

        // Verifica se a data de início é antes da data de fim
        if (!verificarDatasReserva(dataInicio, dataFim)) {
            System.out.println("A data de início deve ser anterior à data de fim.");
            return;
        }

        // Verifica se a data de início é uma data futura
        if (!verificarDataFutura(dataInicio)) {
            System.out.println("A data de início deve ser uma data futura.");
            return;
        }

        // Cria a nova reserva
        Reserva reserva = new Reserva(contador + 1, LocalDate.now(), utente, dataInicio, dataFim, new Livro[]{livro});
        adicionarReserva(reserva);
    }

    /**
     * Método para verificar se a data de início é antes da data de fim.
     *
     * @param dataInicio Data de início da reserva
     * @param dataFim Data de fim da reserva
     * @return true se a data de início for antes da data de fim, false caso contrário
     */
    private boolean verificarDatasReserva(LocalDate dataInicio, LocalDate dataFim) {
        return dataInicio.isBefore(dataFim); // Retorna true se a data de início for antes da data de fim
    }

    /**
     * Método para verificar se a data de início é uma data futura.
     *
     * @param dataInicio Data de início da reserva
     * @return true se a data de início for uma data futura, false caso contrário
     */
    private boolean verificarDataFutura(LocalDate dataInicio) {
        return dataInicio.isAfter(LocalDate.now()); // Retorna true se a data de início for uma data futura
    }

    /**
     * Método para buscar um utente pelo nome.
     *
     * @param nomeUtente Nome do utente a ser buscado
     * @return O utente encontrado ou null se não encontrado
     */
    private Utente buscarUtente(String nomeUtente) {
        for (Utente utente : controlerUtente.getUtentes()) { // Supondo que ControlerUtente tenha um método getUtentes()
            if (utente != null && utente.getNome().equalsIgnoreCase(nomeUtente)) {
                return utente; // Retorna o utente se encontrado
            }
        }
        return null; // Retorna null se o utente não for encontrado
    }

    /**
     * Método para buscar um livro pelo título.
     *
     * @param titulo Título do livro a ser buscado
     * @return O livro encontrado ou null se não encontrado
     */
    private Livro buscarLivro(String titulo) {
        for (Livro livro : controlerLivro.getLivros()) { // Supondo que ControlerLivro tenha um método getLivros()
            if (livro != null && livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro; // Retorna o livro se encontrado
            }
        }
        return null; // Retorna null se o livro não for encontrado
    }

    /**
     * Método para listar todas as reservas cadastradas.
     * Este método exibe a lista de todas as reservas no sistema.
     */
    public void listarReservas() {
        if (contador == 0) {
            System.out.println("Nenhuma reserva cadastrada."); // Mensagem se não houver reservas
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + reservas[i]); // Exibe cada reserva com seu índice
        }
    }

    /**
     * Método para fechar o Scanner ao final do uso.
     * Este método garante que o Scanner seja fechado corretamente.
     */
    public void fecharScanner() {
        if (ler != null) {
            ler.close(); // Fecha o scanner ao final
        }
    }
    /**
     * Método para obter todas as reservas.
     *
     * @return Um array de Reserva contendo todas as reservas cadastradas.
     */
    public Reserva[] getReservas() {
        return reservas; // Retorna o array de reservas
    }
}