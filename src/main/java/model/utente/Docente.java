package model.utente;

import model.didattica.Giorno;
import model.richiesta.RichiestaSpostamento;

import java.time.LocalTime;

public class Docente extends Utente {

    // Costruttore principale
    public Docente(String nome,
                   String cognome,
                   String email,
                   String login,
                   String password) {


        super(nome, cognome, email, login, password, UserRole.TEACHER);
    }

    protected Docente(String nome,
                      String cognome,
                      String email,
                      String login,
                      String password,
                      UserRole role) {

        super(nome, cognome, email, login, password, role);
    }

    // Visualizzazione orario
    @Override
    public void visualizzaOrario() {

        System.out.println(
                "Visualizzazione orario del docente: "
                        + getNome()
                        + " "
                        + getCognome()
        );
    }

    // Invio richiesta di spostamento
    public RichiestaSpostamento inviaRichiesta(
            String motivazione,
            Giorno giornoAttuale,
            LocalTime oraInizioAttuale,
            LocalTime oraFineAttuale,
            Giorno giornoProposto,
            LocalTime oraInizioProposta,
            LocalTime oraFineProposta) {

        // Validazione motivazione
        if (motivazione == null
                || motivazione.trim().isEmpty()) {

            System.out.println(
                    "Errore: motivazione non valida."
            );

            return null;
        }

        // Creazione richiesta
        RichiestaSpostamento richiesta =
                new RichiestaSpostamento(
                        motivazione,
                        giornoAttuale,
                        oraInizioAttuale,
                        oraFineAttuale,
                        giornoProposto,
                        oraInizioProposta,
                        oraFineProposta
                );

        System.out.println(
                "Richiesta inviata dal docente: "
                        + getNome()
                        + " "
                        + getCognome()
        );

        return richiesta;
    }

    // toString
    @Override
    public String toString() {

        return "Docente{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", login='" + getLogin() + '\'' +
                '}';
    }
}