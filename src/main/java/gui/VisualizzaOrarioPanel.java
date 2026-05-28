package gui;

import controller.Controller;
import model.Lezione;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VisualizzaOrarioPanel extends JPanel {

    private JTable tableOrario;
    private DefaultTableModel tableModel;

    public VisualizzaOrarioPanel() {
        setLayout(new BorderLayout());
        String[] colonne = {"Giorno", "Ora Inizio", "Ora Fine", "Insegnamento", "Aula", "Docente"};
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableOrario = new JTable(tableModel);
        add(new JScrollPane(tableOrario), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnAggiorna = new JButton("Aggiorna");
        btnPanel.add(btnAggiorna);
        btnAggiorna.addActionListener(e -> caricaOrario());
        add(btnPanel, BorderLayout.SOUTH);

        caricaOrario();
    }

    private void caricaOrario() {
        tableModel.setRowCount(0);
        for (Lezione l : Controller.getInstance().getTutteLezioni()) {
            tableModel.addRow(new Object[]{
                    l.getGiornoSettimana(),
                    l.getOraInizio(),
                    l.getOraFine(),
                    l.getInsegnamento().getNome(),
                    l.getAula().getNome(),
                    l.getInsegnamento().getDocenteTitolare() != null ?
                            l.getInsegnamento().getDocenteTitolare().getNome() + " " +
                                    l.getInsegnamento().getDocenteTitolare().getCognome() : "N/D"
            });
        }
    }
}