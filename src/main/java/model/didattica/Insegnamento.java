package model.didattica;

import model.utente.Docente;
import java.util.Objects;

public class Insegnamento {

    private final String nomeInsegnamento;
    private final int cfu;
    private final AnnoCorso annoCorso;
    private final Docente docenteTitolare;


    // Costruttore completo con controlli di validità
    public Insegnamento(
            String nomeInsegnamento,
            int cfu,
            AnnoCorso annoCorso,
            Docente docenteTitolare
    ) {

        if (nomeInsegnamento == null || nomeInsegnamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome dell'insegnamento non può essere vuoto.");
        }

        if (cfu <= 0) {
            throw new IllegalArgumentException("I CFU devono essere maggiori di zero.");
        }

        if (annoCorso == null) {
            throw new IllegalArgumentException("L'anno di corso non può essere nullo.");
        }

        if (docenteTitolare == null) {
            throw new IllegalArgumentException("Il docente titolare non può essere nullo.");
        }

        this.nomeInsegnamento = nomeInsegnamento;
        this.cfu = cfu;
        this.annoCorso = annoCorso;
        this.docenteTitolare = docenteTitolare;
    }


    // Getter per il nome del corso
    public String getNomeInsegnamento() {

        return nomeInsegnamento;
    }


    // Getter per il numero di CFU
    public int getCfu() {

        return cfu;
    }


    // Getter per l'anno di corso (I, II, III)
    public AnnoCorso getAnnoCorso() {

        return annoCorso;
    }


    // Getter per il docente responsabile della materia
    public Docente getDocenteTitolare() {

        return docenteTitolare;
    }


    // Metodo equals basato sul nome univoco dell'insegnamento
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Insegnamento)) {
            return false;
        }

        Insegnamento insegnamento = (Insegnamento) obj;

        return Objects.equals(nomeInsegnamento, insegnamento.nomeInsegnamento);
    }


    // Metodo hashCode associato al nome dell'insegnamento
    @Override
    public int hashCode() {

        return Objects.hash(nomeInsegnamento);
    }


    // Rappresentazione testuale dell'insegnamento (usata anche nei JComboBox)
    @Override
    public String toString() {

        return nomeInsegnamento;
    }
}