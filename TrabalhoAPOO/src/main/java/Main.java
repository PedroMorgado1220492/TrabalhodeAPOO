import org.controller.ControlerJornalRevista;
import org.classes.Pesquisa;
import org.controller.ControlerEmprestimo;
import org.controller.ControlerUtente;
import org.controller.ControlerReservas;
import org.controller.ControlerLivro;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        ControlerLivro controlerLivro = new ControlerLivro();
        ControlerJornalRevista controlerJornalRevista = new ControlerJornalRevista();
        ControlerUtente controlerUtente = new ControlerUtente();
        ControlerEmprestimo controlerEmprestimo = new ControlerEmprestimo(controlerLivro, controlerUtente);
        ControlerReservas controlerReservas = new ControlerReservas(controlerLivro, controlerUtente);
        Pesquisa pesquisa = new Pesquisa(controlerLivro, controlerJornalRevista); // Instância da classe Pesquisa

        int opcaoPrincipal;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Gerir Livros");
            System.out.println("2. Gerir Jornais e Revistas");
            System.out.println("3. Gerir Utentes");
            System.out.println("4. Gerir Empréstimos");
            System.out.println("5. Gerir Reservas");
            System.out.println("6. Pesquisa por ISBN/ISSN"); // Nova opção de pesquisa
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = ler.nextInt();
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
    }
}