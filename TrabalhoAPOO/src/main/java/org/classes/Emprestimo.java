import java.time.LocalDate;

/**
 * 
 * @author Gustavo/Pedro
 * 
 * 
 * A classe Emprestimo representa um empréstimo de livros feito por um utente.
 * Ela contém informações sobre o número do empréstimo, as datas de início e de devolução,
 * o utente que fez o empréstimo e os livros emprestados.
 */

public class Emprestimo {
    private int numero; // Número do empréstimo
    private LocalDate dataInicio; // Data de início do empréstimo
    private Utente utente; // Utente que fez o empréstimo
    private LocalDate dataPrevistaDevolucao; // Data prevista de devolução
    private Livro[] livros; // Livros emprestados

    /**
     * Construtor da classe Emprestimo.
     *
     * @param numero O número do empréstimo
     * @param dataInicio A data de início do empréstimo
     * @param utente O utente que fez o empréstimo
     * @param dataPrevistaDevolucao A data prevista de devolução do empréstimo
     * @param livros Os livros que estão sendo emprestados
     */
    public Emprestimo(int numero, LocalDate dataInicio, Utente utente, LocalDate dataPrevistaDevolucao, Livro[] livros) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.utente = utente;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.livros = livros;
    }

    // Getters

    /**
     * Método para obter o número do empréstimo.
     *
     * @return O número do empréstimo
     */
    public int getNumero() {

        return numero;
    }
    /**
     * Método para obter a data de início do empréstimo.
     *
     * @return A data de início do empréstimo
     */
    public LocalDate getDataInicio() {

        return dataInicio;
    }
    /**
     * Método para obter o utente que fez o empréstimo.
     *
     * @return O utente que fez o empréstimo
     */
    public Utente getUtente() {

        return utente;
    }
    /**
     * Método para obter a data prevista de devolução do empréstimo.
     *
     * @return A data prevista de devolução
     */
    public LocalDate getDataPrevistaDevolucao() {

        return dataPrevistaDevolucao;
    }
    /**
     * Método para obter os livros emprestados.
     *
     * @return Um array de Livro contendo os livros emprestados
     */
    public Livro[] getLivros() {

        return livros;
    }

    /**
     * Método para representar o empréstimo como uma string.
     *
     * @return Uma representação em string do empréstimo
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empréstimo{")
                .append("numero=").append(numero)
                .append(", dataInicio=").append(dataInicio)
                .append(", utente=").append(utente.getNome())
                .append(", dataPrevistaDevolucao=").append(dataPrevistaDevolucao)
                .append(", livros=");

        for (Livro livro : livros) {
            sb.append(livro.getTitulo()).append(", "); // Adiciona os títulos dos livros
        }

        sb.append('}');
        return sb.toString();
    }
}