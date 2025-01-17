package org.example;

import java.util.Scanner;

public class ControlerUtente {
    private Utente[] utentes; // Array para armazenar utentes
    private int contador; // Contador para rastrear o número de utentes

    public ControlerUtente() {
        this.utentes = new Utente[100]; // Inicializa o array com capacidade para 100 utentes
        this.contador = 0; // Inicializa o contador
    }

    // Método para adicionar um novo utente ao array
    public void adicionarUtente(Utente utente) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            utentes[contador] = utente; // Adiciona o utente na posição atual do contador
            contador++; // Incrementa o contador
            System.out.println("Utente adicionado com sucesso!");
        } else {
            System.out.println("Limite de utentes atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    // Método para listar todos os utentes cadastrados
    public void listarUtentes() {
        if (contador == 0) {
            System.out.println("Nenhum utente cadastrado."); // Mensagem se não houver utentes
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + utentes[i]); // Exibe cada utente com seu índice
        }
    }

    // Método para gerenciar as operações relacionadas a utentes
    public void gerenciarUtentes(Scanner scanner) {
        int opcao;

        do {
            System.out.println("Menu de Utentes:");
            System.out.println("1. Adicionar Utente");
            System.out.println("2. Listar Utentes");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    // Coleta informações do usuário para adicionar um novo utente
                    System.out.print("NIF: ");
                    String nif = scanner.nextLine();
                    System.out.print("Género: ");
                    String genero = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Contacto: ");
                    String contacto = scanner.nextLine();

                    // Cria um novo utente e adiciona ao controlador
                    Utente utente = new Utente(nif, genero, nome, contacto);
                    adicionarUtente(utente);
                    break;

                case 2:
                    listarUtentes(); // Lista todos os utentes
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
            }
        } while (opcao != 0);
    }
}
