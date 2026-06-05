package model.richiesta;

import model.didattica.Giorno;
import model.didattica.OraInizio;
import model.utente.Docente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class RichiestaSpostamento {

    private final Docente docente;
    private final String motivazione;
    private StatoRichiesta stato;
    private final LocalDate dataRichiesta;

    private final Giorno giornoAttuale;
    private final OraInizio oraInizioAttuale;

    private final Giorno giornoProposto;
    private final OraInizio oraInizioProposta;


    // Costruttore completo che lega subito la richiesta al docente richiedente
    public RichiestaSpostamento(
            Docente docente,
            String motivazione,
            Giorno giornoAttuale,
            OraInizio oraInizioAttuale,
            Giorno giornoProposto,
            OraInizio oraInizioProposta
    ) {

        if (docente == null) {
            throw new IllegalArgumentException("Il docente non può essere nullo.");
        }

        if (motivazione == null || motivazione.trim().isEmpty()) {
            throw new IllegalArgumentException("La motivazione non può essere vuota.");
        }

        if (giornoAttuale == null || giornoProposto == null) {
            throw new IllegalArgumentException("I giorni non possono essere nulli.");
        }

        if (oraInizioAttuale == null || oraInizioProposta == null) {
            throw new IllegalArgumentException("Gli orari non possono essere nulli.");
        }

        this.docente = docente;
        this.motivazione = motivazione;
        this.giornoAttuale = giornoAttuale;
        this.oraInizioAttuale = oraInizioAttuale;
        this.giornoProposto = giornoProposto;
        this.oraInizioProposta = oraInizioProposta;

        this.stato = StatoRichiesta.IN_ATTESA;
        this.dataRichiesta = LocalDate.now();
    }


    // Getter per il docente che ha inoltrato la richiesta
    public Docente getDocente() {

        return docente;
    }


    // Getter per la motivazione dello spostamento
    public String getMotivazione() {

        return motivazione;
    }


    // Getter per lo stato corrente della pratica
    public StatoRichiesta getStato() {

        return stato;
    }


    // Setter per aggiornare lo stato (usato dal ResponsabileOrari)
    public void setStato(StatoRichiesta stato) {

        if (stato != null) {
            this.stato = stato;
        }
    }


    // Getter per la data di invio della richiesta
    public LocalDate getDataRichiesta() {

        return dataRichiesta;
    }


    // Getter per il giorno corrente della lezione da spostare
    public Giorno getGiornoAttuale() {

        return giornoAttuale;
    }


    // Getter per l'orario di inizio attuale
    public OraInizio getOraInizioAttuale() {

        return oraInizioAttuale;
    }


    // Calcolo automatico dell'ora di fine attuale della lezione (2 ore)
    public LocalTime getOraFineAttuale() {

        if (oraInizioAttuale == null) {
            return null;
        }

        return oraInizioAttuale.getTime().plusHours(2);
    }


    // Getter per il giorno proposto dal docente
    public Giorno getGiornoProposto() {

        return giornoProposto;
    }


    // Getter per l'orario d'inizio proposto dal docente
    public OraInizio getOraInizioProposta() {

        return oraInizioProposta;
    }


    // Calcolo automatico dell'ora di fine proposta per la lezione (2 ore)
    public LocalTime getOraFineProposta() {

        if (oraInizioProposta == null) {
            return null;
        }

        return oraInizioProposta.getTime().plusHours(2);
    }


    // Metodo equals basato su docente, motivazione e data per identificare univocamente l'istanza
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RichiestaSpostamento)) {
            return false;
        }

        RichiestaSpostamento richiesta = (RichiestaSpostamento) obj;

        return Objects.equals(docente, richiesta.docente)
                && Objects.equals(motivazione, richiesta.motivazione)
                && Objects.equals(dataRichiesta, richiesta.dataRichiesta);
    }


    // Metodo hashCode coerente con equals
    @Override
    public int hashCode() {

        return Objects.hash(docente, motivazione, dataRichiesta);
    }


    // Rappresentazione testuale sintetica della richiesta
    @Override
    public String toString() {

        return "Richiesta di " + docente + ": " + giornoAttuale + " -> " + giornoProposto + " [" + stato + "]";
    }
}