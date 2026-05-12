package model.didattica;

import java.time.LocalDate;
import java.util.Objects;

public class AnnoAccademico {

    private final String anno;

    private final LocalDate inizio;

    private final LocalDate fine;

    // Costruttore
    public AnnoAccademico(String anno,
                          LocalDate inizio,
                          LocalDate fine) {

        if (anno == null || anno.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "L'anno accademico non può essere vuoto."
            );
        }

        if (inizio == null || fine == null) {

            throw new IllegalArgumentException(
                    "Le date non possono essere nulle."
            );
        }

        if (fine.isBefore(inizio)) {

            throw new IllegalArgumentException(
                    "La data di fine non può precedere la data di inizio."
            );
        }

        this.anno = anno;
        this.inizio = inizio;
        this.fine = fine;
    }

    // Getter
    public String getAnno() {
        return anno;
    }

    public LocalDate getInizio() {
        return inizio;
    }

    public LocalDate getFine() {
        return fine;
    }

    // toString
    @Override
    public String toString() {

        return "AnnoAccademico{" +
                "anno='" + anno + '\'' +
                ", inizio=" + inizio +
                ", fine=" + fine +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AnnoAccademico)) {
            return false;
        }

        AnnoAccademico annoAccademico =
                (AnnoAccademico) obj;

        return Objects.equals(
                anno,
                annoAccademico.anno
        );
    }

    // hashCode
    @Override
    public int hashCode() {

        return Objects.hash(anno);
    }
}