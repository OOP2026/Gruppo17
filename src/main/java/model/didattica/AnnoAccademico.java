package model.didattica;

import java.time.LocalDate;

public class AnnoAccademico {

    private String anno;
    private LocalDate inizio;
    private LocalDate fine;

    public AnnoAccademico(String anno, LocalDate inizio, LocalDate fine) {
        this.anno = anno;
        this.inizio = inizio;
        this.fine = fine;
    }

    // GETTER
    public String getAnno() {
        return anno;
    }

    public LocalDate getInizio() {
        return inizio;
    }

    public LocalDate getFine() {
        return fine;
    }
}