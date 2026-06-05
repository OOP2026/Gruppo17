package model.utente;

import model.didattica.AnnoCorso;

public class Studente extends Utente {

    private String matricola;
    private AnnoCorso annoCorso;


    // Costruttore principale per lo studente
    public Studente(
            String nome,
            String cognome,
            String email,
            String login,
            String password,
            String matricola,
            AnnoCorso annoCorso
    ) {

        super(nome, cognome, email, login, password, UserRole.STUDENT);

        this.matricola = matricola;
        this.annoCorso = annoCorso;
    }


    // Getter per la matricola
    public String getMatricola() {

        return matricola;
    }


    // Setter per la matricola
    public void setMatricola(String matricola) {

        this.matricola = matricola;
    }


    // Getter per l'anno di corso
    public AnnoCorso getAnnoCorso() {

        return annoCorso;
    }


    // Setter per l'anno di corso con validazione
    public void setAnnoCorso(AnnoCorso annoCorso) {

        if (annoCorso != null) {
            this.annoCorso = annoCorso;
        }
    }


    // Metodo toString per la rappresentazione testuale dello studente
    @Override
    public String toString() {

        return getNome() + " " + getCognome() + " (Matr: " + matricola + ")";
    }
}