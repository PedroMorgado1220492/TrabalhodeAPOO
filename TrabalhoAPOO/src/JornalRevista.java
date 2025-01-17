import java.time.LocalDate;

public class JornalRevista {
    private String titulo;
    private String issn; // ISSN
    private String editora;
    private LocalDate dataPublicacao;
    private String categoria;

    public JornalRevista(String titulo, String issn, String editora, LocalDate dataPublicacao, String categoria) {
        if (!validarISSN(issn)) {
            throw new IllegalArgumentException("ISSN deve ser válido: " + issn);
        }
        this.titulo = titulo;
        this.issn = issn;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
    }

    private boolean validarISSN(String issn) {
        // Verifica se o ISSN tem o formato correto
        if (!issn.matches("\\d{4}-\\d{4}")) {
            return false; // O formato deve ser 0000-0000
        }

        // Separa os dígitos do ISSN
        String[] partes = issn.split("-");
        String parte1 = partes[0];
        String parte2 = partes[1];

        // Calcula a soma ponderada
        int soma = 0;
        for (int i = 0; i < 8; i++) {
            int digito = (i < 4) ? parte1.charAt(i) - '0' : parte2.charAt(i - 4) - '0';
            soma += digito * (8 - i); // Ponderação de 8 a 1
        }

        // Verifica o dígito de verificação
        int checkDigit = 11 - (soma % 11);
        if (checkDigit == 10) {
            return issn.charAt(7) == 'X'; // O último dígito pode ser 'X'
        } else {
            return checkDigit == (issn.charAt(7) - '0'); // Verifica se o dígito de verificação está correto
        }
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