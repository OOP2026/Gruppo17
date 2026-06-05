package model.didattica;

import java.time.LocalDate;
import java.util.Objects;

public class AnnoAccademico {

    private final String anno;
    private final LocalDate inizio;
    private final LocalDate fine;


    // Costruttore con validazione dei dati
    public AnnoAccademico(
            String anno,
            LocalDate inizio,
            LocalDate fine
    ) {

        if (anno == null || anno.trim().isEmpty()) {
            throw new IllegalArgumentException("L'anno accademico non può essere vuoto.");
        }

        if (inizio == null || fine == null) {
            throw new IllegalArgumentException("Le date non possono essere nulle.");
        }

        if (fine.isBefore(inizio)) {
            throw new IllegalArgumentException("La data di fine non può precedere la data di inizio.");
        }

        this.anno = anno;
        this.inizio = inizio;
        this.fine = fine;
    }


    // Getter per la stringa dell'anno (es. "2025/2026")
    public String getAnno() {

        return anno;
    }


    // Getter per la data di inizio anno accademico
    public LocalDate getInizio() {

        return inizio;
    }


    // Getter per la data di fine anno accademico
    public LocalDate getFine() {

        return fine;
    }


    // Metodo equals basato sul nome dell'anno accademico
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AnnoAccademico)) {
            return false;
        }

        AnnoAccademico annoAccademico = (AnnoAccademico) obj;

        return Objects.equals(anno, annoAccademico.anno);
    }


    // Metodo hashCode coerente con equals
    @Override
    public int hashCode() {

        return Objects.hash(anno);
    }


    // Metodo toString per la rappresentazione testuale dell'oggetto
    @Override
    public String toString() {

        return anno;
    }
}