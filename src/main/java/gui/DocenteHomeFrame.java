package gui;

import controller.Controller;
import model.utente.Docente;

import javax.swing.*;

public class DocenteHomeFrame extends JFrame {

    private JPanel mainPanelDocente;
    private JLabel lblTitleDocente;
    private JPanel formPanelDocente;

    private JLabel lblSurnameDocente;
    private JTextField txtSurnameDocente;
    private JLabel lblNameDocente;
    private JTextField txtNameDocente;
    private JLabel lblEmailDocente;
    private JTextField txtEmailDocente;
    private JLabel lblLoginDocente;
    private JTextField txtLoginDocente;

    private JPanel buttonPanelDocente;
    private JButton btnVisualizzaOrarioDocente;
    private JButton btnInviaRichiestaDocente;
    private JButton btnBackToLoginDocente;

    private final Controller controller;
    private final Docente docente;


    public DocenteHomeFrame(Controller controller, Docente docente) {

        this.controller = controller;
        this.docente = docente;

        setContentPane(mainPanelDocente);
        setTitle("University Timetable Manager - Teacher Area");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        fillDocenteData();
        initializeListeners();

        pack();
        setLocationRelativeTo(null);
    }


    private void fillDocenteData() {

        if (docente != null) {

            txtSurnameDocente.setText(docente.getCognome());
            txtSurnameDocente.setEditable(false);

            txtNameDocente.setText(docente.getNome());
            txtNameDocente.setEditable(false);

            txtEmailDocente.setText(docente.getEmail());
            txtEmailDocente.setEditable(false);

            txtLoginDocente.setText(docente.getLogin());
            txtLoginDocente.setEditable(false);
        }
    }


    private void initializeListeners() {

        btnVisualizzaOrarioDocente.addActionListener(e -> {

            VisualizzaOrarioFrame orarioFrame = new VisualizzaOrarioFrame(controller, docente, this);
            orarioFrame.setVisible(true);

            this.setVisible(false);
        });


        btnInviaRichiestaDocente.addActionListener(e -> {

            RichiestaSpostamentoPanel richiestaFrame = new RichiestaSpostamentoPanel(controller, docente, this);
            richiestaFrame.setVisible(true);

            this.setVisible(false);
        });


        btnBackToLoginDocente.addActionListener(e -> {

            controller.logout();
            dispose();
        });
    }
}