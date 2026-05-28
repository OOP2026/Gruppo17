package gui;

import javax.swing.*;

public class ResponsabileHomeFrame extends JFrame {

    public ResponsabileHomeFrame() {
        setTitle("Responsabile Orari - Dashboard");
        setSize(1150, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crea Lezione", new CreaLezionePanel());
        tabbedPane.addTab("Gestione Aule", new GestioneAulePanel());
        tabbedPane.addTab("Richieste di Spostamento", new RichiestePanel());
        tabbedPane.addTab("Orario Globale", new VisualizzaOrarioPanel());

        setContentPane(tabbedPane);
    }
}