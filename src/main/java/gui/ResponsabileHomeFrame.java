package gui;

import controller.Controller;
import model.utente.ResponsabileOrari;

import javax.swing.*;

public class ResponsabileHomeFrame extends JFrame {

    private JPanel mainPanelResponsabile;
    private JLabel lblTitleResponsabile;
    private JPanel formPanelResponsabile;

    private JLabel lblSurnameResponsabile;
    private JTextField txtSurnameResponsabile;
    private JLabel lblNameResponsabile;
    private JTextField txtNameResponsabile;
    private JLabel lblEmailResponsabile;
    private JTextField txtEmailResponsabile;
    private JLabel lblLoginResponsabile;
    private JTextField txtLoginResponsabile;

    private JPanel buttonPanelResponsabile1;
    private JButton btnCreaLezioneResponsabile;
    private JButton btnGestioneAuleResponsabile;
    private JButton btnVisualizzaOrarioResponsabile;

    private JPanel buttonPanelResponsabile2;
    private JButton btnRichiesteResponsabile;
    private JButton btnBackToLoginResponsabile;

    private final Controller controller;
    private final ResponsabileOrari responsabile;

    public ResponsabileHomeFrame(Controller controller, ResponsabileOrari responsabile) {
        this.controller = controller;
        this.responsabile = responsabile;

        setContentPane(mainPanelResponsabile);
        setTitle("University Timetable Manager - Admin Area");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        fillResponsabileData();
        initializeListeners();

        pack();
        setLocationRelativeTo(null);
    }

    private void fillResponsabileData() {
        if (responsabile != null) {
            txtSurnameResponsabile.setText(responsabile.getCognome());
            txtNameResponsabile.setText(responsabile.getNome());
            txtEmailResponsabile.setText(responsabile.getEmail());
            txtLoginResponsabile.setText(responsabile.getLogin());
        }
    }

    private void initializeListeners() {
        btnVisualizzaOrarioResponsabile.addActionListener(e -> {
            VisualizzaOrarioFrame orarioFrame = new VisualizzaOrarioFrame(controller, responsabile, this);
            orarioFrame.setVisible(true);
            this.setVisible(false);
        });

        btnCreaLezioneResponsabile.addActionListener(e -> {
            CreaLezionePanel lezioneFrame = new CreaLezionePanel(controller, responsabile, this);
            lezioneFrame.setVisible(true);
            this.setVisible(false);
        });

        btnGestioneAuleResponsabile.addActionListener(e -> {
            GestioneAulePanel auleFrame = new GestioneAulePanel(controller, responsabile, this);
            auleFrame.setVisible(true);
            this.setVisible(false);
        });

        btnRichiesteResponsabile.addActionListener(e -> {
            RichiestePanel richiesteFrame = new RichiestePanel(controller, responsabile, this);
            richiesteFrame.setVisible(true);
            this.setVisible(false);
        });

        btnBackToLoginResponsabile.addActionListener(e -> {
            controller.logout();
            controller.showLoginFrame();
            dispose();
        });
    }
}