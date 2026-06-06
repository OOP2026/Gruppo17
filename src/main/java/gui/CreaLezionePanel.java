package gui;

import controller.Controller;
import model.didattica.Giorno;
import model.didattica.Insegnamento;
import model.didattica.OraInizio;
import model.logistica.Aula;
import model.utente.Utente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreaLezionePanel extends JFrame {

    private JPanel mainPanelLezione;
    private JLabel lblTitleLezione;
    private JPanel formPanelLezione;
    private JLabel lblInsegnamentoLezione;
    private JLabel lblGiornoLezione;
    private JLabel lblOraLezione;
    private JLabel lblAulaLezione;
    private JComboBox<Insegnamento> cmbInsegnamentoLezione;
    private JComboBox<Giorno> cmbGiornoLezione;
    private JComboBox<OraInizio> cmbOraLezione;
    private JComboBox<Aula> cmbAulaLezione;
    private JButton btnCreateLezione;
    private JButton btnBackLezione;

    private final Controller controller;
    private final Utente utente;
    private final JFrame parentFrame;


    public CreaLezionePanel(Controller controller, Utente utente, JFrame parentFrame) {

        this.controller = controller;
        this.utente = utente;
        this.parentFrame = parentFrame;

        setContentPane(mainPanelLezione);
        setTitle("Create Lesson");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setMinimumSize(new Dimension(700, 600));
        setPreferredSize(new Dimension(700, 600));
        setResizable(true);

        populateComboBoxes();
        setupStyles();

        btnCreateLezione.addActionListener(e -> processCreateLezione());

        btnBackLezione.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }


    private void populateComboBoxes() {

        List<Insegnamento> insegnamenti = controller.getInsegnamenti();
        if (insegnamenti != null) {
            for (Insegnamento ins : insegnamenti) {
                cmbInsegnamentoLezione.addItem(ins);
            }
        }

        for (Giorno g : Giorno.values()) {
            cmbGiornoLezione.addItem(g);
        }

        for (OraInizio ora : OraInizio.values()) {
            cmbOraLezione.addItem(ora);
        }

        List<Aula> aule = controller.getTutteAule();
        if (aule != null) {
            for (Aula a : aule) {
                cmbAulaLezione.addItem(a);
            }
        }
    }


    private void processCreateLezione() {

        Insegnamento selectedInsegnamento = (Insegnamento) cmbInsegnamentoLezione.getSelectedItem();
        Giorno selectedGiorno = (Giorno) cmbGiornoLezione.getSelectedItem();
        OraInizio selectedOra = (OraInizio) cmbOraLezione.getSelectedItem();
        Aula selectedAula = (Aula) cmbAulaLezione.getSelectedItem();

        if (selectedInsegnamento == null || selectedGiorno == null || selectedOra == null || selectedAula == null) {
            JOptionPane.showMessageDialog(this, "Please select all fields.", "Missing Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = controller.creaLezione(selectedInsegnamento, selectedGiorno, selectedOra, selectedAula);

        if (success) {

            JOptionPane.showMessageDialog(this, "Lesson created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            cmbInsegnamentoLezione.setSelectedIndex(-1);
            cmbGiornoLezione.setSelectedIndex(-1);
            cmbOraLezione.setSelectedIndex(-1);
            cmbAulaLezione.setSelectedIndex(-1);

        } else {
            JOptionPane.showMessageDialog(this, "Conflict detected! Classroom or professor already busy at this time.", "Schedule Conflict", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void setupStyles() {

        lblTitleLezione.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitleLezione.setForeground(new Color(45, 62, 90));

        lblInsegnamentoLezione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblInsegnamentoLezione.setForeground(Color.WHITE);

        lblGiornoLezione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblGiornoLezione.setForeground(Color.WHITE);

        lblOraLezione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblOraLezione.setForeground(Color.WHITE);

        lblAulaLezione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblAulaLezione.setForeground(Color.WHITE);

        btnCreateLezione.setBackground(new Color(45, 62, 90));
        btnCreateLezione.setForeground(Color.WHITE);
        btnCreateLezione.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnBackLezione.setBackground(new Color(86, 125, 185));
        btnBackLezione.setForeground(Color.WHITE);
        btnBackLezione.setFont(new Font("Segoe UI", Font.BOLD, 13));

        cmbInsegnamentoLezione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbGiornoLezione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbOraLezione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbAulaLezione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }
}