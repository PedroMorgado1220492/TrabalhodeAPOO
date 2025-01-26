import java.util.Scanner;

/**
 * ControlerUtente é uma classe que gerencia um conjunto de utentes.
 * Ela permite adicionar, listar, atualizar e deletar utentes de um array fixo.
 */
public class ControlerUtente {
    private Utente[] utentes; // Array para armazenar utentes
    private int contador; // Contador para rastrear o número de utentes
    private Scanner ler; // Scanner para entrada do usuário

    /**
     * Construtor da classe ControlerUtente.
     * Inicializa o array de utentes, o contador e o Scanner.
     * Também chama o método para adicionar 5 utentes de exemplo ao iniciar.
     */
    public ControlerUtente() {
        this.utentes = new Utente[100]; // Inicializa o array com capacidade para 100 utentes
        this.contador = 0; // Inicializa o contador
        this.ler = new Scanner(System.in); // Inicializa o Scanner
        adicionarUtentesExemplo(); // Adiciona 5 utentes de exemplo ao iniciar
    }

    /**
     * Método para adicionar um novo utente ao array.
     *
     * @param utente O utente a ser adicionado ao array.
     */
    public void adicionarUtente(Utente utente) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            utentes[contador] = utente; // Adiciona o utente na posição atual do contador
            contador++; // Incrementa o contador
            System.out.println("Utente adicionado com sucesso!");
        } else {
            System.out.println("Limite de utentes atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    /**
     * Método para adicionar 5 utentes de exemplo ao array.
     * Este método é chamado no construtor para inicializar a lista de utentes.
     */
    private void adicionarUtentesExemplo() {
        adicionarUtente(new Utente("123456789", "masculino", "João Silva", "912345678"));
        adicionarUtente(new Utente("987654321", "feminino", "Maria Oliveira", "987654321"));
        adicionarUtente(new Utente("456789123", "masculino", "Carlos Santos", "912345679"));
        adicionarUtente(new Utente("321654987", "feminino", "Ana Costa", "912345680"));
        adicionarUtente(new Utente("159753486", "masculino", "Pedro Almeida", "912345681"));
    }

    /**
     * Método para listar todos os utentes cadastrados.
     * Este método exibe a lista de todos os utentes no sistema.
     */
    public void listarUtentes() {
        if (contador == 0) {
            System.out.println("Nenhum utente cadastrado."); // Mensagem se não houver utentes
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + utentes[i]); // Exibe cada utente com seu índice
        }
    }

    /**
     * Método para atualizar um utente existente.
     * Este método coleta novas informações do usuário e atualiza o objeto correspondente no array.
     */
    public void atualizarUtente() {
        System.out.print("Digite o índice do utente que deseja atualizar (0 a " + (contador - 1) + "): ");
        int indice = ler.nextInt();
        ler.nextLine(); // Limpar o buffer

        if (indice < 0 || indice >= contador) {
            System.out.println("Índice inválido.");
            return;
        }

        // Coleta novas informações do usuário
        System.out.print("Novo NIF: ");
        String nif = ler.nextLine();
        if (!validarNIF(nif)) {
            System.out.println("NIF inválido. Deve ter 9 dígitos.");
            return;
        }

        System.out.print("Novo Género (masculino/feminino): ");
        String genero = ler.nextLine();
        if (!validarGenero(genero)) {
            System.out.println("Género inválido. Deve ser 'masculino' ou 'feminino'.");
            return;
        }

        System.out.print("Novo Nome: ");
        String nome = ler.nextLine();
        System.out.print("Novo Contacto: ");
        String contacto = ler.nextLine();
        if (!validarContacto(contacto)) {
            System.out.println("Contacto inválido. Deve ter 9 dígitos.");
            return;
        }

        // Atualiza o utente no array
        utentes[indice].setNif(nif);
        utentes[indice].setGenero(genero);
        utentes[indice].setNome(nome);
        utentes[indice].setContacto(contacto);

        System.out.println("Utente atualizado com sucesso!");
    }

    /**
     * Método para deletar um utente do array.
     * Este método remove o objeto correspondente e reorganiza o array.
     */
    public void deletarUtente() {
        System.out.print("Digite o índice do utente que deseja deletar (0 a " + (contador - 1) + "): ");
        int indice = ler.nextInt();
        ler.nextLine(); // Limpar o buffer

        if (indice < 0 || indice >= contador) {
            System.out.println("Índice inválido.");
            return;
        }

        // Move os elementos para preencher o espaço deixado pelo deletado
        for (int i = indice; i < contador - 1; i++) {
            utentes[i] = utentes[i + 1];
        }
        utentes[contador - 1] = null; // Limpa a última posição
        contador--; // Decrementa o contador

        System.out.println("Utente deletado com sucesso!");
    }

    /**
     * Método para gerenciar as operações relacionadas a utentes.
     * Este método exibe um menu e permite ao usuário escolher entre adicionar, listar, atualizar ou deletar utentes.
     */
    public void gerenciarUtentes() {
        int opcao;

        do {
            System.out.println("Menu de Utentes:");
            System.out.println("1. Adicionar Utente");
            System.out.println("2. Listar Utentes");
            System.out.println("3. Atualizar Utente");
            System.out.println("4. Deletar Utente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = ler.nextInt();
            ler.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    // Coleta informações do usuário para adicionar um novo utente
                    System.out.print("NIF: ");
                    String nif = ler.nextLine();
                    if (!validarNIF(nif)) {
                        System.out.println("NIF inválido. Deve ter 9 dígitos.");
                        break;
                    }

                    System.out.print("Género (masculino/feminino): ");
                    String genero = ler.nextLine();
                    if (!validarGenero(genero)) {
                        System.out.println("Género inválido. Deve ser 'masculino' ou 'feminino'.");
                        break;
                    }

                    System.out.print("Nome: ");
                    String nome = ler.nextLine();
                    System.out.print("Contacto: ");
                    String contacto = ler.nextLine();
                    if (!validarContacto(contacto)) {
                        System.out.println("Contacto inválido. Deve ter 9 dígitos.");
                        break;
                    }

                    // Cria um novo utente e adiciona ao controlador
                    Utente utente = new Utente(nif, genero, nome, contacto);
                    adicionarUtente(utente);
                    break;

                case 2:
                    listarUtentes(); // Lista todos os utentes
                    break;

                case 3:
                    atualizarUtente(); // Atualiza um utente
                    break;

                case 4:
                    deletarUtente(); // Deleta um utente
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
            }
        } while (opcao != 0);
    }

    /**
     * Método para validar o NIF (Número de Identificação Fiscal).
     * O NIF deve ter exatamente 9 dígitos.
     *
     * @param nif O NIF a ser validado
     * @return true se o NIF for válido, false caso contrário
     */
    private boolean validarNIF(String nif) {
        return nif.length() == 9 && nif.matches("\\d+");
    }

    /**
     * Método para validar o género do utente.
     * O género deve ser 'masculino' ou 'feminino'.
     *
     * @param genero O género a ser validado
     * @return true se o género for válido, false caso contrário
     */
    private boolean validarGenero(String genero) {
        return genero.equalsIgnoreCase("masculino") || genero.equalsIgnoreCase("feminino");
    }

    /**
     * Método para validar o contacto do utente.
     * O contacto deve ter exatamente 9 dígitos.
     *
     * @param contacto O contacto a ser validado
     * @return true se o contacto for válido, false caso contrário
     */
    private boolean validarContacto(String contacto) {

        return contacto.length() == 9 && contacto.matches("\\d+");
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

    /**
     * Método para obter a lista de utentes.
     *
     * @return Um array de Utente contendo todos os utentes cadastrados.
     */
    public Utente[] getUtentes() {
        return utentes; // Retorna o array de utentes
    }
}