package gui;

import controller.Controller;
import model.utente.Utente;
import model.utente.Docente;
import model.didattica.Lezione;
import model.didattica.Giorno;
import model.didattica.OraInizio;
import model.richiesta.RichiestaSpostamento;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RichiestaSpostamentoPanel extends JFrame {

    private JPanel mainPanelSpostamento;
    private JLabel lblTitleSpostamento;
    private JPanel formPanelSpostamento;
    private JComboBox<Lezione> cmbLezioneSpostamento;
    private JLabel lblNewDaySpostamento;
    private JLabel lblNewTimeSpostamento;
    private JLabel lblMotivationSpostamento;
    private JComboBox<Giorno> cmbNewDaySpostamento;
    private JComboBox<OraInizio> cmbNewTimeSpostamento;
    private JTextArea textMotivationSpostamento;
    private JButton btnSubmitSpostamento;
    private JButton btnAnnullaSpostamento;

    private final Controller controller;
    private final Utente utente;
    private final JFrame parentFrame;

    public RichiestaSpostamentoPanel(Controller controller, Utente utente, JFrame parentFrame) {
        this.controller = controller;
        this.utente = utente;
        this.parentFrame = parentFrame;

        setContentPane(mainPanelSpostamento);
        setTitle("Create Rescheduling Request");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setupLectureComboBox();
        setupGiornoComboBox();
        setupTimeComboBox();
        setupStyles();

        btnSubmitSpostamento.addActionListener(e -> processAndSubmitRequest());

        btnAnnullaSpostamento.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void setupLectureComboBox() {
        cmbLezioneSpostamento.removeAllItems();
        List<Lezione> lezioniDocente = controller.getLezioniForUser(utente);

        if (lezioniDocente != null && !lezioniDocente.isEmpty()) {
            cmbLezioneSpostamento.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Lezione lez) {
                        setText(String.format("%s — %s (%s)",
                                lez.getInsegnamento().getNomeInsegnamento(),
                                lez.getGiornoSettimana(),
                                lez.getOraInizio()
                        ));
                    }
                    return this;
                }
            });

            for (Lezione lez : lezioniDocente) {
                cmbLezioneSpostamento.addItem(lez);
            }
        } else {
            btnSubmitSpostamento.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No active lessons found for this teacher.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void setupGiornoComboBox() {
        cmbNewDaySpostamento.removeAllItems();
        for (Giorno g : Giorno.values()) {
            if (g.disponibilePerLezioni()) {
                cmbNewDaySpostamento.addItem(g);
            }
        }
    }

    private void setupTimeComboBox() {
        cmbNewTimeSpostamento.removeAllItems();
        for (OraInizio ora : OraInizio.values()) {
            cmbNewTimeSpostamento.addItem(ora);
        }
    }

    private void processAndSubmitRequest() {
        Lezione selectedLezione = (Lezione) cmbLezioneSpostamento.getSelectedItem();
        String motivazione = textMotivationSpostamento.getText().trim();
        Giorno giornoProposto = (Giorno) cmbNewDaySpostamento.getSelectedItem();
        OraInizio oraInizioProposta = (OraInizio) cmbNewTimeSpostamento.getSelectedItem();

        if (selectedLezione == null) {
            JOptionPane.showMessageDialog(this, "Please select a lesson to reschedule.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (motivazione.isEmpty()) {
            JOptionPane.showMessageDialog(this, "The reason/motivation cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (oraInizioProposta == null) {
            JOptionPane.showMessageDialog(this, "Please select a new start time.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            RichiestaSpostamento nuovaRichiesta = new RichiestaSpostamento(
                    motivazione,
                    selectedLezione.getGiornoSettimana(),
                    selectedLezione.getOraInizio(),
                    giornoProposto,
                    oraInizioProposta
            );

            if (utente instanceof Docente) {
                nuovaRichiesta.setDocente((Docente) utente);
            }

            boolean success = controller.inviaRichiestaSpostamento(nuovaRichiesta);

            if (success) {
                JOptionPane.showMessageDialog(this, "Rescheduling request submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                parentFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to submit request. System conflict detected (Room or Teacher busy).", "Schedule Conflict", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Business Logic Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupStyles() {
        btnSubmitSpostamento.setBackground(new Color(45, 62, 90));
        btnSubmitSpostamento.setForeground(Color.WHITE);
        btnSubmitSpostamento.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnAnnullaSpostamento.setBackground(new Color(140, 40, 50));
        btnAnnullaSpostamento.setForeground(Color.WHITE);
        btnAnnullaSpostamento.setFont(new Font("Segoe UI", Font.BOLD, 14));

        lblTitleSpostamento.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitleSpostamento.setForeground(new Color(45, 62, 90));

        textMotivationSpostamento.setLineWrap(true);
        textMotivationSpostamento.setWrapStyleWord(true);
    }
}