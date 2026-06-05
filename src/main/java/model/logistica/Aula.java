package model.logistica;

import java.util.Objects;

public class Aula {

    private final String nomeAula;


    // Costruttore con validazione e pulizia degli spazi bianchi
    public Aula(String nomeAula) {

        if (nomeAula == null || nomeAula.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome dell'aula non può essere vuoto.");
        }

        this.nomeAula = nomeAula.trim();
    }


    // Getter standard (rinominato per uniformità con il resto del progetto e la GUI)
    public String getNome() {

        return nomeAula;
    }


    // Metodo equals con confronto "case-insensitive" per evitare duplicati nel database
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Aula aula = (Aula) o;

        return Objects.equals(nomeAula.toLowerCase(), aula.nomeAula.toLowerCase());
    }


    // Metodo hashCode coerente con il meccanismo equals case-insensitive
    @Override
    public int hashCode() {

        return Objects.hash(nomeAula.toLowerCase());
    }


    // Rappresentazione testuale dell'aula (usata nei JComboBox e nelle tabelle)
    @Override
    public String toString() {

        return nomeAula;
    }
}