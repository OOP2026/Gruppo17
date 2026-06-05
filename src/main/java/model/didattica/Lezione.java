package model.didattica;

import model.logistica.Aula;
import java.time.LocalTime;
import java.util.Objects;

public class Lezione {

    private final Insegnamento insegnamento;
    private Giorno giornoSettimana;
    private OraInizio oraInizio;
    private Aula aula;


    // Costruttore completo con controlli sui vincoli accademici
    public Lezione(
            Insegnamento insegnamento,
            Giorno giornoSettimana,
            OraInizio oraInizio,
            Aula aula
    ) {

        if (insegnamento == null) {
            throw new IllegalArgumentException("L'insegnamento non può essere nullo.");
        }

        if (giornoSettimana == null) {
            throw new IllegalArgumentException("Il giorno della settimana non può essere nullo.");
        }

        if (!giornoSettimana.disponibilePerLezioni()) {
            throw new IllegalArgumentException("Non è possibile programmare lezioni di domenica.");
        }

        if (oraInizio == null) {
            throw new IllegalArgumentException("L'orario di inizio non può essere nullo.");
        }

        if (aula == null) {
            throw new IllegalArgumentException("L'aula non può essere nulla.");
        }

        this.insegnamento = insegnamento;
        this.giornoSettimana = giornoSettimana;
        this.oraInizio = oraInizio;
        this.aula = aula;
    }


    // Getter per la materia della lezione
    public Insegnamento getInsegnamento() {

        return insegnamento;
    }


    // Getter per il giorno della settimana
    public Giorno getGiornoSettimana() {

        return giornoSettimana;
    }


    // Setter per modificare il giorno della lezione
    public void setGiornoSettimana(Giorno giornoSettimana) {

        if (giornoSettimana != null && giornoSettimana.disponibilePerLezioni()) {
            this.giornoSettimana = giornoSettimana;
        }
    }


    // Getter per la fascia oraria di inizio
    public OraInizio getOraInizio() {

        return oraInizio;
    }


    // Setter per modificare l'orario di inizio
    public void setOraInizio(OraInizio oraInizio) {

        if (oraInizio != null) {
            this.oraInizio = oraInizio;
        }
    }


    // Calcolo automatico dell'ora di fine lezione (durata standard di 2 ore)
    public LocalTime getOraFine() {

        if (oraInizio == null) {
            return null;
        }

        return oraInizio.getTime().plusHours(2);
    }


    // Getter per l'aula assegnata
    public Aula getAula() {

        return aula;
    }


    // Setter per modificare l'aula (utile per la risoluzione dei conflitti)
    public void setAula(Aula aula) {

        if (aula != null) {
            this.aula = aula;
        }
    }


    // Metodo equals basato sui parametri core della lezione
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


    // Metodo hashCode coerente con il meccanismo di equals
    @Override
    public int hashCode() {

        return Objects.hash(insegnamento, giornoSettimana, oraInizio, aula);
    }


    // Rappresentazione testuale pulita per la lezione
    @Override
    public String toString() {

        return insegnamento.getNomeInsegnamento() + " - " + giornoSettimana + " " + oraInizio + " (" + aula.getNome() + ")";
    }
}