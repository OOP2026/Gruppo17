package model.utente;
import model.didattica.*;

public class Studente extends Utente {

    private String matricola;
    private AnnoCorso annoCorso;

    public Studente(String nome, String cognome, String email, String login,
                    String password, String matricola, AnnoCorso annoCorso) {
        super(nome, cognome, email, login, password);
        this.matricola = matricola;
        this.annoCorso = annoCorso;
    }

    @Override
    public void visualizzaOrario() {
        System.out.println("Visualizzazione orario dello studente " + getNome());
    }

    public String getMatricola() {
        return matricola;
    }

    public AnnoCorso getAnnoCorso() {
        return annoCorso;
    }

    public void setAnnoCorso(AnnoCorso annoCorso) {
        this.annoCorso = annoCorso;
    }
}