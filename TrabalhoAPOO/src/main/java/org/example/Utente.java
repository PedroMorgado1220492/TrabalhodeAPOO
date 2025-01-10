public class Livro {
    private String titulo;
    private int anoEdicao;
    private String editora;
    private String isbn;
    private String categoria;
    private String autores;

    public Livro(String titulo, int anoEdicao, String editora, String isbn, String categoria, String autores) {
        if (!validarISBN(isbn)) {
            throw new IllegalArgumentException("ISBN deve ter 10 ou 13 dígitos: " + isbn);
        }
        this.titulo = titulo;
        this.anoEdicao = anoEdicao;
        this.editora = editora;
        this.isbn = isbn;
        this.categoria = categoria;
        this.autores = autores;
    }

    // Método de validação simplificado
    private boolean validarISBN(String isbn) {
        return isbn.length() == 10 || isbn.length() == 13;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public int getAnoEdicao() {
        return anoEdicao;
    }

    public String getEditora() {
        return editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getAutores() {
        return autores;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", anoEdicao=" + anoEdicao +
                ", editora='" + editora + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categoria='" + categoria + '\'' +
                ", autores='" + autores + '\'' +
                '}';
    }
}