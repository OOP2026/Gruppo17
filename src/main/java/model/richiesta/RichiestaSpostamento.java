package model.richiesta;

import model.didattica.Giorno;
import model.utente.Docente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class RichiestaSpostamento {

    // =====================================================
    // ATTRIBUTI
    // =====================================================

    private final String motivazione;

    private StatoRichiesta stato;

    private final LocalDate dataRichiesta;

    private Docente docente;

    // =====================================================
    // DATI ATTUALI LEZIONE
    // =====================================================

    private final Giorno giornoAttuale;

    private final LocalTime oraInizioAttuale;

    private final LocalTime oraFineAttuale;

    // =====================================================
    // NUOVA PROPOSTA
    // =====================================================

    private final Giorno giornoProposto;

    private final LocalTime oraInizioProposta;

    private final LocalTime oraFineProposta;

    // =====================================================
    // COSTRUTTORE
    // =====================================================

    public RichiestaSpostamento(
            String motivazione,
            Giorno giornoAttuale,
            LocalTime oraInizioAttuale,
            LocalTime oraFineAttuale,
            Giorno giornoProposto,
            LocalTime oraInizioProposta,
            LocalTime oraFineProposta
    ) {

        if (motivazione == null
                || motivazione.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "La motivazione non può essere vuota."
            );
        }

        if (giornoAttuale == null
                || giornoProposto == null) {

            throw new IllegalArgumentException(
                    "I giorni non possono essere nulli."
            );
        }

        if (oraInizioAttuale == null
                || oraFineAttuale == null
                || oraInizioProposta == null
                || oraFineProposta == null) {

            throw new IllegalArgumentException(
                    "Gli orari non possono essere nulli."
            );
        }

        if (!oraFineAttuale.isAfter(
                oraInizioAttuale)) {

            throw new IllegalArgumentException(
                    "L'orario attuale non è valido."
            );
        }

        if (!oraFineProposta.isAfter(
                oraInizioProposta)) {

            throw new IllegalArgumentException(
                    "L'orario proposto non è valido."
            );
        }

        this.motivazione =
                motivazione;

        this.giornoAttuale =
                giornoAttuale;

        this.oraInizioAttuale =
                oraInizioAttuale;

        this.oraFineAttuale =
                oraFineAttuale;

        this.giornoProposto =
                giornoProposto;

        this.oraInizioProposta =
                oraInizioProposta;

        this.oraFineProposta =
                oraFineProposta;

        this.stato =
                StatoRichiesta.IN_ATTESA;

        this.dataRichiesta =
                LocalDate.now();
    }

    // =====================================================
    // GETTER
    // =====================================================

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

    public LocalTime getOraInizioAttuale() {

        return oraInizioAttuale;
    }

    public LocalTime getOraFineAttuale() {

        return oraFineAttuale;
    }

    public Giorno getGiornoProposto() {

        return giornoProposto;
    }

    public LocalTime getOraInizioProposta() {

        return oraInizioProposta;
    }

    public LocalTime getOraFineProposta() {

        return oraFineProposta;
    }

    // =====================================================
    // SETTER
    // =====================================================

    public void setStato(
            StatoRichiesta stato
    ) {

        if (stato != null) {

            this.stato = stato;
        }
    }

    public void setDocente(
            Docente docente
    ) {

        if (docente != null) {

            this.docente = docente;
        }
    }

    // =====================================================
    // TOSTRING
    // =====================================================

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

    // =====================================================
    // EQUALS
    // =====================================================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (!(obj instanceof RichiestaSpostamento)) {

            return false;
        }

        RichiestaSpostamento richiesta =
                (RichiestaSpostamento) obj;

        return Objects.equals(
                motivazione,
                richiesta.motivazione
        )
                &&
                Objects.equals(
                        dataRichiesta,
                        richiesta.dataRichiesta
                );
    }

    // =====================================================
    // HASHCODE
    // =====================================================

    @Override
    public int hashCode() {

        return Objects.hash(
                motivazione,
                dataRichiesta
        );
    }
}