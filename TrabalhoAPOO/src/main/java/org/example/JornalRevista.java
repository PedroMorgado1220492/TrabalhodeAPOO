import java.time.LocalDate;

public class JornalRevista {
    private String titulo;
    private String issn; // ISSN
    private String editora;
    private LocalDate dataPublicacao;
    private String categoria;

    public JornalRevista(String titulo, String issn, String editora, LocalDate dataPublicacao, String categoria) {
        if (!validarISSN(issn)) {
            throw new IllegalArgumentException("ISSN deve ter 8 dígitos: " + issn);
        }
        this.titulo = titulo;
        this.issn = issn;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
    }

    // Método de validação do ISSN
    private boolean validarISSN(String issn) {
        return issn.length() == 8 && issn.matches("\\d{4}-\\d{4}");
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getIssn() {
        return issn;
    }

    public String getEditora() {
        return editora;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "JornalRevista{" +
                "titulo='" + titulo + '\'' +
                ", issn='" + issn + '\'' +
                ", editora='" + editora + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}