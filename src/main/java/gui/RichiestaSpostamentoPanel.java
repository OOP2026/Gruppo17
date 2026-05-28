package gui;

import controller.Controller;
import model.Docente;
import model.Lezione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RichiestaSpostamentoPanel extends JPanel {

    private JComboBox<Lezione> cmbLezioni;
    private JComboBox<String> cmbGiorno;
    private JTextField txtNuovoOrario;
    private JButton btnInviaRichiesta;

    private final Docente docente;

    public RichiestaSpostamentoPanel(Docente docente) {
        this.docente = docente;
        setLayout(new GridBagLayout());
        initComponents();
        caricaLezioni();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Seleziona Lezione:"), gbc);
        cmbLezioni = new JComboBox<>();
        gbc.gridx = 1;
        add(cmbLezioni, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nuovo Giorno:"), gbc);
        cmbGiorno = new JComboBox<>(new String[]{"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"});
        gbc.gridx = 1;
        add(cmbGiorno, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Nuovo Orario (es. 10:00-12:00):"), gbc);
        txtNuovoOrario = new JTextField(15);
        gbc.gridx = 1;
        add(txtNuovoOrario, gbc);

        btnInviaRichiesta = new JButton("Invia Richiesta di Spostamento");
        gbc.gridx = 1; gbc.gridy = 3;
        add(btnInviaRichiesta, gbc);

        btnInviaRichiesta.addActionListener(this::inviaRichiesta);
    }

    private void caricaLezioni() {
        cmbLezioni.removeAllItems();
        Controller.getInstance().getLezioniDocente(docente).forEach(cmbLezioni::addItem);
    }

    private void inviaRichiesta(ActionEvent e) {
        Lezione lezioneSelezionata = (Lezione) cmbLezioni.getSelectedItem();
        String nuovoGiorno = (String) cmbGiorno.getSelectedItem();
        String nuovoOrario = txtNuovoOrario.getText().trim();

        if (lezioneSelezionata == null || nuovoGiorno == null || nuovoOrario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Compilare tutti i campi!", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Controller.getInstance().inviaRichiestaSpostamento(docente, lezioneSelezionata, nuovoGiorno, nuovoOrario);
        JOptionPane.showMessageDialog(this,
                "Richiesta di spostamento inviata con successo!\nIn attesa di approvazione dal Responsabile.",
                "Richiesta Inviata", JOptionPane.INFORMATION_MESSAGE);
        txtNuovoOrario.setText("");
    }
}