package controller;

import gui.*;
import model.didattica.AnnoCorso;
import model.didattica.Giorno;
import model.didattica.Insegnamento;
import model.didattica.Lezione;
import model.didattica.Orario;
import model.logistica.Aula;
import model.richiesta.RichiestaSpostamento;
import model.richiesta.StatoRichiesta;
import model.utente.Docente;
import model.utente.ResponsabileOrari;
import model.utente.Studente;
import model.utente.UserRole;
import model.utente.Utente;

import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    // =====================================================
    // MODEL
    // =====================================================

    private final Orario orario;

    // =====================================================
    // USERS
    // =====================================================

    private final Map<String, Utente> utenti;

    // =====================================================
    // SESSION
    // =====================================================

    private Utente utenteCorrente;

    // =====================================================
    // VIEWS
    // =====================================================

    private LoginFrame loginFrame;

    private RegisterFrame registerFrame;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Controller() {
        orario = new Orario();
        utenti = new HashMap<>();
    }

    // =====================================================
    // START APPLICATION
    // =====================================================

    public void startApplication() {
        SwingUtilities.invokeLater(() -> {
            initializeViews();
            showLoginFrame();
        });
    }

    // =====================================================
    // INITIALIZE VIEWS
    // =====================================================

    private void initializeViews() {
        loginFrame = new LoginFrame(this);
        registerFrame = new RegisterFrame(this);
    }

    // =====================================================
    // SHOW LOGIN FRAME
    // =====================================================

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

    // =====================================================
    // SHOW REGISTER FRAME
    // =====================================================

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

    // =====================================================
    // OPEN HOME BY ROLE
    // =====================================================

    public void openHomeByRole() {
        UserRole role = getTargetHomeRole();

        if (role == UserRole.STUDENT) {
            System.out.println("Opening Student Home Screen...");

        } else if (role == UserRole.TEACHER) {
            System.out.println("Opening Teacher Home Screen...");

        } else if (role == UserRole.ADMIN) {
            System.out.println("Opening Administrator Home Screen...");

        }
    }

    // =====================================================
    // FIND USER
    // =====================================================

    private Utente findUtenteByLogin(String login) {
        if (login == null) {
            return null;
        }
        return utenti.get(login.toLowerCase().trim());
    }

    // =====================================================
    // LOGIN EXISTS
    // =====================================================

    private boolean loginExists(String login) {
        return findUtenteByLogin(login) != null;
    }

    // =====================================================
    // LOGIN
    // =====================================================

    public boolean login(String login, String password) {
        if (login == null || password == null) {
            return false;
        }

        Utente utente = findUtenteByLogin(login);


        if (utente != null && utente.login(login.toLowerCase().trim(), password)) {
            utenteCorrente = utente;
            return true;
        }

        return false;
    }

    // =====================================================
    // REGISTER
    // =====================================================

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
        // Validation
        if (name == null || surname == null || email == null ||
                login == null || password == null || role == null) {
            return false;
        }

        String sanitizedLogin = login.toLowerCase().trim();

        // Login Check
        if (loginExists(sanitizedLogin)) {
            return false;
        }

        Utente nuovoUtente = null;

        // STUDENT
        if (role == UserRole.STUDENT) {

            nuovoUtente = new Studente(
                    name,
                    surname,
                    email,
                    sanitizedLogin,
                    password,
                    studentId,
                    annoCorso
            );
        }
        // TEACHER
        else if (role == UserRole.TEACHER) {
            nuovoUtente = new Docente(
                    name,
                    surname,
                    email,
                    sanitizedLogin,
                    password
            );
        }
        // ADMIN
        else if (role == UserRole.ADMIN) {
            nuovoUtente = new ResponsabileOrari(
                    name,
                    surname,
                    email,
                    sanitizedLogin,
                    password
            );
        }

        // Save User
        if (nuovoUtente != null) {
            utenti.put(sanitizedLogin, nuovoUtente);
            return true;
        }

        return false;
    }

    // =====================================================
    // LOGOUT
    // =====================================================

    public void logout() {
        utenteCorrente = null;
        showLoginFrame();
    }

    // =====================================================
    // CURRENT USER
    // =====================================================

    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    // =====================================================
    // SESSION CHECK
    // =====================================================

    public boolean isLogged() {
        return utenteCorrente != null;
    }

    // =====================================================
    // CURRENT USER ROLE
    // =====================================================

    public UserRole getTargetHomeRole() {
        if (utenteCorrente == null) {
            return null; // ОНОВЛЕНО: Повертаємо null, бо в енумі немає значення GUEST
        }
        return utenteCorrente.getRole();
    }

    // =====================================================
    // CURRENT USER NAME
    // =====================================================

    public String getNomeUtenteCorrente() {
        if (utenteCorrente == null) {
            return "";
        }
        return utenteCorrente.getNome() + " " + utenteCorrente.getCognome();
    }

    // =====================================================
    // CLASSROOMS
    // =====================================================

    public void aggiungiAula(String nomeAula) {
        if (nomeAula == null || nomeAula.isBlank()) {
            return;
        }
        Aula aula = new Aula(nomeAula.trim());
        orario.aggiungiAula(aula);
    }

    public List<Aula> getAule() {
        return orario.getAule();
    }

    public Aula cercaAula(String nomeAula) {
        if (nomeAula == null) {
            return null;
        }
        return orario.getAule()
                .stream()
                .filter(a -> a.getNomeAula().equalsIgnoreCase(nomeAula.trim()))
                .findFirst()
                .orElse(null);
    }

    // =====================================================
    // SUBJECTS
    // =====================================================

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
        return orario.getInsegnamenti()
                .stream()
                .filter(i -> i.getNomeInsegnamento().equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElse(null);
    }

    // =====================================================
    // CREATE LESSON
    // =====================================================

    public boolean creaLezione(Insegnamento insegnamento, Giorno giorno, LocalTime oraInizio, LocalTime oraFine, Aula aula) {
        if (insegnamento == null || giorno == null || oraInizio == null || oraFine == null || aula == null) {
            return false;
        }

        if (oraInizio.isAfter(oraFine) || oraInizio.equals(oraFine)) {
            return false;
        }

        if (checkConflittoAula(giorno, oraInizio, oraFine, aula)) {
            return false;
        }

        if (checkConflittoDocente(insegnamento, giorno, oraInizio, oraFine)) {
            return false;
        }

        Lezione lezione = new Lezione(insegnamento, giorno, oraInizio, oraFine, aula);
        return orario.aggiungiLezione(lezione);
    }

    // =====================================================
    // DELETE LESSON
    // =====================================================

    public boolean eliminaLezione(Lezione lezione) {
        return orario.rimuoviLezione(lezione);
    }

    // =====================================================
    // GET LESSONS
    // =====================================================

    public List<Lezione> getLezioni() {
        return orario.getLezioni();
    }

    // =====================================================
    // OVERLAP
    // =====================================================

    private boolean isOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return start1.isBefore(end2) && end1.isAfter(start2);
    }

    // =====================================================
    // CLASSROOM CONFLICT
    // =====================================================

    public boolean checkConflittoAula(Giorno giorno, LocalTime oraInizio, LocalTime oraFine, Aula aula) {
        for (Lezione l : orario.getLezioni()) {
            boolean stessoGiorno = l.getGiornoSettimana() == giorno;
            boolean stessaAula = l.getAula().equals(aula);
            boolean overlap = isOverlap(oraInizio, oraFine, l.getOraInizio(), l.getOraFine());

            if (stessoGiorno && stessaAula && overlap) {
                return true;
            }
        }
        return false;
    }

    // =====================================================
    // TEACHER CONFLICT
    // =====================================================

    public boolean checkConflittoDocente(Insegnamento insegnamento, Giorno giorno, LocalTime oraInizio, LocalTime oraFine) {
        Docente docenteNuovo = insegnamento.getDocenteTitolare();

        for (Lezione l : orario.getLezioni()) {
            boolean stessoGiorno = l.getGiornoSettimana() == giorno;
            boolean stessoDocente = l.getInsegnamento().getDocenteTitolare().equals(docenteNuovo);
            boolean overlap = isOverlap(oraInizio, oraFine, l.getOraInizio(), l.getOraFine());

            if (stessoGiorno && stessoDocente && overlap) {
                return true;
            }
        }
        return false;
    }

    // =====================================================
    // SEND REQUEST
    // =====================================================

    public void inviaRichiesta(String motivazione, Giorno giornoAttuale, LocalTime oraInizioAttuale, LocalTime oraFineAttuale, Giorno giornoProposto, LocalTime oraInizioProposta, LocalTime oraFineProposta) {
        if (!(utenteCorrente instanceof Docente docente)) {
            return;
        }

        RichiestaSpostamento richiesta = new RichiestaSpostamento(
                docente,
                motivazione,
                giornoAttuale,
                oraInizioAttuale,
                oraFineAttuale,
                giornoProposto,
                oraInizioProposta,
                oraFineProposta
        );

        orario.aggiungiRichiesta(richiesta);
    }

    // =====================================================
    // GET REQUESTS
    // =====================================================

    public List<RichiestaSpostamento> getRichieste() {
        return orario.getRichieste();
    }

    // =====================================================
    // APPROVE REQUEST
    // =====================================================

    public boolean approvaRichiesta(RichiestaSpostamento richiesta) {
        if (!(utenteCorrente instanceof ResponsabileOrari) || richiesta == null) {
            return false;
        }

        Lezione lezioneDaSpostare = null;

        for (Lezione l : orario.getLezioni()) {
            boolean stessoDocente = l.getInsegnamento().getDocenteTitolare().equals(richiesta.getDocente());
            boolean stessoGiorno = l.getGiornoSettimana() == richiesta.getGiornoAttuale();
            boolean stessaOra = l.getOraInizio().equals(richiesta.getOraInizioAttuale());

            if (stessoDocente && stessoGiorno && stessaOra) {
                lezioneDaSpostare = l;
                break;
            }
        }

        if (lezioneDaSpostare == null) {
            return false;
        }

        if (checkConflittoAula(
                richiesta.getGiornoProposto(),
                richiesta.getOraInizioProposta(),
                richiesta.getOraFineProposta(),
                lezioneDaSpostare.getAula()
        )) {
            return false;
        }

        if (checkConflittoDocente(
                lezioneDaSpostare.getInsegnamento(),
                richiesta.getGiornoProposto(),
                richiesta.getOraInizioProposta(),
                richiesta.getOraFineProposta()
        )) {
            return false;
        }

        orario.rimuoviLezione(lezioneDaSpostare);

        Lezione nuovaLezione = new Lezione(
                lezioneDaSpostare.getInsegnamento(),
                richiesta.getGiornoProposto(),
                richiesta.getOraInizioProposta(),
                richiesta.getOraFineProposta(),
                lezioneDaSpostare.getAula()
        );

        orario.aggiungiLezione(nuovaLezione);
        richiesta.setStato(StatoRichiesta.APPROVATA);

        return true;
    }

    // =====================================================
    // REJECT REQUEST
    // =====================================================

    public void rifiutaRichiesta(RichiestaSpostamento richiesta) {
        if (!(utenteCorrente instanceof ResponsabileOrari) || richiesta == null) {
            return;
        }
        richiesta.setStato(StatoRichiesta.RIFIUTATA);
    }

    // =====================================================
    // FULL TIMETABLE
    // =====================================================

    public Object[][] getDatiTabellaOrario() {
        return buildTabellaLezioni(orario.getLezioni());
    }

    // =====================================================
    // STUDENT TIMETABLE
    // =====================================================

    public Object[][] getDatiTabellaOrarioStudente() {
        if (!(utenteCorrente instanceof Studente studente)) {
            return null;
        }

        List<Lezione> filtrate = new ArrayList<>();

        for (Lezione l : orario.getLezioni()) {
            if (l.getInsegnamento().getAnnoCorso() == studente.getAnnoCorso()) {
                filtrate.add(l);
            }
        }

        return buildTabellaLezioni(filtrate);
    }

    // =====================================================
    // TEACHER TIMETABLE
    // =====================================================

    public Object[][] getDatiTabellaOrarioDocente() {
        if (!(utenteCorrente instanceof Docente docente)) {
            return null;
        }

        List<Lezione> filtrate = new ArrayList<>();

        for (Lezione l : orario.getLezioni()) {
            if (l.getInsegnamento().getDocenteTitolare().equals(docente)) {
                filtrate.add(l);
            }
        }

        return buildTabellaLezioni(filtrate);
    }

    // =====================================================
    // BUILD LESSON TABLE
    // =====================================================

    private Object[][] buildTabellaLezioni(List<Lezione> lezioni) {
        Object[][] dati = new Object[lezioni.size()][6];

        for (int i = 0; i < lezioni.size(); i++) {
            Lezione l = lezioni.get(i);

            dati[i][0] = l.getInsegnamento().getNomeInsegnamento();
            dati[i][1] = l.getInsegnamento().getDocenteTitolare().getCognome() + " " + l.getInsegnamento().getDocenteTitolare().getNome();
            dati[i][2] = l.getGiornoSettimana();
            dati[i][3] = l.getOraInizio();
            dati[i][4] = l.getOraFine();
            dati[i][5] = l.getAula().getNomeAula();
        }

        return dati;
    }

    // =====================================================
    // REQUEST TABLE
    // =====================================================

    public Object[][] getDatiTabellaRichieste() {
        List<RichiestaSpostamento> richieste = orario.getRichieste();
        Object[][] dati = new Object[richieste.size()][6];

        for (int i = 0; i < richieste.size(); i++) {
            RichiestaSpostamento r = richieste.get(i);

            dati[i][0] = r.getDocente().getCognome() + " " + r.getDocente().getNome();
            dati[i][1] = r.getMotivazione();
            dati[i][2] = r.getStato();
            dati[i][3] = r.getGiornoAttuale() + " (" + r.getOraInizioAttuale() + ")";
            dati[i][4] = r.getGiornoProposto() + " (" + r.getOraInizioProposta() + ")";
            dati[i][5] = r.getDataRichiesta();
        }

        return dati;
    }
}