package org.example;

import java.util.Scanner;

public class ControlerLivro {
    private Livro[] livros; // Array para armazenar os livros
    private int contador; // Contador para rastrear o número de livros

    public ControlerLivro() {
        this.livros = new Livro[100]; // Inicializa o array com capacidade para 100 livros
        this.contador = 0; // Inicializa o contador
    }

    // Método para adicionar um novo livro ao array
    public void adicionarLivro(Livro livro) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            livros[contador] = livro; // Adiciona o livro na posição atual do contador
            contador++; // Incrementa o contador
            System.out.println("Livro adicionado com sucesso!");
        } else {
            System.out.println("Limite de livros atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    // Método para listar todos os livros cadastrados
    public void listarLivros() {
        if (contador == 0) {
            System.out.println("Nenhum livro cadastrado."); // Mensagem se não houver livros
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + livros[i]); // Exibe cada livro com seu índice
        }
    }

    // Método para atualizar um livro existente
    public void atualizarLivro(int index, Livro livro) {
        if (index >= 0 && index < contador) { // Verifica se o índice é válido
            livros[index] = livro; // Atualiza o livro na posição especificada
            System.out.println("Livro atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido!"); // Mensagem de erro se o índice for inválido
        }
    }

    // Método para remover um livro do array
    public void removerLivro(int index) {
        if (index >= 0 && index < contador) { // Verifica se o índice é válido
            for (int i = index; i < contador - 1; i++) {
                livros[i] = livros[i + 1]; // Move os livros para preencher o espaço do livro removido
            }
            livros[contador - 1] = null; // Limpa a última posição
            contador--; // Decrementa o contador
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Índice inválido!"); // Mensagem de erro se o índice for inválido
        }
    }

    // Método para gerenciar as operações relacionadas a livros
    public void gerenciarLivros(Scanner scanner) {
        int opcao;

        do {
            System.out.println("Menu de Livros:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Remover Livro");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    // Coleta informações do usuário para adicionar um novo livro
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ano da Edição: ");
                    int anoEdicao = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Editora: ");
                    String editora = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Autores: ");
                    String autores = scanner.nextLine();

                    try {
                        // Tenta criar um novo livro e adicioná-lo
                        Livro livro = new Livro(titulo, anoEdicao, editora, isbn, categoria, autores);
                        adicionarLivro(livro);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); // Exibe mensagem de erro se o ISBN for inválido
                    }
                    break;

                case 2:
                    listarLivros(); // Lista todos os livros
                    break;

                case 3:
                    // Coleta informações do usuário para atualizar um livro existente
                    System.out.print("Índice do livro a atualizar: ");
                    int indexAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Novo Título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Novo Ano da Edição: ");
                    int novoAnoEdicao = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Nova Editora: ");
                    String novaEditora = scanner.nextLine();
                    System.out.print("Novo ISBN: ");
                    String novoIsbn = scanner.nextLine();
                    System.out.print("Nova Categoria: ");
                    String novaCategoria = scanner.nextLine();
                    System.out.print("Novos Autores: ");
                    String novosAutores = scanner.nextLine();

                    try {
                        // Tenta criar um novo livro com as informações atualizadas
                        Livro livroAtualizado = new Livro(novoTitulo, novoAnoEdicao, novaEditora, novoIsbn, novaCategoria, novosAutores);
                        atualizarLivro(indexAtualizar, livroAtualizado);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); // Exibe mensagem de erro se o ISBN for inválido
                    }
                    break;

                case 4:
                    // Coleta o índice do livro a ser removido
                    System.out.print("Índice do livro a remover: ");
                    int indexRemover = scanner.nextInt();
                    removerLivro(indexRemover); // Remove o livro
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