package model.utente;

import model.didattica.AnnoCorso;

public class Studente extends Utente {

    private String matricola;
    private AnnoCorso annoCorso;

    // Costruttore
    public Studente(String nome,
                    String cognome,
                    String email,
                    String login,
                    String password,
                    String matricola,
                    AnnoCorso annoCorso) {

        super(nome, cognome, email, login, password, UserRole.STUDENT);

        this.matricola = matricola;
        this.annoCorso = annoCorso;
    }

    // Override del metodo astratto
    @Override
    public void visualizzaOrario() {

        System.out.println("Visualizzazione orario dello studente: "
                + getNome() + " " + getCognome());

        System.out.println("Anno di corso: " + annoCorso);
    }

    // Getter
    public String getMatricola() {
        return matricola;
    }

    public AnnoCorso getAnnoCorso() {
        return annoCorso;
    }

    // Setter
    public void setAnnoCorso(AnnoCorso annoCorso) {

        if(annoCorso != null) {
            this.annoCorso = annoCorso;
        }
    }

    // toString
    @Override
    public String toString() {

        return "Studente{" +
                "nome='" + getNome() + '\'' +
                ", cognome='" + getCognome() + '\'' +
                ", matricola='" + matricola + '\'' +
                ", annoCorso=" + annoCorso +
                '}';
    }
}