/*package model.test;

import model.didattica.*;
import model.logistica.Aula;
import model.richiesta.RichiestaSpostamento;
import model.utente.*;

import java.time.LocalTime;

public class MainTest {

    public static void main(String[] args) {

        System.out.println("===== INIZIO TEST SISTEMA =====");

        // Creazione aula
        Aula aula = new Aula("Aula Magna");

        System.out.println(aula);

        // Creazione lezione
        Lezione lezione = new Lezione(
                Giorno.LUNEDI,
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                aula
        );

        System.out.println(lezione);

        // Creazione orario
        Orario orario = new Orario();

        orario.aggiungiLezione(lezione);

        System.out.println("Numero lezioni: "
                + orario.numeroLezioni());

        // Creazione studente
        Studente studente = new Studente(
                "Mario",
                "Rossi",
                "mario@email.it",
                "mrossi",
                "1234",
                "S001",
                AnnoCorso.SECONDO
        );

        // Registrazione studente
        studente.registrazione();

        // Login studente
        boolean loginStudente =
                studente.login("mrossi", "1234");

        System.out.println(
                "Login studente: " + loginStudente
        );

        // Visualizzazione orario studente
        studente.visualizzaOrario();

        System.out.println(studente);

        // Creazione docente
        Docente docente = new Docente(
                "Luca",
                "Bianchi",
                "luca@email.it",
                "lbianchi",
                "abcd"
        );

        docente.visualizzaOrario();

        System.out.println(docente);

        // Creazione responsabile
        ResponsabileOrari responsabile =
                new ResponsabileOrari(
                        "Anna",
                        "Verdi",
                        "anna@email.it",
                        "averdi",
                        "pass"
                );

        System.out.println(responsabile);

        // Creazione richiesta spostamento
        RichiestaSpostamento richiesta =
                docente.inviaRichiesta(
                        "Cambio orario lezione",
                        Giorno.LUNEDI,
                        LocalTime.of(9, 0),
                        LocalTime.of(11, 0),
                        Giorno.MARTEDI,
                        LocalTime.of(14, 0),
                        LocalTime.of(16, 0)
                );

        // Stato iniziale
        System.out.println(
                "Stato richiesta: "
                        + richiesta.getStato()
        );

        // Approvazione richiesta
        responsabile.approvaRichiesta(richiesta);

        // Stato dopo approvazione
        System.out.println(
                "Stato dopo approvazione: "
                        + richiesta.getStato()
        );

        // Aggiornamento orario
        responsabile.aggiornaOrario(orario);

        // Visualizzazione orario completo
        System.out.println(orario);

        System.out.println(
                "Lezioni presenti nell'orario: "
                        + orario.numeroLezioni()
        );

        System.out.println("===== TEST COMPLETO OK ✔ =====");
    }
}*/