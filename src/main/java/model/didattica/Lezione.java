package model.didattica;

import java.time.LocalTime;

import model.logistica.Aula;

public class Lezione {

    private String giornoSettimana;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private Aula aula;

    public Lezione(String giornoSettimana, LocalTime oraInizio,
                   LocalTime oraFine, Aula aula) {
        this.giornoSettimana = giornoSettimana;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.aula = aula;
    }

    public String getGiornoSettimana() {
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
}
