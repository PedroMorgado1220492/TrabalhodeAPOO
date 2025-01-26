import java.util.Scanner;

/**
 * Classe principal do sistema de gestão de biblioteca.
 * Esta classe contém o método main que inicia a aplicação e apresenta um menu para o usuário.
 * O usuário pode gerenciar livros, jornais, revistas, utentes, empréstimos e reservas.
 */

public class Main {
    /**
     * Construtor padrão da classe Main.
     * Este construtor não realiza nenhuma operação, mas é necessário para a criação de instâncias da classe.
     */
    public Main() {
        // Construtor padrão
    }

    /**
     * Método principal que inicia a aplicação.
     * Este método cria instâncias dos controladores e do sistema de pesquisa,
     * e exibe um menu para o usuário interagir com o sistema.
     *
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in); // Inicializa o Scanner para entrada do usuário
        ControlerLivro controlerLivro = new ControlerLivro(); // Cria uma instância do controlador de livros
        ControlerJornalRevista controlerJornalRevista = new ControlerJornalRevista(); // Cria uma instância do controlador de jornais e revistas
        ControlerUtente controlerUtente = new ControlerUtente(); // Cria uma instância do controlador de utentes
        ControlerReservas controlerReservas = new ControlerReservas(controlerLivro, controlerUtente); // Cria uma instância do controlador de reservas, passando os controladores de livros e utente
        ControlerEmprestimo controlerEmprestimo = new ControlerEmprestimo(controlerLivro, controlerUtente, controlerReservas); // Cria uma instância do controlador de empréstimos, passando os controladores de livros, utentes e reservas
        Pesquisa pesquisa = new Pesquisa(controlerLivro, controlerJornalRevista);  // Cria uma instância da classe Pesquisa, passando os controladores de livros e jornais/revistas

        int opcaoPrincipal; // Declara uma variável para armazenar a opção escolhida pelo usuário


        do {
            System.out.println("Menu Principal:"); // Exibe o menu principal
            System.out.println("1. Gerir Livros"); // Opção para gerenciar livros
            System.out.println("2. Gerir Jornais e Revistas"); // Opção para gerenciar jornais e revistas
            System.out.println("3. Gerir Utentes"); // Opção para gerenciar utentes
            System.out.println("4. Gerir Empréstimos"); // Opção para gerenciar empréstimos
            System.out.println("5. Gerir Reservas"); // Opção para gerenciar reservas
            System.out.println("6. Pesquisa por ISBN/ISSN"); // Opção para pesquisar por ISBN ou ISSN
            System.out.println("0. Sair"); // Opção para sair do sistema
            System.out.print("Escolha uma opção: "); // Solicita ao usuário que escolha uma opção
            opcaoPrincipal = ler.nextInt(); // Lê a opção escolhida pelo usuário
            ler.nextLine(); // Limpar o buffer

            switch (opcaoPrincipal) {
                case 1:
                    controlerLivro.gerenciarLivros();
                    break;
                case 2:
                    controlerJornalRevista.gerenciarJornaisRevistas();
                    break;
                case 3:
                    controlerUtente.gerenciarUtentes();
                    break;
                case 4:
                    controlerEmprestimo.gerenciarEmprestimos();
                    break;
                case 5:
                    controlerReservas.gerenciarReservas();
                    break;
                case 6:
                    pesquisa.menuPesquisa(); // Chama o método de menu de pesquisa
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoPrincipal != 0);

        ler.close(); // Fecha o scanner ao final
    }
}