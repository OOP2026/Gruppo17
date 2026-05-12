package model.utente;

import model.didattica.Orario;
import model.richiesta.RichiestaSpostamento;
import model.richiesta.StatoRichiesta;

public class ResponsabileOrari extends Docente {

    // Costruttore
    public ResponsabileOrari(String nome,
                             String cognome,
                             String email,
                             String login,
                             String password) {

        super(nome, cognome, email, login, password);
    }

    // Approvazione richiesta
    public void approvaRichiesta(RichiestaSpostamento richiesta) {

        if(richiesta == null) {

            System.out.println("Errore: richiesta non valida.");
            return;
        }

        richiesta.setStato(StatoRichiesta.APPROVATA);

        System.out.println("Richiesta approvata dal responsabile: "
                + getNome() + " " + getCognome());
    }

    // Rifiuto richiesta
    public void rifiutaRichiesta(RichiestaSpostamento richiesta) {

        if(richiesta == null) {

            System.out.println("Errore: richiesta non valida.");
            return;
        }

        richiesta.setStato(StatoRichiesta.RIFIUTATA);

        System.out.println("Richiesta rifiutata dal responsabile: "
                + getNome() + " " + getCognome());
    }

    // Aggiornamento orario
    public void aggiornaOrario(Orario orario) {

        if(orario == null) {

            System.out.println("Errore: orario non valido.");
            return;
        }

        System.out.println("Orario aggiornato correttamente.");
    }

    // toString
    @Override
    public String toString() {

        return "ResponsabileOrari{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}