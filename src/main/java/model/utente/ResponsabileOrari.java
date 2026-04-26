package model.utente;

import model.richiesta.RichiestaSpostamento;
import model.richiesta.StatoRichiesta;
import model.didattica.Orario;

public class ResponsabileOrari extends Docente {

    public ResponsabileOrari(String nome, String cognome,
                             String email, String login, String password) {
        super(nome, cognome, email, login, password);
    }

    public void approvaRichiesta(RichiestaSpostamento richiesta) {
        richiesta.setStato(StatoRichiesta.APPROVATA);
        System.out.println("Richiesta approvata.");
    }

    public void rifiutaRichiesta(RichiestaSpostamento richiesta) {
        richiesta.setStato(StatoRichiesta.RIFIUTATA);
        System.out.println("Richiesta rifiutata.");
    }

    public void aggiornaOrario(Orario orario) {
        System.out.println("Orario aggiornato.");
        // логіка оновлення
    }
}