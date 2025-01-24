package org.controller;

import org.classes.JornalRevista;
import java.time.LocalDate;
import java.util.Scanner;

public class ControlerJornalRevista {
    private JornalRevista[] jornaisRevistas; // Array fixo de 100 jornais/revistas
    private int contador; // Para rastrear o número de jornais/revistas adicionados
    private Scanner ler; // Scanner para entrada do usuário

    public ControlerJornalRevista() {
        this.jornaisRevistas = new JornalRevista[100]; // Inicializa o array com tamanho 100
        this.contador = 0; // Inicializa o contador
        this.ler = new Scanner(System.in); // Inicializa o Scanner
        inicializarJornaisRevistas(); // Chama o método para inicializar com 5 exemplos
    }

    // Método para retornar a lista de jornaisrevistas
    public JornalRevista[] getJornaisRevistas() {
        return jornaisRevistas;
    }

    // Método para inicializar o array com 5 jornais/revistas por defeito
    private void inicializarJornaisRevistas() {
        // Adiciona 5 jornais/revistas ao array
        adicionarJornalRevista(new JornalRevista("Revista Veja", "1234-5678", LocalDate.of(2023, 1, 15), "Notícias", "Editora Abril"));
        adicionarJornalRevista(new JornalRevista("Jornal O Globo", "2345-6789", LocalDate.of(2023, 2, 20), "Jornalismo", "Globo"));
        adicionarJornalRevista(new JornalRevista("Revista Época", "3456-7890", LocalDate.of(2023, 3, 10), "Atualidades", "Editora Globo"));
        adicionarJornalRevista(new JornalRevista("Jornal Folha de S.Paulo", "4567-8901", LocalDate.of(2023, 4, 5), "Jornalismo", "Folha"));
        adicionarJornalRevista(new JornalRevista("Revista Superinteressante", "5678-9012", LocalDate.of(2023, 5, 25), "Ciência", "Editora Abril"));
    }

    // Método para adicionar um JornalRevista ao array
    private void adicionarJornalRevista(JornalRevista jornalRevista) {
        if (contador >= jornaisRevistas.length) {
            System.out.println("Não é possível adicionar mais jornais/revistas. O array está cheio.");
            return;
        }
        jornaisRevistas[contador] = jornalRevista; // Adiciona o objeto ao array
        contador++; // Incrementa o contador
        System.out.println("Jornal/Revista adicionado com sucesso!");
    }

    public void gerenciarJornaisRevistas() {
        int opcao;

        do {
            System.out.println("Menu de Jornais e Revistas:");
            System.out.println("1. Adicionar Jornal/Revista");
            System.out.println("2. Listar Jornais/Revistas");
            System.out.println("3. Atualizar Jornal/Revista");
            System.out.println("4. Deletar Jornal/Revista");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = ler.nextInt();
            ler.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarJornalRevista(); // Chama o método para adicionar um jornal/revista
                    break;
                case 2:
                    listarJornaisRevistas(); // Lista todos os jornais/revistas
                    break;
                case 3:
                    atualizarJornalRevista(); // Chama o método para atualizar um jornal/revista
                    break;
                case 4:
                    deletarJornalRevista(); // Chama o método para deletar um jornal/revista
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarJornalRevista() {
        if (contador >= jornaisRevistas.length) {
            System.out.println("Não é possível adicionar mais jornais /revistas. O array está cheio.");
            return;
        }

        System.out.print("Título: ");
        String titulo = ler.nextLine();

        String issn;
        while (true) {
            System.out.print("ISSN: ");
            issn = ler.nextLine();

            if (validarISSN(issn)) {
                break; // ISSN válido, sai do loop
            } else {
                System.out.println("ISSN inválido. O formato correto é XXXX-XXXX, onde o último dígito pode ser de 0 a 9 ou 'X'. Tente novamente.");
            }
        }

        System.out.print("Data de Publicação (YYYY-MM-DD): ");
        LocalDate dataPublicacao = LocalDate.parse(ler.nextLine());

        System.out.print("Categoria: ");
        String categoria = ler.nextLine();

        System.out.print("Editora: ");
        String editora = ler.nextLine();

        // Cria um novo objeto JornalRevista
        JornalRevista novoJornal = new JornalRevista(titulo, issn, dataPublicacao, categoria, editora);
        adicionarJornalRevista(novoJornal); // Adiciona o novo objeto ao array
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

    private void atualizarJornalRevista() {
        System.out.print("Digite o índice do jornal/revista que deseja atualizar (0 a " + (contador - 1) + "): ");
        int indice = ler.nextInt();
        ler.nextLine(); // Limpar o buffer

        if (indice < 0 || indice >= contador) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("Novo Título: ");
        String titulo = ler.nextLine();

        String issn;
        while (true) {
            System.out.print("Novo ISSN: ");
            issn = ler.nextLine();

            if (validarISSN(issn)) {
                break; // ISSN válido, sai do loop
            } else {
                System.out.println("ISSN inválido. O formato correto é XXXX-XXXX, onde o último dígito pode ser de 0 a 9 ou 'X'. Tente novamente.");
            }
        }

        System.out.print("Nova Data de Publicação (YYYY-MM-DD): ");
        LocalDate dataPublicacao = LocalDate.parse(ler.nextLine());

        System.out.print("Nova Categoria: ");
        String categoria = ler.nextLine();

        System.out.print("Nova Editora: ");
        String editora = ler.nextLine();

        // Atualiza o jornal/revista no array
        jornaisRevistas[indice].setTitulo(titulo);
        jornaisRevistas[indice].setIssn(issn);
        jornaisRevistas[indice].setDataPublicacao(dataPublicacao);
        jornaisRevistas[indice].setCategoria(categoria);
        jornaisRevistas[indice].setEditora(editora);

        System.out.println("Jornal/Revista atualizado com sucesso!");
    }

    private void deletarJornalRevista() {
        System.out.print("Digite o índice do jornal/revista que deseja deletar (0 a " + (contador - 1) + "): ");
        int indice = ler.nextInt();
        ler.nextLine(); // Limpar o buffer

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

    // Método para fechar o Scanner ao final do uso
    public void fecharScanner() {
        if (ler != null) {
            ler.close(); // Fecha o scanner ao final
        }
    }
}