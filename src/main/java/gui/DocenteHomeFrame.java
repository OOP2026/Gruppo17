package gui;

import model.Docente;
import javax.swing.*;

public class DocenteHomeFrame extends JFrame {

    private final Docente docente;

    public DocenteHomeFrame(Docente docente) {
        this.docente = docente;
        setTitle("Docente - " + docente.getNome() + " " + docente.getCognome());
        setSize(1150, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Il Mio Orario", new VisualizzaOrarioFrame());
        tabbedPane.addTab("Richiesta Spostamento", new RichiestaSpostamentoPanel(docente));
        tabbedPane.addTab("Profilo", new ProfiloPanel(docente));

        setContentPane(tabbedPane);
    }
}