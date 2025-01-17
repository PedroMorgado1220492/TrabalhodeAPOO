import java.time.LocalDate;
import java.util.Scanner;

public class ControlerJornalRevista {
    private JornalRevista[] jornaisRevistas; // Array fixo de 100 jornais/revistas
    private int contador; // Para rastrear o número de jornais/revistas adicionados

    public ControlerJornalRevista() {
        this.jornaisRevistas = new JornalRevista[100]; // Inicializa o array com tamanho 100
        this.contador = 0; // Inicializa o contador
    }

    public void gerenciarJornaisRevistas(Scanner scanner) {
        int opcao;

        do {
            System.out.println("Menu de Jornais e Revistas:");
            System.out.println("1. Adicionar Jornal/Revista");
            System.out.println("2. Listar Jornais/Revistas");
            System.out.println("3. Atualizar Jornal/Revista");
            System.out.println("4. Deletar Jornal/Revista");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarJornalRevista(scanner);
                    break;
                case 2:
                    listarJornaisRevistas();
                    break;
                case 3:
                    atualizarJornalRevista(scanner);
                    break;
                case 4:
                    deletarJornalRevista(scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarJornalRevista(Scanner scanner) {
        if (contador >= jornaisRevistas.length) {
            System.out.println("Não é possível adicionar mais jornais/revistas. O array está cheio.");
            return;
        }

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        String issn;
        while (true) {
            System.out.print("ISSN: ");
            issn = scanner.nextLine();

            if (validarISSN(issn)) {
                break; // ISSN válido, sai do loop
            } else {
                System.out.println("ISSN inválido. O formato correto é XXXX-XXXX, onde o último dígito pode ser de 0 a 9 ou 'X'. Tente novamente.");
            }
        }

        System.out.print("Data de Publicação (YYYY-MM-DD): ");
        LocalDate dataPublicacao = LocalDate.parse(scanner.nextLine());

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Editora: ");
        String editora = scanner.nextLine();

        // Adiciona o jornal/revista ao array
        jornaisRevistas[contador] = new JornalRevista(titulo, issn, dataPublicacao, categoria, editora);
        contador++; // Incrementa o contador
        System.out.println("Jornal/Revista adicionado com sucesso!");
    }

    private void listarJornaisRevistas() {
        if (contador == 0) {
            System.out.println("Nenhum jornal/revista cadastrado.");
            return;
        }

        System.out.println("Lista de Jornais e Revistas:");
        for (int i = 0; i < contador; i++) {
            System.out.println(jornaisRevistas[i]);
        }
    }

    private void atualizarJornalRevista(Scanner scanner) {
        System.out.print("Digite o índice do jornal/revista que deseja atualizar (0 a " + (contador - 1) + "): ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (indice < 0 || indice >= contador) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("Novo Título: ");
        String titulo = scanner.nextLine();

        String issn;
        while (true) {
            System.out.print("Novo ISSN: ");
            issn = scanner.nextLine();

            if (validarISSN(issn)) {
                break; // ISSN válido, sai do loop
            } else {
                System.out.println("ISSN inválido. O formato correto é XXXX-XXXX, onde o último dígito pode ser de 0 a 9 ou 'X'. Tente novamente.");
            }
        }

        System.out.print("Nova Data de Publicação (YYYY-MM-DD): ");
        LocalDate dataPublicacao = LocalDate.parse(scanner.nextLine());

        System.out.print("Nova Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Nova Editora: ");
        String editora = scanner.nextLine();

        // Atualiza o jornal/revista no array
        jornaisRevistas[indice].setTitulo(titulo);
        jornaisRevistas[indice].setIssn(issn);
        jornaisRevistas[indice].setDataPublicacao(dataPublicacao);
        jornaisRevistas[indice].setCategoria(categoria);
        jornaisRevistas[indice].setEditora(editora);

        System.out.println("Jornal/Revista atualizado com sucesso!");
    }

    private void deletarJornalRevista(Scanner scanner) {
        System.out.print("Digite o índice do jornal/revista que deseja deletar (0 a " + (contador - 1) + "): ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (indice < 0 || indice >= contador) {
            System.out.println("Índice inválido.");
            return;
        }

        // Move os elementos para preencher o espaço deixado pelo deletado
        for (int i = indice; i < contador - 1; i++) {
            jornaisRevistas[i] = jornaisRevistas[i + 1];
        }
        jornaisRevistas[contador - 1] = null; // Limpa a última posição
        contador--; // Decrementa o contador

        System.out.println("Jornal/Revista deletado com sucesso!");
    }

    private boolean validarISSN(String issn) {
        // Validação simples do formato ISSN (XXXX-XXXX)
        return issn.matches("\\d{4}-\\d{4}");
    }
}