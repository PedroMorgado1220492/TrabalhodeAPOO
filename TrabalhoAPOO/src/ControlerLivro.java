import java.util.Scanner;

/**
 * ControlerLivro é uma classe que gerencia um conjunto de livros.
 * Ela permite adicionar, listar, atualizar e deletar livros de um array fixo.
 */

public class ControlerLivro {
    private Livro[] livros;
    private int contador; // Para rastrear o número de livros adicionados
    private Scanner ler; // Scanner para entrada do usuário

    /**
     * Construtor da classe ControlerLivro.
     * Inicializa o array de livros, o contador e o Scanner.
     * Também chama o método para inicializar a lista de livros com exemplos.
     */
    public ControlerLivro() {
        this.livros = new Livro[100]; // Array fixo de 100 livros
        this.contador = 0; // Inicializa o contador
        this.ler = new Scanner(System.in); // Inicializa o Scanner
        inicializarLivros(); // Chama o método para inicializar a lista de livros
    }

    /**
     * Método para retornar a lista de livros.
     *
     * @return Um array de Livro contendo todos os livros cadastrados.
     */
    public Livro[] getLivros() {

        return livros;
    }

    /**
     * Método para inicializar a lista de livros com exemplos.
     * Este método adiciona 5 livros padrão ao array.
     */
    private void inicializarLivros() {
        adicionarLivro(new Livro("O Senhor dos Anéis", 2001, "HarperCollins", "0618640150", "Fantasia", "J.R.R. Tolkien"));
        adicionarLivro(new Livro("1984", 1949, "Secker & Warburg", "0451524934", "Ficção Científica", "George Orwell"));
        adicionarLivro(new Livro("Dom Casmurro", 1899, "Editora Nacional", "9788535920002", "Romance", "Machado de Assis"));
        adicionarLivro(new Livro("A Revolução dos Bichos", 1945, "Secker & Warburg", "9788535920003", "Fábula", "George Orwell"));
        adicionarLivro(new Livro("O Pequeno Príncipe", 1943, "Reynal & Hitchcock", "9788501063000", "Infantil", "Antoine de Saint-Exupéry"));
    }

    /**
     * Método para adicionar um livro ao array.
     *
     * @param livro O objeto Livro a ser adicionado ao array.
     */
    public void adicionarLivro(Livro livro) {
        if (contador < livros.length) {
            livros[contador] = livro;
            contador++;
            System.out.println("Livro adicionado com sucesso!");
        } else {
            System.out.println("Não é possível adicionar mais livros. O array está cheio.");
        }
    }

    /**
     * Método para gerenciar as operações relacionadas a livros.
     * Este método exibe um menu e permite ao usuário escolher entre adicionar, listar, atualizar ou deletar livros.
     */
    public void gerenciarLivros() {
        int opcaoLivros;

        do {
            System.out.println("Menu de Livros:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoLivros = ler.nextInt();
            ler.nextLine(); // Limpar o buffer

            switch (opcaoLivros) {
                case 1:
                    adicionarLivro(); // Chama o método para adicionar um livro
                    break;
                case 2:
                    listarLivros(); // Lista todos os livros
                    break;
                case 3:
                    atualizarLivro(); // Chama o método para atualizar um livro
                    break;
                case 4:
                    deletarLivro(); // Chama o método para deletar um livro
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
            }
        } while (opcaoLivros != 0);
    }
    /**
     * Método para adicionar um livro ao array.
     * Este método coleta informações do usuário e valida o ISBN antes de adicionar o objeto ao array.
     */
    private void adicionarLivro() {
        System.out.print("Título: ");
        String titulo = ler.nextLine();
        System.out.print("Ano da Edição: ");
        int anoEdicao = ler.nextInt();
        ler.nextLine(); // Limpar o buffer
        System.out.print("Editora: ");
        String editora = ler.nextLine();
        System.out.print("Categoria: ");
        String categoria = ler.nextLine();
        System.out.print("Autores: ");
        String autores = ler.nextLine();

        String isbn = "";
        boolean isbnValido = false;

        // Loop para validar o ISBN
        while (!isbnValido) {
            System.out.print("ISBN (ou digite 'voltar' para voltar ao menu): ");
            isbn = ler.nextLine();

            if (isbn.equalsIgnoreCase("voltar")) {
                System.out.println("Voltando ao menu anterior...");
                return; // Retorna ao menu anterior
            }

            try {
                Livro livro = new Livro(titulo, anoEdicao, editora, isbn, categoria, autores);
                adicionarLivro(livro); // Adiciona o livro ao array
                isbnValido = true; // ISBN válido, sai do loop
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + ". Tente novamente.");
            }
        }
    }
    /**
     * Método para listar todos os livros cadastrados.
     * Este método exibe a lista de todos os livros no sistema.
     */
    private void listarLivros() {
        if (contador == 0) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("Lista de Livros:");
        for (int i = 0; i < contador; i++) {
            System.out.println(livros[i]);
        }
    }
    /**
     * Método para atualizar um livro existente.
     * Este método coleta novas informações do usuário e atualiza o objeto correspondente no array.
     */
    private void atualizarLivro() {
        System.out.print("Digite o título do livro que deseja atualizar: ");
        String titulo = ler.nextLine();
        for (int i = 0; i < contador; i++) {
            if (livros[i].getTitulo().equalsIgnoreCase(titulo)) {
                System.out.print("Novo Ano da Edição: ");
                int anoEdicao = ler.nextInt();
                ler.nextLine(); // Limpar o buffer
                System.out.print("Nova Editora: ");
                String editora = ler.nextLine();
                System.out.print("Nova Categoria: ");
                String categoria = ler.nextLine();
                System.out.print("Novos Autores: ");
                String autores = ler.nextLine();
                System.out.print("Novo ISBN: ");
                String isbn = ler.nextLine();

                // Atualiza os dados do livro
                livros[i].setAnoEdicao(anoEdicao);
                livros[i].setEditora(editora);
                livros[i].setCategoria(categoria);
                livros[i].setAutores(autores);
                livros[i].setIsbn(isbn);
                System.out.println("Livro atualizado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }
    /**
     * Método para deletar um livro do array.
     * Este método remove o objeto correspondente e reorganiza o array.
     */
    private void deletarLivro() {
        System.out.print("Digite o título do livro que deseja deletar: ");
        String titulo = ler.nextLine();
        for (int i = 0; i < contador; i++) {
            if (livros[i].getTitulo().equalsIgnoreCase(titulo)) {
                // Move os livros para preencher o espaço
                for (int j = i; j < contador - 1; j++) {
                    livros[j] = livros[j + 1];
                }
                livros[contador - 1] = null; // Limpa a última posição
                contador--; // Decrementa o contador
                System.out.println("Livro deletado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    /**
     * Método para fechar o Scanner ao final do uso.
     * Este método garante que o Scanner seja fechado corretamente.
     */
    public void fecharScanner() {
        if (ler != null) {
            ler.close(); // Fecha o scanner ao final
        }
    }
}