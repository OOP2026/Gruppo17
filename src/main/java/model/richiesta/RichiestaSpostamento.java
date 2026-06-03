package model.richiesta;

import model.didattica.Giorno;
import model.didattica.OraInizio;
import model.utente.Docente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class RichiestaSpostamento {

    private final String motivazione;
    private StatoRichiesta stato;
    private final LocalDate dataRichiesta;
    private Docente docente;

    private final Giorno giornoAttuale;
    private final OraInizio oraInizioAttuale;

    private final Giorno giornoProposto;
    private final OraInizio oraInizioProposta;

    public RichiestaSpostamento(
            String motivazione,
            Giorno giornoAttuale,
            OraInizio oraInizioAttuale,
            Giorno giornoProposto,
            OraInizio oraInizioProposta
    ) {
        if (motivazione == null || motivazione.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La motivazione non può essere vuota."
            );
        }

        if (giornoAttuale == null || giornoProposto == null) {
            throw new IllegalArgumentException(
                    "I giorni non possono essere nulli."
            );
        }

        if (oraInizioAttuale == null || oraInizioProposta == null) {
            throw new IllegalArgumentException(
                    "Gli orari non possono essere nulli."
            );
        }

        this.motivazione = motivazione;
        this.giornoAttuale = giornoAttuale;
        this.oraInizioAttuale = oraInizioAttuale;
        this.giornoProposto = giornoProposto;
        this.oraInizioProposta = oraInizioProposta;
        this.stato = StatoRichiesta.IN_ATTESA;
        this.dataRichiesta = LocalDate.now();
    }

    public String getMotivazione() {
        return motivazione;
    }

    public StatoRichiesta getStato() {
        return stato;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public Docente getDocente() {
        return docente;
    }

    public Giorno getGiornoAttuale() {
        return giornoAttuale;
    }

    public OraInizio getOraInizioAttuale() {
        return oraInizioAttuale;
    }

    public LocalTime getOraFineAttuale() {
        if (oraInizioAttuale == null) return null;
        return oraInizioAttuale.getTime().plusHours(2);
    }

    public Giorno getGiornoProposto() {
        return giornoProposto;
    }

    public OraInizio getOraInizioProposta() {
        return oraInizioProposta;
    }

    public LocalTime getOraFineProposta() {
        if (oraInizioProposta == null) return null;
        return oraInizioProposta.getTime().plusHours(2);
    }

    public void setStato(StatoRichiesta stato) {
        if (stato != null) {
            this.stato = stato;
        }
    }

    public void setDocente(Docente docente) {
        if (docente != null) {
            this.docente = docente;
        }
    }

    @Override
    public String toString() {
        return "RichiestaSpostamento{" +
                "docente=" + docente +
                ", motivazione='" + motivazione + '\'' +
                ", stato=" + stato +
                ", dataRichiesta=" + dataRichiesta +
                ", giornoAttuale=" + giornoAttuale +
                ", giornoProposto=" + giornoProposto +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RichiestaSpostamento)) {
            return false;
        }

        RichiestaSpostamento richiesta = (RichiestaSpostamento) obj;

        return Objects.equals(motivazione, richiesta.motivazione)
                && Objects.equals(dataRichiesta, richiesta.dataRichiesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motivazione, dataRichiesta);
    }
}