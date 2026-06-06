package gui;

import controller.Controller;
import model.utente.Studente;

import javax.swing.*;
import java.awt.*;

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

        setMinimumSize(new Dimension(700, 600));
        setPreferredSize(new Dimension(700, 600));
        setResizable(true);

        fillStudentData();
        initializeListeners();

        pack();
        setLocationRelativeTo(null);
    }


    private void fillStudentData() {

        if (studente != null) {

            txtNameStudente.setText(studente.getNome());
            txtNameStudente.setEditable(false);

            txtSurnameStudente.setText(studente.getCognome());
            txtSurnameStudente.setEditable(false);

            txtEmailStudente.setText(studente.getEmail());
            txtEmailStudente.setEditable(false);

            txtLoginStudente.setText(studente.getLogin());
            txtLoginStudente.setEditable(false);

            txtMatricolaStudente.setText(studente.getMatricola());
            txtMatricolaStudente.setEditable(false);


            if (studente.getAnnoCorso() != null) {

                switch (studente.getAnnoCorso()) {

                    case PRIMO:
                        txtAnnoCorsoStudente.setText("First Year (I)");
                        break;

                    case SECONDO:
                        txtAnnoCorsoStudente.setText("Second Year (II)");
                        break;

                    case TERZO:
                        txtAnnoCorsoStudente.setText("Third Year (III)");
                        break;

                    default:
                        txtAnnoCorsoStudente.setText(studente.getAnnoCorso().toString());
                        break;
                }
            }

            txtAnnoCorsoStudente.setEditable(false);
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
            dispose();
        });
    }
}