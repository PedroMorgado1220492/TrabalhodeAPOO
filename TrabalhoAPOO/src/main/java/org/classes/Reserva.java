package org.classes;

import java.time.LocalDate;

public class Reserva {
    private int numero; // Número da reserva
    private LocalDate dataRegistro; // Data de registro da reserva
    private Utente utente; // Utente que fez a reserva
    private LocalDate dataInicio; // Data de início da reserva
    private LocalDate dataFim; // Data de fim da reserva
    private Livro[] livros; // Livros reservados

    public Reserva(int numero, LocalDate dataRegistro, Utente utente, LocalDate dataInicio, LocalDate dataFim, Livro[] livros) {
        this.numero = numero;
        this.dataRegistro = dataRegistro;
        this.utente = utente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.livros = livros;
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public Utente getUtente() {
        return utente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Livro[] getLivros() {
        return livros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva{")
                .append("numero=").append(numero)
                .append(", dataRegistro=").append(dataRegistro)
                .append(", utente=").append(utente.getNome())
                .append(", dataInicio=").append(dataInicio)
                .append(", dataFim=").append(dataFim)
                .append(", livros=");

        for (Livro livro : livros) {
            sb.append(livro.getTitulo()).append(", "); // Adiciona os títulos dos livros
        }

        sb.append('}');
        return sb.toString();
    }
}
