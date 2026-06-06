package gui;

import controller.Controller;
import model.logistica.Aula;
import model.utente.Utente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestioneAulePanel extends JFrame {

    private JPanel mainPanelAule;
    private JLabel lblTitleAule;
    private JPanel addPanelAule;
    private JLabel lblNewAula;
    private JTextField txtNomeAula;
    private JButton btnAddAule;
    private JPanel viewPanelAule;
    private JLabel lblExistingAule;
    private JScrollPane scrollPanelAule;
    private JList<Aula> listAule;
    private JButton btnBackAule;

    private final DefaultListModel<Aula> listModel;
    private final Controller controller;
    private final Utente utente;
    private final JFrame parentFrame;


    public GestioneAulePanel(Controller controller, Utente utente, JFrame parentFrame) {

        this.controller = controller;
        this.utente = utente;
        this.parentFrame = parentFrame;
        this.listModel = new DefaultListModel<>();

        setContentPane(mainPanelAule);
        setTitle("Manage Classrooms");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setMinimumSize(new Dimension(700, 600));
        setPreferredSize(new Dimension(700, 600));
        setResizable(true);

        listAule.setModel(listModel);

        updateClassroomList();
        setupStyles();

        btnAddAule.addActionListener(e -> processAddAula());

        btnBackAule.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }


    private void updateClassroomList() {

        listModel.clear();
        List<Aula> auleSistema = controller.getTutteAule();

        if (auleSistema != null) {
            for (Aula aula : auleSistema) {
                listModel.addElement(aula);
            }
        }
    }


    private void processAddAula() {

        String nomeAula = txtNomeAula.getText().trim();

        if (nomeAula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a classroom name.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            boolean success = controller.aggiungiAula(nomeAula);

            if (success) {
                JOptionPane.showMessageDialog(this, "Classroom added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                txtNomeAula.setText("");
                updateClassroomList();
            } else {
                JOptionPane.showMessageDialog(this, "A classroom with this name already exists.", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void setupStyles() {

        btnAddAule.setBackground(new Color(45, 62, 90));
        btnAddAule.setForeground(Color.WHITE);
        btnAddAule.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnBackAule.setBackground(new Color(86, 125, 185));
        btnBackAule.setForeground(Color.WHITE);
        btnBackAule.setFont(new Font("Segoe UI", Font.BOLD, 13));

        lblTitleAule.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitleAule.setForeground(new Color(45, 62, 90));

        listAule.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listAule.setSelectionBackground(new Color(45, 62, 90));
        listAule.setSelectionForeground(Color.WHITE);
    }
}