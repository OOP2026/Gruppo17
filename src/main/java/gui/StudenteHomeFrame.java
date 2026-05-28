package gui;

import model.Studente;
import javax.swing.*;

public class StudenteHomeFrame extends JFrame {

    private final Studente studente;

    public StudenteHomeFrame(Studente studente) {
        this.studente = studente;
        setTitle("Studente - " + studente.getNome() + " " + studente.getCognome());
        setSize(1150, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Orario Lezioni", new VisualizzaOrarioPanel());
        tabbedPane.addTab("Profilo", new ProfiloPanel(studente));

        setContentPane(tabbedPane);
    }
}