package gui;

import controller.Controller;
import model.utente.Studente;

import javax.swing.*;

public class StudenteHomeFrame extends JFrame {

    private JPanel mainPanelStudente;
    private JLabel lblTitleStudente;
    private JPanel formPanelStudente;

    private JTextField txtSurnameStudente;
    private JTextField txtNameStudente;
    private JTextField txtEmailStudente;
    private JTextField txtLoginStudente;
    private JTextField txtMatricolaStudente;
    private JTextField txtAnnoCorsoStudente;

    private JLabel lblSurnameStudente;
    private JLabel lblNameStudente;
    private JLabel lblEmailStudente;
    private JLabel lblLoginStudente;
    private JLabel lblMatricolaStudente;
    private JLabel lblAnnoCorsoStudente;

    private JButton btnVisualizzaOrarioStudente;
    private JButton btnBackToLoginStudente;

    private final Controller controller;
    private final Studente studente;

    public StudenteHomeFrame(Controller controller, Studente studente) {
        this.controller = controller;
        this.studente = studente;

        setContentPane(mainPanelStudente);
        setTitle("University Timetable Manager - Student Area");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        fillStudentData();
        initializeListeners();

        pack();
        setLocationRelativeTo(null);
    }

    private void fillStudentData() {
        if (studente != null) {
            txtNameStudente.setText(studente.getNome());
            txtSurnameStudente.setText(studente.getCognome());
            txtEmailStudente.setText(studente.getEmail());
            txtLoginStudente.setText(studente.getLogin());
            txtMatricolaStudente.setText(studente.getMatricola());

            if (studente.getAnnoCorso() != null) {
                switch (studente.getAnnoCorso()) {
                    case PRIMO -> txtAnnoCorsoStudente.setText("First Year (I)");
                    case SECONDO -> txtAnnoCorsoStudente.setText("Second Year (II)");
                    case TERZO -> txtAnnoCorsoStudente.setText("Third Year (III)");
                    default -> txtAnnoCorsoStudente.setText(studente.getAnnoCorso().toString());
                }
            }
        }
    }

    private void initializeListeners() {
        btnVisualizzaOrarioStudente.addActionListener(e -> {
            VisualizzaOrarioFrame orarioFrame = new VisualizzaOrarioFrame(controller, studente, this);
            orarioFrame.setVisible(true);
            this.setVisible(false);
        });

        btnBackToLoginStudente.addActionListener(e -> {
            controller.logout();
            controller.showLoginFrame();
            dispose();
        });
    }
}