package model.utente;

public abstract class Utente {

    private String nome;
    private String cognome;
    private String email;
    private String login;
    private String password;
    private UserRole role;

    public Utente(String nome, String cognome, String email, String login, String password, UserRole role) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return nome + " " + cognome;
    }
}