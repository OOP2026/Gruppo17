package model.logistica;

import java.util.Objects;

public class Aula {

    private final String nomeAula;

    public Aula(String nomeAula) {
        if (nomeAula == null || nomeAula.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome dell'aula non può essere vuoto.");
        }
        this.nomeAula = nomeAula.trim();
    }

    public String getNomeAula() {
        return nomeAula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(nomeAula.toLowerCase(), aula.nomeAula.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeAula.toLowerCase());
    }

    @Override
    public String toString() {
        return nomeAula;
    }
}