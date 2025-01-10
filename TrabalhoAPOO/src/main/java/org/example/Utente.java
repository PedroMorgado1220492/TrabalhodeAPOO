public class Utente {
    private String nif; // Número de Identificação Fiscal
    private String genero; // Gênero do utente
    private String nome; // Nome do utente
    private String contacto; // Contacto do utentee

    public Utente(String nif, String genero, String nome, String contacto) {
        this.nif = nif;
        this.genero = genero;
        this.nome = nome;
        this.contacto = contacto;
    }

    // Getters
    public String getNif() {
        return nif;
    }

    public String getGenero() {
        return genero;
    }

    public String getNome() {
        return nome;
    }

    public String getContacto() {
        return contacto;
    }

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