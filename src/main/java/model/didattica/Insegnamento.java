package model.didattica;

import model.utente.Docente;

import java.util.Objects;

public class Insegnamento {

    private final String nomeInsegnamento;

    private final int cfu;

    private final AnnoCorso annoCorso;

    // =====================================================
    // NUOVO CAMPO
    // =====================================================

    private final Docente docenteTitolare;

    // =====================================================
    // COSTRUTTORE
    // =====================================================

    public Insegnamento(
            String nomeInsegnamento,
            int cfu,
            AnnoCorso annoCorso,
            Docente docenteTitolare
    ) {

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

        if (docenteTitolare == null) {

            throw new IllegalArgumentException(
                    "Il docente titolare non può essere nullo."
            );
        }

        this.nomeInsegnamento =
                nomeInsegnamento;

        this.cfu = cfu;

        this.annoCorso = annoCorso;

        this.docenteTitolare =
                docenteTitolare;
    }

    // =====================================================
    // GETTER
    // =====================================================

    public String getNomeInsegnamento() {

        return nomeInsegnamento;
    }

    public int getCfu() {

        return cfu;
    }

    public AnnoCorso getAnnoCorso() {

        return annoCorso;
    }

    public Docente getDocenteTitolare() {

        return docenteTitolare;
    }

    // =====================================================
    // TOSTRING
    // =====================================================

    @Override
    public String toString() {

        return nomeInsegnamento;
    }

    // =====================================================
    // EQUALS
    // =====================================================

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

    // =====================================================
    // HASHCODE
    // =====================================================

    @Override
    public int hashCode() {

        return Objects.hash(
                nomeInsegnamento
        );
    }
}