package org.controller;

import org.classes.Utente;
import org.classes.Livro;
import org.classes.Emprestimo;
import java.time.LocalDate;
import java.util.Scanner;

public class ControlerEmprestimo {
    private Emprestimo[] emprestimos; // Array fixo para armazenar empréstimos
    private int contador; // Contador para rastrear o número de empréstimos
    private ControlerLivro controlerLivro; // Controlador de livros
    private ControlerUtente controlerUtente; // Controlador de utentes
    private Scanner ler; // Scanner para entrada do usuário

    public ControlerEmprestimo(ControlerLivro controlerLivro, ControlerUtente controlerUtente) {
        this.emprestimos = new Emprestimo[100]; // Inicializa o array com capacidade para 100 empréstimos
        this.contador = 0; // Inicializa o contador
        this.controlerLivro = controlerLivro; // Recebe o controlador de livros
        this.controlerUtente = controlerUtente; // Recebe o controlador de utentes
        this.ler = new Scanner(System.in); // Inicializa o Scanner
        inicializarEmprestimos(); // Adiciona 3 empréstimos por defeito
    }

    // Método para inicializar o array com 3 empréstimos por defeito
    private void inicializarEmprestimos() {
        // Verifica se existem utentes e livros disponíveis
        Utente utente1 = controlerUtente.getUtentes()[0]; // Supondo que já existam utentes
        Utente utente2 = controlerUtente.getUtentes()[1];
        Livro livro1 = controlerLivro.getLivros()[0]; // Supondo que já existam livros
        Livro livro2 = controlerLivro.getLivros()[1];

        // Adiciona 3 empréstimos por defeito
        adicionarEmprestimo(new Emprestimo(contador + 1, LocalDate.of(2023, 1, 10), utente1, LocalDate.of(2023, 1, 20), new Livro[]{livro1}));
        adicionarEmprestimo(new Emprestimo(contador + 1, LocalDate.of(2023, 2, 15), utente2, LocalDate.of(2023, 2, 25), new Livro[]{livro2}));
        adicionarEmprestimo(new Emprestimo(contador + 1, LocalDate.of(2023, 3, 5), utente1, LocalDate.of(2023, 3, 15), new Livro[]{livro1, livro2}));
    }

