package gui;

import controller.Controller;
import model.RichiestaSpostamentoLezione;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RichiestePanel extends JPanel {

    private JTable tableRichieste;
    private DefaultTableModel tableModel;

    public RichiestePanel() {
        setLayout(new BorderLayout());

        String[] colonne = {"Lezione", "Docente", "Nuovo Giorno", "Nuovo Orario", "Stato"};
        tableModel = new DefaultTableModel(colonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRichieste = new JTable(tableModel);
        tableRichieste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tableRichieste), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnApprova = new JButton("Approva");
        JButton btnRifiuta = new JButton("Rifiuta");
        JButton btnAggiorna = new JButton("Aggiorna");
        btnPanel.add(btnApprova);
        btnPanel.add(btnRifiuta);
        btnPanel.add(btnAggiorna);
        add(btnPanel, BorderLayout.SOUTH);

        btnApprova.addActionListener(e -> approvaRichiesta());
        btnRifiuta.addActionListener(e -> rifiutaRichiesta());
        btnAggiorna.addActionListener(e -> caricaRichieste());

        caricaRichieste();
    }

    private void caricaRichieste() {
        tableModel.setRowCount(0);
        for (RichiestaSpostamentoLezione r : Controller.getInstance().getRichiestePendenti()) {
            tableModel.addRow(new Object[]{
                    r.getLezioneDaSpostare().getInsegnamento().getNome(),
                    r.getLezioneDaSpostare().getInsegnamento().getDocenteTitolare(),
                    r.getNuovoGiorno(),
                    r.getNuovoOrario(),
                    r.getStato()
            });
        }
    }

    private void approvaRichiesta() {
        int rigaSelezionata = tableRichieste.getSelectedRow();
        if (rigaSelezionata < 0) {
            JOptionPane.showMessageDialog(this, "Seleziona una richiesta dalla tabella.", "Nessuna selezione", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<RichiestaSpostamentoLezione> richieste = Controller.getInstance().getRichiestePendenti();
        RichiestaSpostamentoLezione richiesta = richieste.get(rigaSelezionata);
        int conferma = JOptionPane.showConfirmDialog(this, "Approvare la richiesta di spostamento?", "Conferma", JOptionPane.YES_NO_OPTION);
        if (conferma == JOptionPane.YES_OPTION) {
            Controller.getInstance().approvaRichiesta(richiesta);
            JOptionPane.showMessageDialog(this, "Richiesta approvata.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            caricaRichieste();
        }
    }

    private void rifiutaRichiesta() {
        int rigaSelezionata = tableRichieste.getSelectedRow();
        if (rigaSelezionata < 0) {
            JOptionPane.showMessageDialog(this, "Seleziona una richiesta dalla tabella.", "Nessuna selezione", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<RichiestaSpostamentoLezione> richieste = Controller.getInstance().getRichiestePendenti();
        RichiestaSpostamentoLezione richiesta = richieste.get(rigaSelezionata);
        int conferma = JOptionPane.showConfirmDialog(this, "Rifiutare la richiesta di spostamento?", "Conferma", JOptionPane.YES_NO_OPTION);
        if (conferma == JOptionPane.YES_OPTION) {
            Controller.getInstance().rifiutaRichiesta(richiesta);
            JOptionPane.showMessageDialog(this, "Richiesta rifiutata.", "Esito", JOptionPane.INFORMATION_MESSAGE);
            caricaRichieste();
        }
    }
}