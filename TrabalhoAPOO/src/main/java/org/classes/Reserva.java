import java.time.LocalDate;

/**
 * 
 * @author Gustavo/PedroP
 * 
 * 
 * A classe Reserva representa uma reserva de livros feita por um utente.
 * Ela contém informações sobre o número da reserva, a data de registro,
 * o utente que fez a reserva, as datas de início e fim da reserva,
 * e os livros que foram reservados.
 */
public class Reserva {
    private int numero; // Número da reserva
    private LocalDate dataRegistro; // Data de registro da reserva
    private Utente utente; // Utente que fez a reserva
    private LocalDate dataInicio; // Data de início da reserva
    private LocalDate dataFim; // Data de fim da reserva
    private Livro[] livros; // Livros reservados

    /**
     * Construtor da classe Reserva.
     *
     * @param numero O número da reserva
     * @param dataRegistro A data de registro da reserva
     * @param utente O utente que fez a reserva
     * @param dataInicio A data de início da reserva
     * @param dataFim A data de fim da reserva
     * @param livros Os livros que foram reservados
     */
    public Reserva(int numero, LocalDate dataRegistro, Utente utente, LocalDate dataInicio, LocalDate dataFim, Livro[] livros) {
        this.numero = numero;
        this.dataRegistro = dataRegistro;
        this.utente = utente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.livros = livros;
    }

    /**
     * Método para obter o ISBN do primeiro livro na reserva.
     *
     * @return O ISBN do primeiro livro na reserva ou null se não houver livros
     */
    public String getLivroIsbn() {
        if (livros != null && livros.length > 0) {
            return livros[0].getIsbn(); // Retorna o ISBN do primeiro livro na reserva
        }
        return null; // Retorna null se não houver livros
    }

    // Getters
    /**
     * Método para obter o número da reserva.
     *
     * @return O número da reserva
     */
    public int getNumero() {

        return numero;
    }
    /**
     * Método para obter a data de registro da reserva.
     *
     * @return A data de registro da reserva
     */

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }
    /**
     * Método para obter o utente que fez a reserva.
     *
     * @return O utente que fez a reserva
     */
    public Utente getUtente() {

        return utente;
    }
    /**
     * Método para obter a data de início da reserva.
     *
     * @return A data de início da reserva
     */
    public LocalDate getDataInicio() {

        return dataInicio;
    }
    /**
     * Método para obter a data de fim da reserva.
     *
     * @return A data de fim da reserva
     */
    public LocalDate getDataFim() {

        return dataFim;
    }
    /**
     * Método para obter os livros reservados.
     *
     * @return Um array de Livro contendo os livros reservados
     */
    public Livro[] getLivros() {

        return livros;
    }

    /**
     * Método para representar a reserva como uma string.
     *
     * @return Uma representação em string da reserva
     */
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
