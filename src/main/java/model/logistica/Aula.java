package model.logistica;

public class Aula {

    private final String nomeAula;

    public Aula(String nomeAula) {

        if (nomeAula == null || nomeAula.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Il nome dell'aula non può essere vuoto."
            );
        }

        this.nomeAula = nomeAula;
    }

    public String getNomeAula() {
        return nomeAula;
    }

    @Override
    public String toString() {

        return "Aula{" +
                "nomeAula='" + nomeAula + '\'' +
                '}';
    }
}