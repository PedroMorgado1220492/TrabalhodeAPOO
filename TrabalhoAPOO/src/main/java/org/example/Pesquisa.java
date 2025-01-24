import java.util.Scanner;

public class Pesquisa {
    private ControlerLivro controlerLivro;
    private ControlerJornalRevista controlerJornalRevista;
    private Scanner ler; // Scanner para entrada do usuário

    public Pesquisa(ControlerLivro controlerLivro, ControlerJornalRevista controlerJornalRevista) {
        this.controlerLivro = controlerLivro;
        this.controlerJornalRevista = controlerJornalRevista;
        this.ler = new Scanner(System.in); // Inicializa o Scanner
    }

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

    private Livro buscarLivroPorISBN(String isbn) {
        for (Livro livro : controlerLivro.getLivros()) {
            if (livro != null && livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    private JornalRevista buscarJornalRevistaPorISSN(String issn) {
        for (JornalRevista jornalRevista : controlerJornalRevista.getJornaisRevistas()) {
            if (jornalRevista != null && jornalRevista.getIssn().equals(issn)) {
                return jornalRevista;
            }
        }
        return null;
    }
}