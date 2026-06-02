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
            txtNameDocente.setText(docente.getNome());
            txtEmailDocente.setText(docente.getEmail());
            txtLoginDocente.setText(docente.getLogin());
        }
    }

    private void initializeListeners() {
        btnVisualizzaOrarioDocente.addActionListener(e -> {
            docente.visualizzaOrario();
            VisualizzaOrarioFrame orarioFrame = new VisualizzaOrarioFrame(controller, docente, this);
            orarioFrame.setVisible(true);
            this.setVisible(false);
        });

        btnInviaRichiestaDocente.addActionListener(e -> {
            RichiestaSpostamentoFrame richiestaFrame = new RichiestaSpostamentoFrame(controller, docente, this);
            richiestaFrame.setVisible(true);
            this.setVisible(false);
        });

        btnBackToLoginDocente.addActionListener(e -> {
            controller.logout();
            controller.showLoginFrame();
            dispose();
        });
    }
}