package model.didattica;

public enum Giorno {

    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA;

    public boolean disponibilePerLezioni() {
        return this != DOMENICA;
    }
}
