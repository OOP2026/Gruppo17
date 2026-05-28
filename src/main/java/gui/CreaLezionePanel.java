package gui;

import controller.Controller;
import model.Aula;
import model.Insegnamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreaLezionePanel extends JPanel {

    private JComboBox<Insegnamento> cmbInsegnamento;
    private JComboBox<Aula> cmbAula;
    private JComboBox<String> cmbGiorno;
    private JTextField txtOraInizio;
    private JTextField txtOraFine;
    private JButton btnCrea;

    public CreaLezionePanel() {
        initComponents();
        caricaDati();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        cmbInsegnamento = new JComboBox<>();
        cmbAula = new JComboBox<>();
        cmbGiorno = new JComboBox<>();
        txtOraInizio = new JTextField(10);
        txtOraFine = new JTextField(10);
        btnCrea = new JButton("Crea Lezione");

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Insegnamento:"), gbc);
        gbc.gridx = 1;
        add(cmbInsegnamento, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Aula:"), gbc);
        gbc.gridx = 1;
        add(cmbAula, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Giorno:"), gbc);
        gbc.gridx = 1;
        add(cmbGiorno, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Ora Inizio (es. 09:00):"), gbc);
        gbc.gridx = 1;
        add(txtOraInizio, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Ora Fine (es. 11:00):"), gbc);
        gbc.gridx = 1;
        add(txtOraFine, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        add(btnCrea, gbc);

        btnCrea.addActionListener(this::btnCreaActionPerformed);
    }

    private void caricaDati() {
        Controller c = Controller.getInstance();
        c.getInsegnamenti().forEach(cmbInsegnamento::addItem);
        c.getAuleDisponibili().forEach(cmbAula::addItem);
        String[] giorni = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì"};
        for (String g : giorni) cmbGiorno.addItem(g);
    }

    public void btnCreaActionPerformed(ActionEvent e) {
        Insegnamento ins = (Insegnamento) cmbInsegnamento.getSelectedItem();
        Aula aula = (Aula) cmbAula.getSelectedItem();
        String giorno = (String) cmbGiorno.getSelectedItem();

        if (ins == null || aula == null || giorno == null ||
                txtOraInizio.getText().trim().isEmpty() ||
                txtOraFine.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tutti i campi sono obbligatori!", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Controller.getInstance().creaLezione(ins, giorno, txtOraInizio.getText().trim(), txtOraFine.getText().trim(), aula);
        JOptionPane.showMessageDialog(this, "Lezione creata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
    }
}
