package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControlerLivro controlerLivro = new ControlerLivro(); // Controlador para gerenciar livros
        ControlerJornalRevista controlerJornalRevista = new ControlerJornalRevista(); // Controlador para gerenciar jornais/revistas
        ControlerUtente controlerUtente = new ControlerUtente(); // Controlador para gerenciar utentes
        ControlerEmprestimo controlerEmprestimo = new ControlerEmprestimo(); // Controlador para gerenciar empréstimos
        ControlerReservas controlerReservas = new ControlerReservas(); // Controlador para gerenciar reservas

        int opcaoPrincipal;

        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Gerir Livros");
            System.out.println("2. Gerir Jornais e Revistas");
            System.out.println("3. Gerir Utentes");
            System.out.println("4. Gerir Empréstimos");
            System.out.println("5. Gerir Reservas");
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
                    controlerEmprestimo.gerenciarEmprestimos(scanner); // Chama o método para gerenciar empréstimos
                    break;
                case 5:
                    controlerReservas.gerenciarReservas(scanner); // Chama o método para gerenciar reservas
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