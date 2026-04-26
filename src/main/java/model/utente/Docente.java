package model.utente;

import model.richiesta.RichiestaSpostamento;

public class Docente extends Utente {

    public Docente(String nome, String cognome, String email,
                   String login, String password) {
        super(nome, cognome, email, login, password);
    }

    @Override
    public void visualizzaOrario() {
        System.out.println("Visualizzazione orario del docente " + getNome());
    }

    public RichiestaSpostamento inviaRichiesta(String motivazione) {
        RichiestaSpostamento richiesta = new RichiestaSpostamento(motivazione);
        System.out.println("Richiesta inviata dal docente " + getNome());
        return richiesta;
    }
}