package controller;

import gui.*;
import model.didattica.*;
import model.logistica.Aula;
import model.richiesta.RichiestaSpostamento;
import model.richiesta.StatoRichiesta;
import model.utente.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private final Orario orario;
    private final Map<String, Utente> utenti;
    private Utente utenteCorrente;

    private LoginFrame loginFrame;
    private RegisterFrame registerFrame;


    public Controller() {

        this.orario = new Orario();
        this.utenti = new HashMap<>();

        inizializzaDatiPredefiniti();
    }


    public void startApplication() {

        SwingUtilities.invokeLater(() -> {
            initializeViews();
            showLoginFrame();
        });
    }


    private void initializeViews() {

        loginFrame = new LoginFrame(this);
        registerFrame = new RegisterFrame(this);
    }


    private void inizializzaDatiPredefiniti() {

        ResponsabileOrari admin = new ResponsabileOrari("Mario", "Rossi", "admin@unina.it", "admin", "admin");
        utenti.put("admin", admin);

        Docente docente = new Docente("Luca", "Bianchi", "luca.bianchi@unina.it", "docente", "docente");
        utenti.put("docente", docente);

        orario.aggiungiAula(new Aula("Aula A1"));
        orario.aggiungiAula(new Aula("Aula T2"));

        Insegnamento insegnamento = new Insegnamento("Programmazione Oggetti", 6, AnnoCorso.PRIMO, docente);
        orario.aggiungiInsegnamento(insegnamento);
    }


    public void showLoginFrame() {

        SwingUtilities.invokeLater(() -> {
            if (registerFrame != null) {
                registerFrame.setVisible(false);
            }
            if (loginFrame != null) {
                loginFrame.clearFields();
                loginFrame.setLocationRelativeTo(null);
                loginFrame.setVisible(true);
            }
        });
    }


    public void showRegisterFrame() {

        SwingUtilities.invokeLater(() -> {
            if (loginFrame != null) {
                loginFrame.setVisible(false);
            }
            if (registerFrame != null) {
                registerFrame.clearFields();
                registerFrame.setLocationRelativeTo(null);
                registerFrame.setVisible(true);
            }
        });
    }


    public void openHomeByRole() {

        UserRole role = getTargetHomeRole();

        if (role == UserRole.STUDENT && utenteCorrente instanceof Studente) {

            StudenteHomeFrame studentFrame = new StudenteHomeFrame(this, (Studente) utenteCorrente);
            studentFrame.setVisible(true);

        } else if (role == UserRole.TEACHER && utenteCorrente instanceof Docente) {

            DocenteHomeFrame docenteFrame = new DocenteHomeFrame(this, (Docente) utenteCorrente);
            docenteFrame.setVisible(true);

        } else if (role == UserRole.ADMIN && utenteCorrente instanceof ResponsabileOrari) {

            ResponsabileHomeFrame adminFrame = new ResponsabileHomeFrame(this, (ResponsabileOrari) utenteCorrente);
            adminFrame.setVisible(true);
        }
    }


    public boolean login(String login, String password) {

        if (login == null || password == null) {
            return false;
        }

        Utente utente = utenti.get(login.toLowerCase().trim());

        if (utente != null && utente.getPassword().equals(password)) {

            utenteCorrente = utente;
            return true;
        }

        return false;
    }


    public boolean register(
            String name,
            String surname,
            String email,
            String login,
            String password,
            UserRole role,
            String studentId,
            AnnoCorso annoCorso
    ) {

        if (name == null || surname == null || email == null || login == null || password == null || role == null) {
            return false;
        }

        String sanitizedLogin = login.toLowerCase().trim();

        if (utenti.containsKey(sanitizedLogin)) {
            return false;
        }

        Utente nuovoUtente = null;

        if (role == UserRole.STUDENT) {

            nuovoUtente = new Studente(name, surname, email, sanitizedLogin, password, studentId, annoCorso);

        } else if (role == UserRole.TEACHER) {

            nuovoUtente = new Docente(name, surname, email, sanitizedLogin, password);

        } else if (role == UserRole.ADMIN) {

            nuovoUtente = new ResponsabileOrari(name, surname, email, sanitizedLogin, password);
        }

        if (nuovoUtente != null) {

            utenti.put(sanitizedLogin, nuovoUtente);
            return true;
        }

        return false;
    }


    public void logout() {

        utenteCorrente = null;
        showLoginFrame();
    }


    public Utente getUtenteCorrente() {

        return utenteCorrente;
    }


    public UserRole getTargetHomeRole() {

        if (utenteCorrente == null) {
            return null;
        }

        return utenteCorrente.getRole();
    }


    public String getNomeUtenteCorrente() {

        if (utenteCorrente == null) {
            return "";
        }

        return utenteCorrente.getNome() + " " + utenteCorrente.getCognome();
    }


    public boolean aggiungiAula(String nomeAula) {

        if (nomeAula == null || nomeAula.trim().isEmpty()) {
            return false;
        }

        return aggiungiAula(new Aula(nomeAula.trim()));
    }


    public boolean aggiungiAula(Aula aula) {

        if (aula == null) {
            return false;
        }

        boolean esiste = orario.getAule().stream()
                .anyMatch(a -> a.getNome().equalsIgnoreCase(aula.getNome()));

        if (esiste) {
            return false;
        }

        orario.aggiungiAula(aula);
        return true;
    }


    public boolean aggiungereAula(Aula aula) {

        return aggiungiAula(aula);
    }


    public List<Aula> getTutteAule() {

        return orario.getAule();
    }


    public Aula cercaAula(String nomeAula) {

        if (nomeAula == null) {
            return null;
        }

        return orario.getAule().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nomeAula.trim()))
                .findFirst()
                .orElse(null);
    }


    public void aggiungiInsegnamento(String nome, int cfu, AnnoCorso annoCorso, Docente docenteTitolare) {

        Insegnamento insegnamento = new Insegnamento(nome, cfu, annoCorso, docenteTitolare);
        orario.aggiungiInsegnamento(insegnamento);
    }


    public List<Insegnamento> getInsegnamenti() {

        return orario.getInsegnamenti();
    }


    public Insegnamento cercaInsegnamento(String nome) {

        if (nome == null) {
            return null;
        }

        return orario.getInsegnamenti().stream()
                .filter(i -> i.getNomeInsegnamento().equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElse(null);
    }


    public boolean creaLezione(Insegnamento insegnamento, Giorno giorno, OraInizio oraInizio, Aula aula) {

        if (insegnamento == null || giorno == null || oraInizio == null || aula == null) {
            return false;
        }

        Lezione lezione = new Lezione(insegnamento, giorno, oraInizio, aula);
        return orario.aggiungiLezione(lezione);
    }


    public boolean eliminaLezione(Lezione lezione) {

        return orario.rimuoviLezione(lezione);
    }


    public List<Lezione> getLezioni() {

        return orario.getLezioni();
    }


    public List<Lezione> getLezioniForUser(Utente utente) {

        List<Lezione> filtrate = new ArrayList<>();

        if (orario == null || orario.getLezioni() == null) {
            return filtrate;
        }

        if (utente instanceof Studente) {

            Studente studente = (Studente) utente;

            for (Lezione l : orario.getLezioni()) {
                if (l.getInsegnamento() != null && l.getInsegnamento().getAnnoCorso() == studente.getAnnoCorso()) {
                    filtrate.add(l);
                }
            }

        } else if (utente instanceof Docente) {

            Docente docente = (Docente) utente;

            for (Lezione l : orario.getLezioni()) {
                if (l.getInsegnamento() != null && l.getInsegnamento().getDocenteTitolare() != null) {

                    String loginDocenteInLezione = l.getInsegnamento().getDocenteTitolare().getLogin();

                    if (loginDocenteInLezione != null && loginDocenteInLezione.equalsIgnoreCase(docente.getLogin())) {
                        filtrate.add(l);
                    }
                }
            }

        } else if (utente instanceof ResponsabileOrari) {

            filtrate.addAll(orario.getLezioni());
        }

        return filtrate;
    }


    public boolean inviaRichiestaSpostamento(RichiestaSpostamento richiesta) {

        if (richiesta == null) {
            return false;
        }

        orario.aggiungiRichiesta(richiesta);
        return true;
    }


    public List<RichiestaSpostamento> getRichieste() {

        return orario.getRichieste();
    }


    public boolean approvaRichiesta(RichiestaSpostamento richiesta) {

        if (!(utenteCorrente instanceof ResponsabileOrari) || richiesta == null) {
            return false;
        }

        ResponsabileOrari responsabile = (ResponsabileOrari) utenteCorrente;
        Lezione lezioneDaSpostare = null;

        for (Lezione l : orario.getLezioni()) {

            if (l.getInsegnamento().getDocenteTitolare().equals(richiesta.getDocente())
                    && l.getGiornoSettimana() == richiesta.getGiornoAttuale()
                    && l.getOraInizio() == richiesta.getOraInizioAttuale()) {

                lezioneDaSpostare = l;
                break;
            }
        }

        if (lezioneDaSpostare == null) {
            return false;
        }

        Lezione nuovaLezione = new Lezione(
                lezioneDaSpostare.getInsegnamento(),
                richiesta.getGiornoProposto(),
                richiesta.getOraInizioProposta(),
                lezioneDaSpostare.getAula()
        );

        orario.rimuoviLezione(lezioneDaSpostare);

        if (orario.aggiungiLezione(nuovaLezione)) {

            responsabile.approvaRichiesta(richiesta);
            return true;

        } else {

            orario.aggiungiLezione(lezioneDaSpostare);
            return false;
        }
    }


    public void rifiutaRichiesta(RichiestaSpostamento richiesta) {

        if (!(utenteCorrente instanceof ResponsabileOrari) || richiesta == null) {
            return;
        }

        ResponsabileOrari responsabile = (ResponsabileOrari) utenteCorrente;
        responsabile.rifiutaRichiesta(richiesta);
    }


    public Object[][] getDatiTabellaOrario() {

        return buildTabellaLezioni(orario.getLezioni());
    }


    public Object[][] getDatiTabellaOrarioStudente() {

        if (!(utenteCorrente instanceof Studente)) {
            return new Object[0][0];
        }

        Studente studente = (Studente) utenteCorrente;
        List<Lezione> filtrate = new ArrayList<>();

        for (Lezione l : orario.getLezioni()) {
            if (l.getInsegnamento().getAnnoCorso() == studente.getAnnoCorso()) {
                filtrate.add(l);
            }
        }

        return buildTabellaLezioni(filtrate);
    }


    public Object[][] getDatiTabellaOrarioDocente() {

        if (!(utenteCorrente instanceof Docente)) {
            return new Object[0][0];
        }

        Docente docente = (Docente) utenteCorrente;
        List<Lezione> filtrate = new ArrayList<>();

        for (Lezione l : orario.getLezioni()) {
            if (l.getInsegnamento().getDocenteTitolare().equals(docente)) {
                filtrate.add(l);
            }
        }

        return buildTabellaLezioni(filtrate);
    }


    private Object[][] buildTabellaLezioni(List<Lezione> lezioni) {

        Object[][] dati = new Object[lezioni.size()][6];

        for (int i = 0; i < lezioni.size(); i++) {

            Lezione l = lezioni.get(i);

            dati[i][0] = l.getInsegnamento().getNomeInsegnamento();

            dati[i][1] = l.getInsegnamento().getDocenteTitolare().getCognome() + " " +
                    l.getInsegnamento().getDocenteTitolare().getNome();

            dati[i][2] = l.getGiornoSettimana();
            dati[i][3] = l.getOraInizio();
            dati[i][4] = l.getOraFine();
            dati[i][5] = l.getAula().getNome();
        }

        return dati;
    }


    public Object[][] getDatiTabellaRichieste() {

        List<RichiestaSpostamento> richieste = orario.getRichieste();
        Object[][] dati = new Object[richieste.size()][6];

        for (int i = 0; i < richieste.size(); i++) {

            RichiestaSpostamento r = richieste.get(i);

            dati[i][0] = r.getDocente() != null ? r.getDocente().getCognome() + " " +
                    r.getDocente().getNome() : "Sconosciuto";

            dati[i][1] = r.getMotivazione();
            dati[i][2] = r.getStato();
            dati[i][3] = r.getGiornoAttuale() + " (" + r.getOraInizioAttuale() + ")";
            dati[i][4] = r.getGiornoProposto() + " (" + r.getOraInizioProposta() + ")";
            dati[i][5] = r.getDataRichiesta();
        }

        return dati;
    }
}