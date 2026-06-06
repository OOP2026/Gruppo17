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
    private JLabel lblLezioneSpostamento;
    private JComboBox<Lezione> cmbLezioneSpostamento;
    private JLabel lblNewDaySpostamento;
    private JComboBox<Giorno> cmbNewDaySpostamento;
    private JLabel lblNewTimeSpostamento;
    private JComboBox<OraInizio> cmbNewTimeSpostamento;
    private JLabel lblMotivationSpostamento;
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

        setMinimumSize(new Dimension(700, 600));
        setPreferredSize(new Dimension(700, 600));
        setResizable(true);

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
                    if (value instanceof Lezione) {
                        Lezione lez = (Lezione) value;
                        setText(String.format("%s — %s (%s)",
                                lez.getInsegnamento().getNomeInsegnamento(),
                                lez.getGiornoSettimana(),
                                lez.getOraInizio()));
                    }
                    return this;
                }
            });

            for (Lezione lez : lezioniDocente) {
                cmbLezioneSpostamento.addItem(lez);
            }
        } else {
            btnSubmitSpostamento.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No active lessons found.");
        }
    }

    private void setupGiornoComboBox() {
        cmbNewDaySpostamento.removeAllItems();
        for (Giorno g : Giorno.values()) {
            String name = g.name().toUpperCase();
            if (!name.equals("SABATO") && !name.equals("DOMENICA")) {
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

        if (selectedLezione == null || motivazione.isEmpty() || giornoProposto == null || oraInizioProposta == null) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        if (!(utente instanceof Docente)) return;

        RichiestaSpostamento nuovaRichiesta = new RichiestaSpostamento(
                (Docente) utente,
                motivazione,
                selectedLezione.getGiornoSettimana(),
                selectedLezione.getOraInizio(),
                giornoProposto,
                oraInizioProposta
        );

        if (controller.inviaRichiestaSpostamento(nuovaRichiesta)) {
            JOptionPane.showMessageDialog(this, "Submitted successfully!");
            parentFrame.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Conflict detected.");
        }
    }

    private void setupStyles() {
        btnSubmitSpostamento.setBackground(new Color(45, 62, 90));
        btnSubmitSpostamento.setForeground(Color.WHITE);
        btnAnnullaSpostamento.setBackground(new Color(140, 40, 50));
        btnAnnullaSpostamento.setForeground(Color.WHITE);
        lblTitleSpostamento.setFont(new Font("Segoe UI", Font.BOLD, 18));
        textMotivationSpostamento.setLineWrap(true);
    }
}