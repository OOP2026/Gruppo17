package model.didattica;

import model.logistica.Aula;

import java.time.LocalTime;
import java.util.Objects;

public class Lezione {

    // =====================================================
    // ATTRIBUTI
    // =====================================================

    private final Insegnamento insegnamento;

    private Giorno giornoSettimana;

    private LocalTime oraInizio;

    private LocalTime oraFine;

    private final Aula aula;

    // =====================================================
    // COSTRUTTORE
    // =====================================================

    public Lezione(
            Insegnamento insegnamento,
            Giorno giornoSettimana,
            LocalTime oraInizio,
            LocalTime oraFine,
            Aula aula
    ) {

        if (insegnamento == null) {

            throw new IllegalArgumentException(
                    "L'insegnamento non può essere nullo."
            );
        }

        if (giornoSettimana == null) {

            throw new IllegalArgumentException(
                    "Il giorno della settimana non può essere nullo."
            );
        }

        // Domenica bloccata

        if (!giornoSettimana.disponibilePerLezioni()) {

            throw new IllegalArgumentException(
                    "Non è possibile programmare lezioni di domenica."
            );
        }

        if (oraInizio == null
                || oraFine == null) {

            throw new IllegalArgumentException(
                    "Gli orari non possono essere nulli."
            );
        }

        if (!oraFine.isAfter(oraInizio)) {

            throw new IllegalArgumentException(
                    "L'ora di fine deve essere successiva all'ora di inizio."
            );
        }

        if (aula == null) {

            throw new IllegalArgumentException(
                    "L'aula non può essere nulla."
            );
        }

        this.insegnamento =
                insegnamento;

        this.giornoSettimana =
                giornoSettimana;

        this.oraInizio =
                oraInizio;

        this.oraFine =
                oraFine;

        this.aula =
                aula;
    }

    // =====================================================
    // GETTER
    // =====================================================

    public Insegnamento getInsegnamento() {

        return insegnamento;
    }

    public Giorno getGiornoSettimana() {

        return giornoSettimana;
    }

    public LocalTime getOraInizio() {

        return oraInizio;
    }

    public LocalTime getOraFine() {

        return oraFine;
    }

    public Aula getAula() {

        return aula;
    }

    // =====================================================
    // SETTER
    // =====================================================

    public void setGiornoSettimana(
            Giorno giornoSettimana
    ) {

        if (giornoSettimana != null
                &&
                giornoSettimana.disponibilePerLezioni()) {

            this.giornoSettimana =
                    giornoSettimana;
        }
    }

    public void setOraInizio(
            LocalTime oraInizio
    ) {

        if (oraInizio != null) {

            this.oraInizio =
                    oraInizio;
        }
    }

    public void setOraFine(
            LocalTime oraFine
    ) {

        if (oraFine != null
                &&
                oraFine.isAfter(oraInizio)) {

            this.oraFine =
                    oraFine;
        }
    }

    // =====================================================
    // TOSTRING
    // =====================================================

    @Override
    public String toString() {

        return "Lezione{" +
                "insegnamento=" + insegnamento +
                ", giornoSettimana=" + giornoSettimana +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", aula=" + aula +
                '}';
    }

    // =====================================================
    // EQUALS
    // =====================================================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (!(obj instanceof Lezione)) {

            return false;
        }

        Lezione lezione =
                (Lezione) obj;

        return Objects.equals(
                insegnamento,
                lezione.insegnamento
        )
                &&
                giornoSettimana
                        ==
                        lezione.giornoSettimana
                &&
                Objects.equals(
                        oraInizio,
                        lezione.oraInizio
                )
                &&
                Objects.equals(
                        aula,
                        lezione.aula
                );
    }

    // =====================================================
    // HASHCODE
    // =====================================================

    @Override
    public int hashCode() {

        return Objects.hash(
                insegnamento,
                giornoSettimana,
                oraInizio,
                aula
        );
    }
}