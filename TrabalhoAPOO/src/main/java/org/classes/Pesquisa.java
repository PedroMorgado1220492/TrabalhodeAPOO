import java.util.Scanner;

/**
 * 
 * @author Gustavo/PedroP
 * 
 * A classe Pesquisa é responsável por gerenciar a pesquisa de livros e jornais/revistas
 * com base em seus identificadores (ISBN e ISSN).
 */
public class Pesquisa {
    private ControlerLivro controlerLivro;
    private ControlerJornalRevista controlerJornalRevista;
    private Scanner ler; // Scanner para entrada do usuário

    /**
     * Construtor da classe Pesquisa.
     * Inicializa os controladores de livros e jornais/revistas, e o Scanner.
     *
     * @param controlerLivro Controlador de livros
     * @param controlerJornalRevista Controlador de jornais e revistas
     */
    public Pesquisa(ControlerLivro controlerLivro, ControlerJornalRevista controlerJornalRevista) {
        this.controlerLivro = controlerLivro;
        this.controlerJornalRevista = controlerJornalRevista;
        this.ler = new Scanner(System.in); // Inicializa o Scanner
    }
    /**
     * Método que exibe o menu de pesquisa e permite ao usuário escolher entre
     * pesquisar por ISBN ou ISSN.
     */
    public void menuPesquisa() {
        int opcao;

        do {
            System.out.println("Menu de Pesquisa:");
            System.out.println("1. Pesquisar por ISBN");
            System.out.println("2. Pesquisar por ISSN");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = ler.nextInt();
            ler.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    pesquisarPorISBN(); // Chama o método para pesquisar por ISBN
                    break;
                case 2:
                    pesquisarPorISSN(); // Chama o método para pesquisar por ISSN
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }
    /**
     * Método para pesquisar um livro pelo ISBN.
     * Solicita ao usuário o ISBN e exibe o livro encontrado ou uma mensagem de erro.
     */
    public void pesquisarPorISBN() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = ler.nextLine();

        Livro livro = buscarLivroPorISBN(isbn);
        if (livro != null) {
            System.out.println("Livro encontrado: " + livro);
        } else {
            System.out.println("Livro não encontrado com o ISBN: " + isbn);
        }
    }
    /**
     * Método para pesquisar uma revista ou jornal pelo ISSN.
     * Solicita ao usuário o ISSN e exibe a revista/jornal encontrado ou uma mensagem de erro.
     */
    public void pesquisarPorISSN() {
        System.out.print("Digite o ISSN da revista/jornal: ");
        String issn = ler.nextLine();

        JornalRevista jornalRevista = buscarJornalRevistaPorISSN(issn);
        if (jornalRevista != null) {
            System.out.println("Jornal/Revista encontrado: " + jornalRevista);
        } else {
            System.out.println("Jornal/Revista não encontrado com o ISSN: " + issn);
        }
    }
    /**
     * Método para buscar um livro pelo ISBN.
     *
     * @param isbn O ISBN do livro a ser buscado
     * @return O livro encontrado ou null se não for encontrado
     */

    private Livro buscarLivroPorISBN(String isbn) {
        for (Livro livro : controlerLivro.getLivros()) {
            if (livro != null && livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }
    /**
     * Método para buscar uma revista ou jornal pelo ISSN.
     *
     * @param issn O ISSN da revista/jornal a ser buscado
     * @return A revista/jornal encontrado ou null se não for encontrado
     */
    private JornalRevista buscarJornalRevistaPorISSN(String issn) {
        for (JornalRevista jornalRevista : controlerJornalRevista.getJornaisRevistas()) {
            if (jornalRevista != null && jornalRevista.getIssn().equals(issn)) {
                return jornalRevista;
            }
        }
        return null;
    }
}