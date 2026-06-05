/*package model.test;

import model.didattica.*;
import model.logistica.Aula;
import model.richiesta.RichiestaSpostamento;
import model.utente.*;

public class MainTest {

    public static void main(String[] args) {

        System.out.println("===== INIZIO TEST SISTEMA =====");


        // 1. Creazione delle aule logistiche
        Aula aulaMagna = new Aula("Aula Magna");

        System.out.println("Aula creata: " + aulaMagna);


        // 2. Creazione degli utenti (Docente e Responsabile)
        Docente docente = new Docente(
                "Luca",
                "Bianchi",
                "luca@email.it",
                "lbianchi",
                "abcd"
        );

        System.out.println("Docente creato: " + docente);

        ResponsabileOrari responsabile = new ResponsabileOrari(
                "Anna",
                "Verdi",
                "anna@email.it",
                "averdi",
                "pass"
        );

        System.out.println("Responsabile creato: " + resposabile);


        // 3. Creazione dell'insegnamento attivo (legato al docente titolare)
        Insegnamento programmazione = new Insegnamento(
                "Programmazione Java",
                6,
                AnnoCorso.PRIMO,
                docente
        );

        System.out.println("Insegnamento creato: " + programmazione);


        // 4. Creazione di una lezione accademica
        Lezione lezione1 = new Lezione(
                programmazione,
                Giorno.LUNEDI,
                OraInizio.PAR_1,
                aulaMagna
        );

        System.out.println("Lezione creata: " + lezione1);


        // 5. Inizializzazione del contenitore Orario generale e gestione conflitti
        Orario orario = new Orario();

        boolean aggiunta1 = orario.aggiungiLezione(lezione1);

        System.out.println("Prima lezione aggiunta con successo? " + aggiunta1);
        System.out.println("Numero lezioni nell'orario: " + orario.numeroLezioni());


        // Test tentativo inserimento lezione in conflitto (Stessa aula, stesso giorno, stessa ora)
        Lezione lezioneConflitto = new Lezione(
                programmazione,
                Giorno.LUNEDI,
                OraInizio.PAR_1,
                aulaMagna
        );

        boolean aggiuntaConflitto = orario.aggiungiLezione(lezioneConflitto);

        System.out.println("Lezione in conflitto aggiunta? (Ci aspettiamo false): " + aggiuntaConflitto);


        // 6. Creazione e gestione dello Studente
        String passwordStudente = "1234";

        System.out.println("Password originale per il controllo di login: " + passwordStudente);

        Studente studente = new Studente(
                "Mario",
                "Rossi",
                "mario@email.it",
                "mrossi",
                passwordStudente,
                "S001",
                AnnoCorso.SECONDO
        );

        System.out.println("Studente creato: " + studente);


        // 7. Gestione flussi Richieste di Spostamento (Inviate dal docente)
        RichiestaSpostamento richiesta = docente.inviaRichiesta(
                "Cambio orario per motivi accademici",
                Giorno.LUNEDI,
                OraInizio.PAR_1,
                Giorno.MARTEDI,
                OraInizio.PAR_3
        );

        orario.aggiungiRichiesta(richiesta);

        System.out.println("Richiesta registrata nel sistema: " + richiesta);
        System.out.println("Stato iniziale della richiesta: " + richiesta.getStato());


        // 8. Approvazione della richiesta da parte del Responsabile degli orari
        responsabile.approvaRichiesta(richiesta);

        System.out.println("Stato dopo l'approvazione del responsabile: " + richiesta.getStato());


        // 9. Stampa dello stato finale dell'orario complessivo
        System.out.println("\n--- STATO FINALE DELL'ORARIO ---");
        System.out.println(orario);


        System.out.println("\n===== TEST COMPLETO OK ✔ =====");
    }
}*/