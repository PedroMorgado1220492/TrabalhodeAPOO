import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
        * Controlador responsável pela gestão de empréstimos de livros.
        * Esta classe permite adicionar, listar e gerenciar empréstimos,
        * além de verificar a disponibilidade de livros e reservas.
 */

public class ControlerEmprestimo {
    private Emprestimo[] emprestimos; // Array fixo para armazenar empréstimos
    private int contador; // Contador para rastrear o número de empréstimos
    private ControlerLivro controlerLivro; // Controlador de livros
    private ControlerUtente controlerUtente; // Controlador de utentes
    private ControlerReservas controlerReservas; // Controlador de reservas
    private Scanner ler; // Scanner para entrada do usuário

    /**
     * Construtor da classe ControlerEmprestimo.
     * Inicializa o array de empréstimos, o contador e os controladores de livros, utentes e reservas.
     * Também inicializa o Scanner e adiciona 3 empréstimos padrão.
     *
     * @param controlerLivro Controlador de livros
     * @param controlerUtente Controlador de utentes
     * @param controlerReservas Controlador de reservas
     */

    public ControlerEmprestimo(ControlerLivro controlerLivro, ControlerUtente controlerUtente, ControlerReservas controlerReservas) {
        this.emprestimos = new Emprestimo[100]; // Inicializa o array com capacidade para 100 empréstimos
        this.contador = 0; // Inicializa o contador
        this.controlerLivro = controlerLivro; // Recebe o controlador de livros
        this.controlerUtente = controlerUtente; // Recebe o controlador de utentes
        this.controlerReservas = controlerReservas; // Recebe o controlador de reservas
        this.ler = new Scanner(System.in); // Inicializa o Scanner
        inicializarEmprestimos(); // Adiciona 3 empréstimos padrão
    }

    /**
     * Método para inicializar o array com 3 empréstimos padrão.
     * Este método verifica se existem utentes e livros disponíveis antes de adicionar os empréstimos.
     */
    private void inicializarEmprestimos() {
        // Verifica se existem utentes e livros disponíveis
        Utente utente1 = controlerUtente.getUtentes()[0]; // Supondo que já existam utentes
        Utente utente2 = controlerUtente.getUtentes()[1];
        Livro livro1 = controlerLivro.getLivros()[0]; // Supondo que já existam livros
        Livro livro2 = controlerLivro.getLivros()[1];
        Livro livro3 = controlerLivro.getLivros()[2];
        Livro livro4 = controlerLivro.getLivros()[3];

        // Adiciona 3 empréstimos padrão
        adicionarEmprestimo(new Emprestimo(contador + 1, LocalDate.of(2025, 2, 10), utente1, LocalDate.of(2025, 2, 20), new Livro[]{livro1}));
        adicionarEmprestimo(new Emprestimo(contador + 1, LocalDate.of(2025, 2, 15), utente2, LocalDate.of(2025, 2, 25), new Livro[]{livro2}));
        adicionarEmprestimo(new Emprestimo(contador + 1, LocalDate.of(2025, 2, 5), utente1, LocalDate.of(2025, 2, 15), new Livro[]{livro3, livro4}));
    }

