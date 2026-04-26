package model.didattica;

public class Insegnamento {

    private String nomeInsegnamento;
    private int cfu;
    private AnnoCorso annoCorso; // ✔ enum

    public Insegnamento(String nomeInsegnamento, int cfu, AnnoCorso annoCorso) {
        this.nomeInsegnamento = nomeInsegnamento;
        this.cfu = cfu;
        this.annoCorso = annoCorso;
    }

    public String getNomeInsegnamento() {
        return nomeInsegnamento;
    }

    public int getCfu() {
        return cfu;
    }

    public AnnoCorso getAnnoCorso() {
        return annoCorso;
    }
}
