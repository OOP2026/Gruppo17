package model.didattica;

import java.util.Objects;

public class Insegnamento {

    private final String nomeInsegnamento;

    private final int cfu;

    private final AnnoCorso annoCorso;

    // Costruttore
    public Insegnamento(String nomeInsegnamento,
                        int cfu,
                        AnnoCorso annoCorso) {

        if (nomeInsegnamento == null
                || nomeInsegnamento.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Il nome dell'insegnamento non può essere vuoto."
            );
        }

        if (cfu <= 0) {

            throw new IllegalArgumentException(
                    "I CFU devono essere maggiori di zero."
            );
        }

        if (annoCorso == null) {

            throw new IllegalArgumentException(
                    "L'anno di corso non può essere nullo."
            );
        }

        this.nomeInsegnamento = nomeInsegnamento;
        this.cfu = cfu;
        this.annoCorso = annoCorso;
    }

    // Getter
    public String getNomeInsegnamento() {
        return nomeInsegnamento;
    }

    public int getCfu() {
        return cfu;
    }

    public AnnoCorso getAnnoCorso() {
        return annoCorso;
    }

    // toString
    @Override
    public String toString() {

        return "Insegnamento{" +
                "nomeInsegnamento='" + nomeInsegnamento + '\'' +
                ", cfu=" + cfu +
                ", annoCorso=" + annoCorso +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Insegnamento)) {
            return false;
        }

        Insegnamento insegnamento =
                (Insegnamento) obj;

        return Objects.equals(
                nomeInsegnamento,
                insegnamento.nomeInsegnamento
        );
    }

    // hashCode
    @Override
    public int hashCode() {

        return Objects.hash(nomeInsegnamento);
    }
}