public class Livro {
    private String titulo;
    private int anoEdicao;
    private String editora;
    private String isbn;
    private String categoria;
    private String autores;

    public Livro(String titulo, int anoEdicao, String editora, String isbn, String categoria, String autores) {
        if (!validarISBN(isbn)) {
            throw new IllegalArgumentException("ISBN deve ser válido: " + isbn);
        }
        this.titulo = titulo;
        this.anoEdicao = anoEdicao;
        this.editora = editora;
        this.isbn = isbn;
        this.categoria = categoria;
        this.autores = autores;
    }

    private boolean validarISBN(String isbn) {
        // Remove espaços e hífens para facilitar a validação
        isbn = isbn.replaceAll("[-\\s]", "");

        // Verifica se o ISBN tem 10 ou 13 caracteres
        if (isbn.length() != 10 && isbn.length() != 13) {
            return false;
        }

        // Verifica se todos os caracteres são dígitos ou 'X'
        for (int i = 0; i < isbn.length(); i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c) && !(c == 'X' && i == 9 && isbn.length() == 10)) {
                return false; // Deve ser um dígito ou 'X' na posição correta
            }
        }
        return true; // ISBN válido
    }

    // Métodos set para atualizar os atributos
    public void setAnoEdicao(int anoEdicao) {
        this.anoEdicao = anoEdicao;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public void setIsbn(String isbn) {
        if (!validarISBN(isbn)) {
            throw new IllegalArgumentException("ISBN deve ser válido: " + isbn);
        }
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
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