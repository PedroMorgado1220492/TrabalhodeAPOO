/**
 * A classe Livro representa um livro com informações como título, ano de edição,
 * editora, ISBN, categoria e autores.
 */
public class Livro {
    private String titulo;
    private int anoEdicao;
    private String editora;
    private String isbn;
    private String categoria;
    private String autores;

    /**
     * Construtor da classe Livro.
     * Este construtor inicializa os atributos do livro e valida o ISBN.
     *
     * @param titulo O título do livro
     * @param anoEdicao O ano da edição do livro
     * @param editora A editora do livro
     * @param isbn O ISBN do livro
     * @param categoria A categoria do livro
     * @param autores Os autores do livro
     * @throws IllegalArgumentException Se o ISBN não for válido
     */
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

    /**
     * Método para validar o formato do ISBN.
     * O ISBN deve ter 10 ou 13 caracteres e ser composto apenas por dígitos ou 'X' na posição correta.
     *
     * @param isbn O ISBN a ser validado
     * @return true se o ISBN for válido, false caso contrário
     */
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

    /**
     * Método para definir o ano da edição do livro.
     *
     * @param anoEdicao O novo ano da edição
     */
    public void setAnoEdicao(int anoEdicao) {

        this.anoEdicao = anoEdicao;
    }
    /**
     * Método para definir a editora do livro.
     *
     * @param editora A nova editora
     */
    public void setEditora(String editora) {

        this.editora = editora;
    }
    /**
     * Método para definir a categoria do livro.
     *
     * @param categoria A nova categoria
     */
    public void setCategoria(String categoria) {

        this.categoria = categoria;
    }
    /**
     * Método para definir os autores do livro.
     *
     * @param autores Os novos autores
     */
    public void setAutores(String autores) {

        this.autores = autores;
    }
    /**
     * Método para definir o ISBN do livro.
     *
     * @param isbn O novo ISBN
     * @throws IllegalArgumentException Se o ISBN não for válido
     */
    public void setIsbn(String isbn) {
        if (!validarISBN(isbn)) {
            throw new IllegalArgumentException("ISBN deve ser válido: " + isbn);
        }
        this.isbn = isbn;
    }

    /**
     * Método para obter o título do livro.
     *
     * @return O título do livro
     */
    public String getTitulo() {

        return titulo;
    }
    /**
     * Método para obter o ISBN do livro.
     *
     * @return O ISBN do livro
     */
    public String getIsbn() {

        return isbn;
    }

    /**
     * Método para representar o livro como uma string.
     *
     * @return Uma representação em string do livro
     */
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