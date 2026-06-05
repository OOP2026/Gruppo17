package model.didattica;

import model.logistica.Aula;
import model.richiesta.RichiestaSpostamento;
import model.utente.Docente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orario {

    private final List<Lezione> lezioni;
    private final List<Aula> aule;
    private final List<Insegnamento> insegnamenti;
    private final List<RichiestaSpostamento> richieste;


    // Costruttore principale che inizializza le liste interne
    public Orario() {

        this.lezioni = new ArrayList<>();
        this.aule = new ArrayList<>();
        this.insegnamenti = new ArrayList<>();
        this.richieste = new ArrayList<>();
    }


    // Metodo per verificare i vincoli di sovrapposizione (Risoluzione conflitti)
    public boolean haConflitti(Lezione nuovaLezione) {

        for (Lezione l : lezioni) {

            // Controllo se giorno e ora coincidono
            if (l.getGiornoSettimana() == nuovaLezione.getGiornoSettimana()
                    && l.getOraInizio() == nuovaLezione.getOraInizio()) {

                // Vincolo 1: L'aula è già occupata?
                if (l.getAula().equals(nuovaLezione.getAula())) {
                    return true;
                }

                // Vincolo 2: Il docente è già impegnato in un'altra lezione?
                Docente d1 = l.getInsegnamento().getDocenteTitolare();
                Docente d2 = nuovaLezione.getInsegnamento().getDocenteTitolare();

                if (d1.equals(d2)) {
                    return true;
                }
            }
        }

        return false;
    }


    // Metodo per aggiungere una lezione verificando i vincoli del sistema
    public boolean aggiungiLezione(Lezione lezione) {

        if (lezione == null) {
            return false;
        }

        // Se viola i vincoli del TЗ, l'aggiunta fallisce
        if (haConflitti(lezione)) {
            return false;
        }

        return lezioni.add(lezione);
    }


    // Metodo per rimuovere una lezione esistente
    public boolean rimuoviLezione(Lezione lezione) {

        return lezioni.remove(lezione);
    }


    // Getter sicuro per la lista delle lezioni
    public List<Lezione> getLezioni() {

        return Collections.unmodifiableList(lezioni);
    }


    // Metodo per ottenere il numero totale di lezioni pianificate
    public int numeroLezioni() {

        return lezioni.size();
    }


    // Metodo per inserire una nuova aula disponibile
    public void aggiungiAula(Aula aula) {

        if (aula != null && !aule.contains(aula)) {
            aule.add(aula);
        }
    }


    // Getter sicuro per la lista delle aule
    public List<Aula> getAule() {

        return Collections.unmodifiableList(aule);
    }


    // Metodo per inserire un nuovo insegnamento attivo
    public void aggiungiInsegnamento(Insegnamento insegnamento) {

        if (insegnamento != null && !insegnamenti.contains(insegnamento)) {
            insegnamenti.add(insegnamento);
        }
    }


    // Getter sicuro per la lista degli insegnamenti
    public List<Insegnamento> getInsegnamenti() {

        return Collections.unmodifiableList(insegnamenti);
    }


    // Metodo per registrare una richiesta di spostamento inviata da un docente
    public void aggiungiRichiesta(RichiestaSpostamento richiesta) {

        if (richiesta != null) {
            richieste.add(richiesta);
        }
    }


    // Getter sicuro per la lista delle richieste
    public List<RichiestaSpostamento> getRichieste() {

        return Collections.unmodifiableList(richieste);
    }


    // Rappresentazione testuale sintetica dell'orario complessivo
    @Override
    public String toString() {

        return "Orario generale: " + lezioni.size() + " lezioni pianificate, " + aule.size() + " aule attive.";
    }
}