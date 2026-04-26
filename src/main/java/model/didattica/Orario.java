package model.didattica;

import java.util.ArrayList;
import java.util.List;

public class Orario {

    private List<Lezione> lezioni;

    public Orario() {
        this.lezioni = new ArrayList<>();
    }

    public void aggiungiLezione(Lezione lezione) {
        lezioni.add(lezione);
    }

    public List<Lezione> getLezioni() {
        return lezioni;
    }
}
