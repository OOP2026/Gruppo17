package gui;

import controller.Controller;
import model.utente.Studente;

import javax.swing.*;

public class StudenteHomeFrame extends JFrame {

    // =====================================================
    // COMPONENTS
    // =====================================================
    private JPanel mainPanelStudent;
    private JLabel lblTitleStudent;
    private JPanel formPanelStudent;


    private JTextField txtSurnameStudent;
    private JTextField txtNameStudent;
    private JTextField txtLoginStudent;
    private JTextField txtCourseYearStudent;
    private JTextField JTextField;
    private JTextField txtMatricolaStudent;


    private JLabel lblSurnameStudent;
    private JLabel lblNameStudent;
    private JLabel lblEmailStudente;
    private JLabel lblLoginStudente;
    private JLabel lblMatricolaStudente;
    private JLabel lblCourseYearStudent;

    private JButton btnBackToLogin;
    private JButton btnVisualizzaOrario;

    // =====================================================
    // FIELDS & CONTROLLER
    // =====================================================
    private final Controller controller;
    private final Studente studente;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================
    public StudenteHomeFrame(Controller controller, Studente studente) {
        this.controller = controller;
        this.studente = studente;

        setContentPane(mainPanelStudent);

        setTitle("University Timetable Manager - Student Area");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        pack();
        setLocationRelativeTo(null);

        fillStudentData();
        initializeListeners();
    }

    // =====================================================
    // LOGIC METHODS
    // =====================================================
    private void fillStudentData() {
        if (studente != null) {
            txtNameStudent.setText(studente.getNome());
            txtSurnameStudent.setText(studente.getCognome());

            if (JTextField != null) {
                JTextField.setText(studente.getEmail());
            }

            txtLoginStudent.setText(studente.getLogin());
            txtMatricolaStudent.setText(studente.getMatricola());

            if (studente.getAnnoCorso() != null) {
                switch (studente.getAnnoCorso()) {
                    case PRIMO -> txtCourseYearStudent.setText("First Year (I)");
                    case SECONDO -> txtCourseYearStudent.setText("Second Year (II)");
                    case TERZO -> txtCourseYearStudent.setText("Third Year (III)");
                    default -> txtCourseYearStudent.setText(studente.getAnnoCorso().toString());
                }
            }
        }
    }

    private void initializeListeners() {

        btnVisualizzaOrario.addActionListener(e -> {
            VisualizzaOrarioFrame orarioFrame = new VisualizzaOrarioFrame(controller, studente, this);
            orarioFrame.setVisible(true);
            this.setVisible(false);
        });


        btnBackToLogin.addActionListener(e -> {
            controller.logout();
            controller.showLoginFrame();
            dispose();
        });
    }
}