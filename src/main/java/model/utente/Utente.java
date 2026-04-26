package model.utente;

public abstract class Utente {

    private String nome;
    private String cognome;
    private String email;
    private String login;
    private String password;

    public Utente(String nome, String cognome, String email, String login, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public void registrazione() {
        System.out.println("Utente registrato correttamente.");
    }

    public abstract void visualizzaOrario();

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