    // Método para adicionar um novo empréstimo ao array
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        if (contador < 100) { // Verifica se ainda há espaço no array
            if (!verificarSobreposicao(emprestimo)) {
                emprestimos[contador] = emprestimo; // Adiciona o empréstimo na posição atual do contador
                contador++; // Incrementa o contador
                System.out.println("Empréstimo adicionado com sucesso!");
            } else {
                System.out.println("Não é possível adicionar o empréstimo devido à sobreposição de datas.");
            }
        } else {
            System.out.println("Limite de empréstimos atingido!"); // Mensagem de erro se o limite for atingido
        }
    }

    // Método para verificar sobreposição de datas
    private boolean verificarSobreposicao(Emprestimo novoEmprestimo) {
        for (int i = 0; i < contador; i++) {
            Emprestimo emprestimoExistente = emprestimos[i];
            if (emprestimoExistente != null) {
                LocalDate inicioExistente = emprestimoExistente.getDataInicio();
                LocalDate fimExistente = emprestimoExistente.getDataPrevistaDevolucao();
                LocalDate inicioNovo = novoEmprestimo.getDataInicio();
                LocalDate fimNovo = novoEmprestimo.getDataPrevistaDevolucao();

                // Verifica se há sobreposição
                if (inicioNovo.isBefore(fimExistente) && fimNovo.isAfter(inicioExistente) ||
                        inicioNovo.isEqual(inicioExistente) || fimNovo.isEqual(fimExistente)) {
                    return true; // Há sobreposição
                }
            }
        }
        return false; // Não há sobreposição
    }

    // Método para gerenciar as operações relacionadas a empréstimos
    public void gerenciarEmprestimos() {
        int opcao;

        do {
            System.out.println("Menu de Empréstimos:");
            System.out.println("1. Adicionar Empréstimo");
            System.out.println("2. Listar Empréstimos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = ler.nextInt();
            ler.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarNovoEmprestimo();
                    break;

                case 2:
                    listarEmprestimos(); // Lista todos os empréstimos
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal..."); // Mensagem ao voltar
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente."); // Mensagem de erro para opção inválida
            }
        } while (opcao != 0);
    }

    // Método para adicionar um novo empréstimo
    private void adicionarNovoEmprestimo() {
        System.out.print("Data de Início (YYYY-MM-DD): ");
        LocalDate dataInicio = LocalDate.parse(ler.nextLine());
        System.out.print("Utente (Nome): ");
        String nomeUtente = ler.nextLine();

        // Verifica se o utente existe
        Utente utente = buscarUtente(nomeUtente);
        if (utente == null) {
            System.out.println("Utente não encontrado: " + nomeUtente);
            return;
        }

        System.out.print("Data Prevista de Devolução (YYYY-MM-DD): ");
        LocalDate dataPrevistaDevolucao = LocalDate.parse(ler.nextLine());

        // Verifica se a data de início é antes da data de devolução
        if (!verificarDatasEmprestimo(dataInicio, dataPrevistaDevolucao)) {
            System.out.println("A data de início deve ser anterior à data de devolução.");
            return;
        }

        System.out.print("Número de Livros: ");
        int numLivros = ler.nextInt();
        Livro[] livros = new Livro[numLivros];

        boolean livroDisponivel = true; // Flag para verificar a disponibilidade do livro

        for (int i = 0; i < numLivros; i++) {
            System.out.print("Título do Livro " + (i + 1) + ": ");
            String tituloLivro = ler.next();
            // Verifica se o livro está cadastrado
            Livro livro = buscarLivro(tituloLivro);
            if (livro != null) {
                livros[i] = livro; // Adiciona o livro ao array se estiver disponível
            } else {
                System.out.println("Livro não encontrado: " + tituloLivro);
                livroDisponivel = false; // Marca como não disponível
            }
        }

        if (livroDisponivel) {
            Emprestimo emprestimo = new Emprestimo(contador + 1, dataInicio, utente, dataPrevistaDevolucao, livros);
            adicionarEmprestimo(emprestimo);
        } else {
            System.out.println("Não foi possível realizar o empréstimo devido à indisponibilidade de um ou mais livros.");
        }
    }

    // Método para verificar se a data de início é antes da data de devolução
    private boolean verificarDatasEmprestimo(LocalDate dataInicio, LocalDate dataPrevistaDevolucao) {
        return dataInicio.isBefore(dataPrevistaDevolucao); // Retorna true se a data de início for antes da data de devolução
    }

    // Método para buscar um utente pelo nome
    private Utente buscarUtente(String nomeUtente) {
        for (Utente utente : controlerUtente.getUtentes()) { // Supondo que ControlerUtente tenha um método getUtentes()
            if (utente != null && utente.getNome().equalsIgnoreCase(nomeUtente)) {
                return utente; // Retorna o utente se encontrado
            }
        }
        return null; // Retorna null se o utente não for encontrado
    }

    // Método para buscar um livro pelo título
    private Livro buscarLivro(String titulo) {
        for (Livro livro : controlerLivro.getLivros()) { // Supondo que ControlerLivro tenha um método getLivros()
            if (livro != null && livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro; // Retorna o livro se encontrado
            }
        }
        return null; // Retorna null se o livro não for encontrado
    }

    // Método para listar todos os empréstimos cadastrados
    public void listarEmprestimos() {
        if (contador == 0) {
            System.out.println("Nenhum empréstimo cadastrado."); // Mensagem se não houver empréstimos
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ": " + emprestimos[i]); // Exibe cada empréstimo com seu índice
        }
    }

    // Método para fechar o Scanner ao final do uso
    public void fecharScanner() {
        if (ler != null) {
            ler.close(); // Fecha o scanner ao final
        }
    }
}