import java.time.LocalDate;
import java.util.Scanner;

public class ControlerEmprestimo {
    private Emprestimo[] emprestimos; // Array fixo para armazenar empréstimos
    private int contador; // Contador para rastrear o número de empréstimos
    private ControlerLivro controlerLivro; // Controlador de livros

    public ControlerEmprestimo(ControlerLivro controlerLivro) {
        this.emprestimos = new Emprestimo[100]; // Inicializa o array com capacidade para 100 empréstimos
        this.contador = 0; // Inicializa o contador
        this.controlerLivro = controlerLivro; // Recebe o controlador de livros
    }

    // Método para adicionar um novo empréstimo ao array
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            if (!verificarSobreposicao(emprestimo)) {
                emprestimos[contador] = emprestimo; // Adiciona o empréstimo na posição atual do contador
                contador++; // Incrementa o contador
                System.out.println("Empréstimo adicionado com sucesso!");
            } else {
                System.out.println("Não é possível adicionar o empréstimo devido à sobreposição de datas.");
            }
        } else {
            System.out.println("Limite de empréstimos atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    // Método para verificar sobreposição de datas
    private boolean verificarSobreposicao(Emprestimo novoEmprestimo) {
        for (int i = 0; i < contador; i++) {
            Emprestimo emprestimoExistente = emprestimos[i];
            if (emprestimoExistente != null) {
                LocalDate inicioExistente = emprestimoExistente.getDataInicio();
                LocalDate fimExistente = emprestimoExistente.getDataPrevistaDevolucao();
                LocalDate inicioNovo = novoEmprestimo.getDataInicio();
                LocalDate fimNovo = novoEmprestimo.getDataPrevistaDevolucao();

                // Verifica se há sobreposição
                if ((inicioNovo.isBefore(fimExistente) && fimNovo.isAfter(inicioExistente))) {
                    return true; // Há sobreposição
                }
            }
        }
        return false; // Não há sobreposição
    }

    // Método para gerenciar as operações relacionadas a empréstimos
    public void gerenciarEmprestimos(Scanner scanner) {
        int opcao;

        do {
            System.out.println("Menu de Empréstimos:");
            System.out.println("1. Adicionar Empréstimo");
            System.out.println("2. Listar Empréstimos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarNovoEmprestimo(scanner);
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
        } while (opcao != 0);
    }

    // Método para adicionar um novo empréstimo
    private void adicionarNovoEmprestimo(Scanner scanner) {
        System.out.print("Número do Empréstimo: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Data de Início (YYYY-MM-DD): ");
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine());
        System.out.print("Utente (Nome): ");
        String nomeUtente = scanner.nextLine();
        Utente utente = new Utente(nomeUtente); // Supondo que a classe Utente tenha um construtor que aceita o nome
        System.out.print("Data Prevista de Devolução (YYYY-MM-DD): ");
        LocalDate dataPrevistaDevolucao = LocalDate.parse(scanner.nextLine());

        System.out.print("Número de Livros: ");
        int numLivros = scanner.nextInt();
        Livro[] livros = new Livro[numLivros];

        boolean livroDisponivel = true; // Flag para verificar a disponibilidade do livro

        for (int i = 0; i < numLivros; i++) {
            System.out.print("Título do Livro " + (i + 1) + ": ");
            String tituloLivro = scanner.next();
            // Verifica se o livro está cadastrado
            Livro livro = buscarLivro(tituloLivro);
            if (livro != null) {
                livros[i] = livro; // Adiciona o livro ao array se estiver disponível
            } else {
                System.out.println("Livro não encontrado: " + tituloLivro);
                livroDisponivel = false; // Marca como não disponível
            }
        }

        if (livroDisponivel) {
            Emprestimo emprestimo = new Emprestimo(numero, dataInicio, utente, dataPrevistaDevolucao, livros);
            adicionarEmprestimo(emprestimo);
        } else {
            System.out.println("Não foi possível realizar o empréstimo devido à indisponibilidade de um ou mais livros.");
        }
    }

    // Método para buscar um livro pelo título
    private Livro buscarLivro(String titulo) {
        for (Livro livro : controlerLivro.getLivros()) { // Supondo que ControlerLivro tenha um método getLivros()
            if (livro != null && livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro; // Retorna o livro se encontrado
            }
        }
        return null; // Retorna null se o livro não for encontrado
    }

    // Método para listar todos os empréstimos cadastrados
    public void listarEmprestimos() {
        if (contador == 0) {
            System.out.println("Nenhum empréstimo cadastrado."); // Mensagem se não houver empréstimos
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + emprestimos[i]); // Exibe cada empréstimo com seu índice
        }
    }
}