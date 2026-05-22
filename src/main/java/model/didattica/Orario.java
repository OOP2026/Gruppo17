package model.didattica;

import model.logistica.Aula;
import model.richiesta.RichiestaSpostamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orario {

    // =========================
    // ATTRIBUTI
    // =========================

    private final List<Lezione> lezioni;

    private final List<Aula> aule;

    private final List<Insegnamento> insegnamenti;

    private final List<RichiestaSpostamento> richieste;

    // =========================
    // COSTRUTTORE
    // =========================

    public Orario() {

        this.lezioni = new ArrayList<>();

        this.aule = new ArrayList<>();

        this.insegnamenti = new ArrayList<>();

        this.richieste = new ArrayList<>();
    }

    // =========================
    // LEZIONI
    // =========================

    public boolean aggiungiLezione(
            Lezione lezione
    ) {

        if (lezione == null) {

            return false;
        }

        // Controllo duplicati

        if (lezioni.contains(lezione)) {

            System.out.println(
                    "Errore: la lezione è già presente."
            );

            return false;
        }

        lezioni.add(lezione);

        System.out.println(
                "Lezione aggiunta correttamente."
        );

        return true;
    }

    public boolean rimuoviLezione(
            Lezione lezione
    ) {

        return lezioni.remove(lezione);
    }

    public List<Lezione> getLezioni() {

        return Collections.unmodifiableList(
                lezioni
        );
    }

    public int numeroLezioni() {

        return lezioni.size();
    }

    // =========================
    // AULE
    // =========================

    public void aggiungiAula(
            Aula aula
    ) {

        if(aula != null) {

            aule.add(aula);
        }
    }

    public List<Aula> getAule() {

        return Collections.unmodifiableList(
                aule
        );
    }

    // =========================
    // INSEGNAMENTI
    // =========================

    public void aggiungiInsegnamento(
            Insegnamento insegnamento
    ) {

        if(insegnamento != null) {

            insegnamenti.add(
                    insegnamento
            );
        }
    }

    public List<Insegnamento>
    getInsegnamenti() {

        return Collections.unmodifiableList(
                insegnamenti
        );
    }

    // =========================
    // RICHIESTE
    // =========================

    public void aggiungiRichiesta(
            RichiestaSpostamento richiesta
    ) {

        if(richiesta != null) {

            richieste.add(richiesta);
        }
    }

    public List<RichiestaSpostamento>
    getRichieste() {

        return Collections.unmodifiableList(
                richieste
        );
    }

    // =========================
    // toString
    // =========================

    @Override
    public String toString() {

        return "Orario{" +
                "lezioni=" + lezioni +
                ", aule=" + aule +
                ", insegnamenti=" + insegnamenti +
                ", richieste=" + richieste +
                '}';
    }
}