    /**
     * Método para adicionar um novo empréstimo ao array.
     *
     * @param emprestimo O empréstimo a ser adicionado
     */
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            emprestimos[contador] = emprestimo; // Adiciona o empréstimo na posição atual do contador
            contador++; // Incrementa o contador
            System.out.println("Empréstimo adicionado com sucesso!");
        } else {
            System.out.println("Limite de empréstimos atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    /**
     * Método para gerenciar as operações relacionadas a empréstimos.
     * Este método exibe um menu e permite ao usuário escolher entre adicionar ou listar empréstimos.
     */
    public void gerenciarEmprestimos() {
        int opcao;

        do {
            System.out.println("Menu de Empréstimos:");
            System.out.println("1. Adicionar Empréstimo");
            System.out.println("2. Listar Empréstimos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = ler.nextInt();
                ler.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        adicionarNovoEmprestimo();
                        break;

                    case 2:
                        listarEmprestimos(); // Lista todos os empréstimos
                        break;

                    case 0:
                        System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                ler.nextLine(); // Limpa o buffer do scanner
                opcao = -1; // Define uma opção inválida para continuar o loop
            }
        } while (opcao != 0);
    }

    /**
     * Método para adicionar um novo empréstimo.
     * Este método coleta informações do usuário e verifica a disponibilidade dos livros antes de adicionar o empréstimo.
     */
    private void adicionarNovoEmprestimo() {
        System.out.print("Data de Início (YYYY-MM-DD): ");
        LocalDate dataInicio = LocalDate.parse(ler.nextLine());
        System.out.print("Utente (Nome): ");
        String nomeUtente = ler.nextLine();

        // Verifica se o utente existe
        Utente utente = buscarUtente(nomeUtente);
        if (utente == null) {
            System.out.println("Utente não encontrado: " + nomeUtente);
            return;
        }

        System.out.print("Data Prevista de Devolução (YYYY-MM-DD): ");
        LocalDate dataPrevistaDevolucao = LocalDate.parse(ler.nextLine());

        // Verifica se a data de início é antes da data de devolução
        if (!verificarDatasEmprestimo(dataInicio, dataPrevistaDevolucao)) {
            System.out.println("A data de início deve ser anterior à data de devolução.");
            return;
        }

        System.out.print("Número de Livros: ");
        int numLivros = ler.nextInt();
        ler.nextLine(); // Limpa o buffer após nextInt()
        Livro[] livros = new Livro[numLivros];

        boolean livroDisponivel = true; // Flag para verificar a disponibilidade do livro

        for (int i = 0; i < numLivros; i++) {
            System.out.print("Título do Livro " + (i + 1) + ": ");
            String tituloLivro = ler.nextLine(); // Use nextLine() para capturar o título completo
            // Verifica se o livro está cadastrado
            Livro livro = buscarLivro(tituloLivro);
            if (livro != null) {
                // Verifica se o livro está reservado nas datas especificadas
                if (!verificarReservaLivro(livro.getIsbn(), dataInicio, dataPrevistaDevolucao)) {
                    System.out.println("O livro " + tituloLivro + " está reservado nas datas especificadas.");
                    livroDisponivel = false; // Marca como não disponível
                } else {
                    livros[i] = livro; // Adiciona o livro ao array se estiver disponível
                }
            } else {
                System.out.println("Livro não encontrado: " + tituloLivro);
                livroDisponivel = false; // Marca como não disponível
            }
        }

        if (livroDisponivel) {
            Emprestimo emprestimo = new Emprestimo(contador + 1, dataInicio, utente, dataPrevistaDevolucao, livros);
            adicionarEmprestimo(emprestimo);
        } else {
            System.out.println("Não foi possível realizar o empréstimo devido à indisponibilidade de um ou mais livros.");
        }
    }

    /**
     * Método para verificar se um livro está reservado nas datas especificadas.
     *
     * @param isbn O ISBN do livro a ser verificado
     * @param dataInicio A data de início do empréstimo
     * @param dataPrevistaDevolucao A data prevista de devolução do empréstimo
     * @return true se o livro estiver disponível, false caso contrário
     */
    private boolean verificarReservaLivro(String isbn, LocalDate dataInicio, LocalDate dataPrevistaDevolucao) {
        for (Reserva reserva : controlerReservas.getReservas()) {
            if (reserva != null && reserva.getLivroIsbn().equals(isbn)) {
                LocalDate inicioReserva = reserva.getDataInicio();
                LocalDate fimReserva = reserva.getDataFim();
                // Verifica se há sobreposição com a reserva
                if ((dataInicio.isBefore(fimReserva) && dataPrevistaDevolucao.isAfter(inicioReserva)) ||
                        dataInicio.isEqual(inicioReserva) || dataPrevistaDevolucao.isEqual(fimReserva)) {
                    return false; // Livro reservado
                }
            }
        }
        return true; // Livro disponível
    }

    /**
     * Método para verificar se a data de início é antes da data de devolução.
     *
     * @param dataInicio A data de início do empréstimo
     * @param dataPrevistaDevolucao A data prevista de devolução do empréstimo
     * @return true se a data de início for anterior à data de devolução, false caso contrário
     */
    private boolean verificarDatasEmprestimo(LocalDate dataInicio, LocalDate dataPrevistaDevolucao) {
        return dataInicio.isBefore(dataPrevistaDevolucao); // Retorna true se a data de início for antes da data de devolução
    }

    /**
     * Método para buscar um utente pelo nome.
     *
     * @param nomeUtente O nome do utente a ser buscado
     * @return O utente encontrado ou null se não for encontrado
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
     * @param titulo O título do livro a ser buscado
     * @return O livro encontrado ou null se não for encontrado
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
     * Método para listar todos os empréstimos.
     * Este método exibe a lista de todos os empréstimos registrados no sistema.
     */
    private void listarEmprestimos() {
        System.out.println("Lista de Empréstimos:");
        for (int i = 0; i < contador; i++) {
            if (emprestimos[i] != null) {
                System.out.println(emprestimos[i].toString()); // Supondo que a classe Emprestimo tenha um método toString() implementado
            }
        }
    }
}