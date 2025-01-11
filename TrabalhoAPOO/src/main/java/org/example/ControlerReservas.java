package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class ControlerReservas {
    private Reserva[] reservas; // Array fixo para armazenar reservas
    private int contador; // Contador para rastrear o número de reservas

    public ControlerReservas() {
        this.reservas = new Reserva[100]; // Inicializa o array com capacidade para 100 reservas
        this.contador = 0; // Inicializa o contador
    }

    // Método para adicionar uma nova reserva ao array
    public void adicionarReserva(Reserva reserva) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            reservas[contador] = reserva; // Adiciona a reserva na posição atual do contador
            contador++; // Incrementa o contador
            System.out.println("Reserva adicionada com sucesso!");
        } else {
            System.out.println("Limite de reservas atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    // Método para listar todas as reservas cadastradas
    public void listarReservas() {
        if (contador == 0) {
            System.out.println("Nenhuma reserva cadastrada."); // Mensagem se não houver reservas
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + reservas[i]); // Exibe cada reserva com seu índice
        }
    }

    // Método para gerenciar as operações relacionadas a reservas
    public void gerenciarReservas(Scanner scanner) {
        int opcao;

        do {
            System.out.println("Menu de Reservas:");
            System.out.println("1. Adicionar Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Número da Reserva: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Data de Registro (YYYY-MM-DD): ");
                    LocalDate dataRegistro = LocalDate.parse(scanner.nextLine());
                    System.out.print("Utente (Nome): ");
                    String nomeUtente = scanner.nextLine();
                    Utente utente = new Utente(nomeUtente); // Supondo que a classe Utente tenha um construtor que aceita o nome
                    System.out.print("Data de Início (YYYY-MM-DD): ");
                    LocalDate dataInicio = LocalDate.parse(scanner.nextLine());
                    System.out.print("Data de Fim (YYYY-MM-DD): ");
                    LocalDate dataFim = LocalDate.parse(scanner.nextLine());
                    System.out.print("Número de Livros: ");
                    int numLivros = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    Livro[] livros = new Livro[numLivros];

                    for (int i = 0; i < numLivros; i++) {
                        System.out.print("Título do Livro " + (i + 1) + ": ");
                        String tituloLivro = scanner.nextLine();
                        livros[i] = new Livro(tituloLivro, 2021, "Editora Exemplo", "1234567890", "Categoria Exemplo", "Autor Exemplo"); // Exemplo de criação de livro
                    }

                    Reserva reserva = new Reserva(numero, dataRegistro, utente, dataInicio, dataFim, livros);
                    adicionarReserva(reserva);
                    break;

                case 2:
                    listarReservas(); // Lista todas as reservas
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro
            }
        } while (opcao != 0);
    }
}
