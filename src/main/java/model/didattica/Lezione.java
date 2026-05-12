package model.didattica;

import model.logistica.Aula;

import java.time.LocalTime;
import java.util.Objects;

public class Lezione {

    private final Giorno giornoSettimana;

    private final LocalTime oraInizio;

    private final LocalTime oraFine;

    private final Aula aula;

    // Costruttore
    public Lezione(Giorno giornoSettimana,
                   LocalTime oraInizio,
                   LocalTime oraFine,
                   Aula aula) {

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

        if (oraInizio == null || oraFine == null) {
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

        this.giornoSettimana = giornoSettimana;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.aula = aula;
    }

    // Getter
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

    // toString
    @Override
    public String toString() {

        return "Lezione{" +
                "giornoSettimana=" + giornoSettimana +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
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

        return giornoSettimana == lezione.giornoSettimana
                && Objects.equals(oraInizio, lezione.oraInizio)
                && Objects.equals(aula, lezione.aula);
    }

    // hashCode
    @Override
    public int hashCode() {

        return Objects.hash(
                giornoSettimana,
                oraInizio,
                aula
        );
    }
}