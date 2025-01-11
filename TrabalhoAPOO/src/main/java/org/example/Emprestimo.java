package org.example;

import java.time.LocalDate;

public class Emprestimo {
    private int numero; // Número do empréstimo
    private LocalDate dataInicio; // Data de início do empréstimo
    private Utente utente; // Utente que fez o empréstimo
    private LocalDate dataPrevistaDevolucao; // Data prevista de devolução
    private Livro[] livros; // Livros emprestados
    private LocalDate dataEfetivaDevolucao; // Data efetiva de devolução

    public Emprestimo(int numero, LocalDate dataInicio, Utente utente, LocalDate dataPrevistaDevolucao, Livro[] livros) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.utente = utente;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.livros = livros;
        this.dataEfetivaDevolucao = null; // Inicialmente, a data efetiva de devolução é nula
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public Utente getUtente() {
        return utente;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public Livro[] getLivros() {
        return livros;
    }

    public LocalDate getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }

    public void setDataEfetivaDevolucao(LocalDate dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao; // Define a data efetiva de devolução
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empréstimo{")
                .append("número=").append(numero)
                .append(", dataInicio=").append(dataInicio)
                .append(", utente=").append(utente.getNome())
                .append(", dataPrevistaDevolucao=").append(dataPrevistaDevolucao)
                .append(", livros=");

        for (Livro livro : livros) {
            sb.append(livro.getTitulo()).append(", "); // Adiciona os títulos dos livros
        }

        sb.append("dataEfetivaDevolucao=").append(dataEfetivaDevolucao)
                .append('}');
        return sb.toString();
    }
}
