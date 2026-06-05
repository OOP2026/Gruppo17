package model.utente;

import model.richiesta.RichiestaSpostamento;
import model.richiesta.StatoRichiesta;

public class ResponsabileOrari extends Docente {

    // Costruttore principale per il responsabile degli orari (Admin)
    public ResponsabileOrari(
            String nome,
            String cognome,
            String email,
            String login,
            String password
    ) {

        super(nome, cognome, email, login, password, UserRole.ADMIN);
    }


    // Metodo per approvare la richiesta di spostamento della lezione
    public void approvaRichiesta(RichiestaSpostamento richiesta) {

        if (richiesta == null) {
            throw new IllegalArgumentException("La richiesta non può essere nulla.");
        }

        richiesta.setStato(StatoRichiesta.APPROVATA);
    }


    // Metodo per rifiutare la richiesta di spostamento della lezione
    public void rifiutaRichiesta(RichiestaSpostamento richiesta) {

        if (richiesta == null) {
            throw new IllegalArgumentException("La richiesta non può essere nulla.");
        }

        richiesta.setStato(StatoRichiesta.RIFIUTATA);
    }


    // Metodo toString per la rappresentazione testuale del responsabile
    @Override
    public String toString() {

        return getNome() + " " + getCognome() + " (Responsabile Orari)";
    }
}