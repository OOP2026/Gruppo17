package model.test;

import model.utente.*;
import model.logistica.Aula;
import model.didattica.*;
import model.richiesta.*;

import java.time.LocalTime;

public class MainTest {

    public static void main(String[] args) {

        Aula aula = new Aula("Aula Magna");

        Lezione lezione = new Lezione(
                "Lunedì",
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                aula
        );

        Orario orario = new Orario();
        orario.aggiungiLezione(lezione);

        Studente studente = new Studente(
                "Mario", "Rossi",
                "mario@email.it",
                "mrossi", "1234",
                "S001",
                AnnoCorso.SECONDO
        );

        Docente docente = new Docente(
                "Luca", "Bianchi",
                "luca@email.it",
                "lbianchi", "abcd"
        );

        ResponsabileOrari responsabile = new ResponsabileOrari(
                "Anna", "Verdi",
                "anna@email.it",
                "averdi", "pass"
        );

        RichiestaSpostamento richiesta = docente.inviaRichiesta("Cambio orario lezione");

        System.out.println("Stato richiesta: " + richiesta.getStato());

        responsabile.approvaRichiesta(richiesta);
        System.out.println("Stato dopo approvazione: " + richiesta.getStato());

        studente.visualizzaOrario();
        docente.visualizzaOrario();

        System.out.println("Lezioni in orario: " + orario.getLezioni().size());

        System.out.println("TEST COMPLETO OK ✔");
    }
}
