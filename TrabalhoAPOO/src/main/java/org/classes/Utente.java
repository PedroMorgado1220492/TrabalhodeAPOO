/**
 * 
 * @author Gustavo/PedroP
 * 
 * A classe Utente representa um utente do sistema, contendo informações como
 * Número de Identificação Fiscal (NIF), gênero, nome e contato.
 */

public class Utente {
    private String nif; // Número de Identificação Fiscal
    private String genero; // Gênero do utente
    private String nome; // Nome do utente
    private String contacto; // Contacto do utente

    /**
     * Construtor completo da classe Utente.
     * Este construtor inicializa todos os atributos do utente.
     *
     * @param nif O Número de Identificação Fiscal do utente
     * @param genero O gênero do utente
     * @param nome O nome do utente
     * @param contacto O contato do utente
     */
    public Utente(String nif, String genero, String nome, String contacto) {
        this.nif = nif;
        this.genero = genero;
        this.nome = nome;
        this.contacto = contacto;
    }

    /**
     * Construtor da classe Utente que aceita apenas o nome.
     * Este construtor inicializa o nome e define os outros atributos como vazios.
     *
     * @param nome O nome do utente
     */
    public Utente(String nome) {
        this.nome = nome;
        this.nif = ""; // Inicializa como vazio
        this.genero = ""; // Inicializa como vazio
        this.contacto = ""; // Inicializa como vazio
    }

    // Getters
    /**
     * Método para obter o NIF do utente.
     *
     * @return O NIF do utente
     */
    public String getNif() {

        return nif;
    }
    /**
     * Método para obter o gênero do utente.
     *
     * @return O gênero do utente
     */
    public String getGenero() {

        return genero;
    }
    /**
     * Método para obter o nome do utente.
     *
     * @return O nome do utente
     */
    public String getNome() {

        return nome;
    }
    /**
     * Método para obter o contato do utente.
     *
     * @return O contato do utente
     */
    public String getContacto() {

        return contacto;
    }

    // Setters
    /**
     * Método para definir o NIF do utente.
     *
     * @param nif O novo NIF do utente
     */
    public void setNif(String nif) {

        this.nif = nif;
    }
    /**
     * Método para definir o gênero do utente.
     *
     * @param genero O novo gênero do utente
     */
    public void setGenero(String genero) {

        this.genero = genero;
    }
    /**
     * Método para definir o nome do utente.
     *
     * @param nome O novo nome do utente
     */
    public void setNome(String nome) {

        this.nome = nome;
    }
    /**
     * Método para definir o contato do utente.
     *
     * @param contacto O novo contato do utente
     */
    public void setContacto(String contacto) {

        this.contacto = contacto;
    }

    /**
     * Método para representar o utente como uma string.
     *
     * @return Uma representação em string do utente
     */
    @Override
    public String toString() {
        return "Utente{" +
                "nif='" + nif + '\'' +
                ", genero='" + genero + '\'' +
                ", nome='" + nome + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}