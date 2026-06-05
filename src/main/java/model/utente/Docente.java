package model.utente;

import model.didattica.Giorno;
import model.didattica.OraInizio;
import model.richiesta.RichiestaSpostamento;

public class Docente extends Utente {

    // Costruttore principale per il docente standard
    public Docente(
            String nome,
            String cognome,
            String email,
            String login,
            String password
    ) {

        super(nome, cognome, email, login, password, UserRole.TEACHER);
    }


    // Costruttore protetto per permettere a ResponsabileOrari di ereditare correttamente
    protected Docente(
            String nome,
            String cognome,
            String email,
            String login,
            String password,
            UserRole role
    ) {

        super(nome, cognome, email, login, password, role);
    }


    // Metodo per la creazione di una richiesta di spostamento della lezione
    public RichiestaSpostamento inviaRichiesta(
            String motivazione,
            Giorno giornoAttuale,
            OraInizio oraAttuale,
            Giorno giornoProposto,
            OraInizio oraProposta
    ) {

        if (motivazione == null || motivazione.trim().isEmpty()) {
            throw new IllegalArgumentException("La motivazione non può essere vuota.");
        }

        return new RichiestaSpostamento(
                this,
                motivazione,
                giornoAttuale,
                oraAttuale,
                giornoProposto,
                oraProposta
        );
    }


    // Metodo toString per la rappresentazione testuale dell'oggetto
    @Override
    public String toString() {

        return getNome() + " " + getCognome();
    }
}