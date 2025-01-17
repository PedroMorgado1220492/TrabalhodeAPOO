import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlerLivro {
    private List<Livro> livros;

    public ControlerLivro() {
        this.livros = new ArrayList<>();
    }

    // Método para retornar a lista de livros
    public List<Livro> getLivros() {
        return livros;
    }

    public void gerenciarLivros(Scanner scanner) {
        int opcaoLivros;

        do {
            System.out.println("Menu de Livros:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoLivros = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcaoLivros) {
                case 1:
                    adicionarLivro(scanner); // Chama o método para adicionar um livro
                    break;
                case 2:
                    listarLivros(); // Lista todos os livros
                    break;
                case 3:
                    atualizarLivro(scanner); // Chama o método para atualizar um livro
                    break;
                case 4:
                    deletarLivro(scanner); // Chama o método para deletar um livro
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
            }
        } while (opcaoLivros != 0);
    }

    private void adicionarLivro(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano da Edição: ");
        int anoEdicao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Autores: ");
        String autores = scanner.nextLine();

        String isbn = "";
        boolean isbnValido = false;

        // Loop para validar o ISBN
        while (!isbnValido) {
            System.out.print("ISBN (ou digite 'voltar' para voltar ao menu): ");
            isbn = scanner.nextLine();

            if (isbn.equalsIgnoreCase("voltar")) {
                System.out.println("Voltando ao menu anterior...");
                return; // Retorna ao menu anterior
            }

            try {
                Livro livro = new Livro(titulo, anoEdicao, editora, isbn, categoria, autores);
                livros.add(livro);
                isbnValido = true; // ISBN válido, sai do loop
                System.out.println("Livro adicionado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Tente novamente.");
            }
        }
    }

    private void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("Lista de Livros:");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private void atualizarLivro(Scanner scanner) {
        System.out.print("Digite o título do livro que deseja atualizar: ");
        String titulo = scanner.nextLine();
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.print("Novo Ano da Edição: ");
                int anoEdicao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                System.out.print("Nova Editora: ");
                String editora = scanner.nextLine();
                System.out.print("Nova Categoria: ");
                String categoria = scanner.nextLine();
                System.out.print("Novos Autores: ");
                String autores = scanner.nextLine();
                System.out.print("Novo ISBN: ");
                String isbn = scanner.nextLine();

                // Atualiza os dados do livro
                livro.setAnoEdicao(anoEdicao);
                livro.setEditora(editora);
                livro.setCategoria(categoria);
                livro.setAutores(autores);
                livro.setIsbn(isbn);
                System.out.println("Livro atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    private void deletarLivro(Scanner scanner) {
        System.out.print("Digite o título do livro que deseja deletar: ");
        String titulo = scanner.nextLine();
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(i);
                System.out.println("Livro deletado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }
}