import java.time.LocalDate;

public class JornalRevista {
    private String titulo;
    private String issn;
    private LocalDate dataPublicacao;
    private String categoria;
    private String editora;

    public JornalRevista(String titulo, String issn, LocalDate dataPublicacao, String categoria, String editora) {
        this.titulo = titulo;
        this.issn = issn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.editora = editora;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getIssn() {
        return issn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEditora() {
        return editora;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

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