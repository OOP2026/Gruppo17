package model.logistica;

public class Aula {

    private String nomeAula;

    public Aula(String nomeAula) {
        this.nomeAula = nomeAula;
    }

    public String getNomeAula() {
        return nomeAula;
    }

    public void setNomeAula(String nomeAula) {
        this.nomeAula = nomeAula;
    }

    @Override
    public String toString() {
        return "Aula: " + nomeAula;
    }
}
