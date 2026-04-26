package model.richiesta;

import java.time.LocalDate;

public class RichiestaSpostamento {

    private String motivazione;
    private StatoRichiesta stato;
    private LocalDate dataRichiesta;

    public RichiestaSpostamento(String motivazione) {
        this.motivazione = motivazione;
        this.stato = StatoRichiesta.IN_ATTESA; // default ✔
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

    public void setStato(StatoRichiesta stato) {
        this.stato = stato;
    }
}
