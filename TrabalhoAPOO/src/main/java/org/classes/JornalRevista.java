import java.time.LocalDate;

/**
 * 
 * @author Gustavo/PedroP
 * 
 * A classe JornalRevista representa uma publicação periódica, como um jornal ou revista.
 * Ela contém informações sobre o título, ISSN, data de publicação, categoria e editora da publicação.
 */
public class JornalRevista {
    private String titulo;
    private String issn;
    private LocalDate dataPublicacao;
    private String categoria;
    private String editora;

    /**
     * Construtor da classe JornalRevista.
     *
     * @param titulo O título da publicação
     * @param issn O ISSN da publicação
     * @param dataPublicacao A data de publicação
     * @param categoria A categoria da publicação
     * @param editora A editora da publicação
     */
    public JornalRevista(String titulo, String issn, LocalDate dataPublicacao, String categoria, String editora) {
        this.titulo = titulo;
        this.issn = issn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.editora = editora;
    }

    // Getters
    /**
     * Método para obter o título da publicação.
     *
     * @return O título da publicação
     */
    public String getTitulo() {

        return titulo;
    }
    /**
     * Método para obter o ISSN da publicação.
     *
     * @return O ISSN da publicação
     */
    public String getIssn() {

        return issn;
    }
    /**
     * Método para obter a data de publicação.
     *
     * @return A data de publicação
     */
    public LocalDate getDataPublicacao() {
        return
                dataPublicacao;
    }
    /**
     * Método para obter a categoria da publicação.
     *
     * @return A categoria da publicação
     */
    public String getCategoria() {

        return categoria;
    }
    /**
     * Método para obter a editora da publicação.
     *
     * @return A editora da publicação
     */
    public String getEditora() {

        return editora;
    }

    /**
     * Método para obter o título da publicação (usado para exibição).
     *
     * @return O título da publicação
     */
    public String getJornaisRevistas() {

        return titulo;
    }

    // Setters

    /**
     * Método para definir o título da publicação.
     *
     * @param titulo O novo título da publicação
     */
    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }
    /**
     * Método para definir o ISSN da publicação.
     *
     * @param issn O novo ISSN da publicação
     */
    public void setIssn(String issn) {

        this.issn = issn;
    }
    /**
     * Método para definir a data de publicação.
     *
     * @param dataPublicacao A nova data de publicação
     */
    public void setDataPublicacao(LocalDate dataPublicacao) {

        this.dataPublicacao = dataPublicacao;
    }
    /**
     * Método para definir a categoria da publicação.
     *
     * @param categoria A nova categoria da publicação
     */
    public void setCategoria(String categoria) {

        this.categoria = categoria;
    }
    /**
     * Método para definir a editora da publicação.
     *
     * @param editora A nova editora da publicação
     */
    public void setEditora(String editora) {

        this.editora = editora;
    }

    /**
     * Método para representar a publicação como uma string.
     *
     * @return Uma representação em string da publicação
     */
    @Override
    public String toString() {
        return "JornalRevista{" +
                "titulo='" + titulo + '\'' +
                ", issn='" + issn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoria='" + categoria + '\'' +
                ", editora='" + editora + '\'' +
                '}';
    }
}