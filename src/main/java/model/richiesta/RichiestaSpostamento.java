package model.richiesta;

import model.didattica.Giorno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class RichiestaSpostamento {

    private final String motivazione;

    private StatoRichiesta stato;

    private final LocalDate dataRichiesta;

    // Dati attuali della lezione
    private final Giorno giornoAttuale;
    private final LocalTime oraInizioAttuale;
    private final LocalTime oraFineAttuale;

    // Nuova proposta
    private final Giorno giornoProposto;
    private final LocalTime oraInizioProposta;
    private final LocalTime oraFineProposta;

    // Costruttore
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

        this.motivazione = motivazione;

        this.giornoAttuale = giornoAttuale;
        this.oraInizioAttuale = oraInizioAttuale;
        this.oraFineAttuale = oraFineAttuale;

        this.giornoProposto = giornoProposto;
        this.oraInizioProposta = oraInizioProposta;
        this.oraFineProposta = oraFineProposta;

        this.stato = StatoRichiesta.IN_ATTESA;

        this.dataRichiesta = LocalDate.now();
    }

    // Getter
    public String getMotivazione() {
        return motivazione;
    }

    public StatoRichiesta getStato() {
        return stato;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
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

    // Setter stato
    public void setStato(StatoRichiesta stato) {

        if (stato != null) {
            this.stato = stato;
        }
    }

    // toString
    @Override
    public String toString() {

        return "RichiestaSpostamento{" +
                "motivazione='" + motivazione + '\'' +
                ", stato=" + stato +
                ", dataRichiesta=" + dataRichiesta +
                ", giornoAttuale=" + giornoAttuale +
                ", giornoProposto=" + giornoProposto +
                '}';
    }

    // equals
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
                && Objects.equals(
                dataRichiesta,
                richiesta.dataRichiesta
        );
    }

    // hashCode
    @Override
    public int hashCode() {

        return Objects.hash(motivazione, dataRichiesta);
    }
}