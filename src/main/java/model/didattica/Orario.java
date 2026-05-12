package model.didattica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orario {

    private final List<Lezione> lezioni;

    // Costruttore
    public Orario() {

        this.lezioni = new ArrayList<>();
    }

    // Aggiunta lezione
    public boolean aggiungiLezione(Lezione lezione) {

        if (lezione == null) {
            return false;
        }

        // Controllo duplicati
        if (lezioni.contains(lezione)) {

            System.out.println(
                    "Errore: la lezione è già presente nell'orario."
            );

            return false;
        }

        lezioni.add(lezione);

        System.out.println("Lezione aggiunta correttamente.");

        return true;
    }

    // Rimozione lezione
    public boolean rimuoviLezione(Lezione lezione) {

        return lezioni.remove(lezione);
    }

    // Getter sicuro
    public List<Lezione> getLezioni() {

        return Collections.unmodifiableList(lezioni);
    }

    // Numero lezioni
    public int numeroLezioni() {

        return lezioni.size();
    }

    // toString
    @Override
    public String toString() {

        return "Orario{" +
                "lezioni=" + lezioni +
                '}';
    }
}