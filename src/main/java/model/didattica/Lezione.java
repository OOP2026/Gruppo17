package model.didattica;

import model.logistica.Aula;
import java.time.LocalTime;
import java.util.Objects;

public class Lezione {

    private final Insegnamento insegnamento;
    private Giorno giornoSettimana;
    private OraInizio oraInizio;
    private final Aula aula;

    public Lezione(
            Insegnamento insegnamento,
            Giorno giornoSettimana,
            OraInizio oraInizio,
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

        if (!giornoSettimana.disponibilePerLezioni()) {
            throw new IllegalArgumentException(
                    "Non è possibile programmare lezioni di domenica."
            );
        }

        if (oraInizio == null) {
            throw new IllegalArgumentException(
                    "L'orario di inizio non può essere nullo."
            );
        }

        if (aula == null) {
            throw new IllegalArgumentException(
                    "L'aula non può essere nulla."
            );
        }

        this.insegnamento = insegnamento;
        this.giornoSettimana = giornoSettimana;
        this.oraInizio = oraInizio;
        this.aula = aula;
    }

    public Insegnamento getInsegnamento() {
        return insegnamento;
    }

    public Giorno getGiornoSettimana() {
        return giornoSettimana;
    }

    public OraInizio getOraInizio() {
        return oraInizio;
    }

    public LocalTime getOraFine() {
        if (oraInizio == null) return null;
        return oraInizio.getTime().plusHours(2);
    }

    public Aula getAula() {
        return aula;
    }

    public void setGiornoSettimana(Giorno giornoSettimana) {
        if (giornoSettimana != null && giornoSettimana.disponibilePerLezioni()) {
            this.giornoSettimana = giornoSettimana;
        }
    }

    public void setOraInizio(OraInizio oraInizio) {
        if (oraInizio != null) {
            this.oraInizio = oraInizio;
        }
    }

    @Override
    public String toString() {
        return "Lezione{" +
                "insegnamento=" + insegnamento +
                ", giornoSettimana=" + giornoSettimana +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + getOraFine() +
                ", aula=" + aula +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Lezione)) {
            return false;
        }

        Lezione lezione = (Lezione) obj;

        return Objects.equals(insegnamento, lezione.insegnamento)
                && giornoSettimana == lezione.giornoSettimana
                && oraInizio == lezione.oraInizio
                && Objects.equals(aula, lezione.aula);
    }

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