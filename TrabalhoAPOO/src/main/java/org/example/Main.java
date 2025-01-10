
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControlerLivro controlerLivro = new ControlerLivro(); // Controlador para gerenciar livros
        ControlerJornalRevista controlerJornalRevista = new ControlerJornalRevista(); // Controlador para gerenciar jornais/revistas
        ControlerUtente controlerUtente = new ControlerUtente(); // Controlador para gerenciar utentes
        // Aqui você pode instanciar ControlerEmprestimo e ControlerReserva quando implementados

        int opcaoPrincipal;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Gerir Livros");
            System.out.println("2. Gerir Jornais/Revistas");
            System.out.println("3. Gerir Utentes");
            System.out.println("4. Gerir Empréstimos (não implementado)");
            System.out.println("5. Gerir Reservas (não implementado)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcaoPrincipal) {
                case 1:
                    controlerLivro.gerenciarLivros(scanner); // Chama o método para gerenciar livros
                    break;
                case 2:
                    controlerJornalRevista.gerenciarJornaisRevistas(scanner); // Chama o método para gerenciar jornais/revistas
                    break;
                case 3:
                    controlerUtente.gerenciarUtentes(scanner); // Chama o método para gerenciar utentes
                    break;
                case 4:
                    System.out.println("Gerenciar Empréstimos (não implementado ainda).");
                    break;
                case 5:
                    System.out.println("Gerenciar Reservas (não implementado ainda).");
                    break;
                case 0:
                    System.out.println("Saindo..."); // Mensagem ao sair
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
            }
        } while (opcaoPrincipal != 0);

        scanner.close(); // Fecha o scanner ao final
    }
}