import java.time.LocalDate;
import java.util.Scanner;

public class ControlerJornalRevista {
    private JornalRevista[] jornaisRevistas; // Array para armazenar jornais e revistas
    private int contador; // Contador para rastrear o número de jornais/revistas

    public ControlerJornalRevista() {
        this.jornaisRevistas = new JornalRevista[100]; // Inicializa o array com capacidade para 100 jornais/revistas
        this.contador = 0; // Inicializa o contador
    }

    // Método para adicionar um novo jornal/revista ao array
    public void adicionarJornalRevista(JornalRevista jornalRevista) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            jornaisRevistas[contador] = jornalRevista; // Adiciona o jornal/revista na posição atual do contador
            contador++; // Incrementa o contador
            System.out.println("Jornal/Revista adicionado com sucesso!");
        } else {
            System.out.println("Limite de jornais/revistas atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    // Método para listar todos os jornais e revistas cadastrados
    public void listarJornaisRevistas() {
        if (contador == 0) {
            System.out.println("Nenhum jornal/revista cadastrado."); // Mensagem se não houver jornais/revistas
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + jornaisRevistas[i]); // Exibe cada jornal/revista com seu índice
        }
    }

    // Método para atualizar um jornal/revista existente
    public void atualizarJornalRevista(int index, JornalRevista jornalRevista) {
        if (index >= 0 && index < contador) { // Verifica se o índice é válido
            jornaisRevistas[index] = jornalRevista; // Atualiza o jornal/revista na posição especificada
            System.out.println("Jornal/Revista atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido!"); // Mensagem de erro se o índice for inválido
        }
    }

    // Método para remover um jornal/revista do array
    public void removerJornalRevista(int index) {
        if (index >= 0 && index < contador) { // Verifica se o índice é válido
            for (int i = index; i < contador - 1; i++) {
                jornaisRevistas[i] = jornaisRevistas[i + 1]; // Move os jornais/revistas para preencher o espaço do removido
            }
            jornaisRevistas[contador - 1] = null; // Limpa a última posição
            contador--; // Decrementa o contador
            System.out.println("Jornal/Revista removido com sucesso!");
        } else {
            System.out.println("Índice inválido!"); // Mensagem de erro se o índice for inválido
        }
    }

    // Método para gerenciar as operações relacionadas a jornais e revistas
    public void gerenciarJornaisRevistas(Scanner scanner) {
        int opcao;

        do {
            System.out.println("Menu de Jornais/Revistas:");
            System.out.println("1. Adicionar Jornal/Revista");
            System.out.println("2. Listar Jornais/Revistas");
            System.out.println("3. Atualizar Jornal/Revista");
            System.out.println("4. Remover Jornal/Revista");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    // Coleta informações do usuário para adicionar um novo jornal/revista
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("ISSN (formato XXXX-XXXX): ");
                    String issn = scanner.nextLine();
                    System.out.print("Editora: ");
                    String editora = scanner.nextLine();
                    System.out.print("Data de Publicação (YYYY-MM-DD): ");
                    LocalDate dataPublicacao = LocalDate.parse(scanner.nextLine());
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();

                    try {
                        // Tenta criar um novo jornal/revista e adicioná-lo
                        JornalRevista jornalRevista = new JornalRevista(titulo, issn, editora, dataPublicacao, categoria);
                        adicionarJornalRevista(jornalRevista);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); // Exibe mensagem de erro se os dados forem inválidos
                    }
                    break;

                case 2:
                    listarJornaisRevistas(); // Lista todos os jornais e revistas
                    break;

                case 3:
                    // Coleta informações do usuário para atualizar um jornal/revista existente
                    System.out.print("Índice do jornal/revista a atualizar: ");
                    int indexAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Novo Título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Novo ISSN (formato XXXX-XXXX): ");
                    String novoIssn = scanner.nextLine();
                    System.out.print("Nova Editora: ");
                    String novaEditora = scanner.nextLine();
                    System.out.print("Nova Data de Publicação (YYYY-MM-DD): ");
                    LocalDate novaDataPublicacao = LocalDate.parse(scanner.nextLine());
                    System.out.print("Nova Categoria: ");
                    String novaCategoria = scanner.nextLine();

                    try {
                        // Tenta criar um novo jornal/revista com as informações atualizadas
                        JornalRevista jornalRevistaAtualizado = new JornalRevista(novoTitulo, novoIssn, novaEditora, novaDataPublicacao, novaCategoria);
                        atualizarJornalRevista(indexAtualizar, jornalRevistaAtualizado);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); // Exibe mensagem de erro se os dados forem inválidos
                    }
                    break;

                case 4:
                    // Coleta o índice do jornal/revista a ser removido
                    System.out.print("Índice do jornal/revista a remover: ");
                    int indexRemover = scanner.nextInt();
                    removerJornalRevista(indexRemover); // Remove o jornal/revista
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