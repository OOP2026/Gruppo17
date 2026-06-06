package gui;

import controller.Controller;
import model.utente.Utente;

import javax.swing.*;
import java.awt.*;

public class ResponsabileHomeFrame extends JFrame {

    private JPanel mainPanelResponsabile;
    private JLabel lblTitleResponsabile;
    private JPanel formPanelResponsabile;
    private JLabel lblSurnameResponsabile;
    private JLabel lblNameResponsabile;
    private JLabel lblEmailResponsabile;
    private JLabel lblLoginResponsabile;
    private JTextField txtSurnameResponsabile;
    private JTextField txtNameResponsabile;
    private JTextField txtEmailResponsabile;
    private JTextField txtLoginResponsabile;
    private JPanel buttonPanelResponsabile1;
    private JButton btnCreaLezioneResponsabile;
    private JButton btnGestioneAuleResponsabile;
    private JButton btnVisualizzaOrarioResponsabile;
    private JPanel buttonPanelResponsabile2;
    private JButton btnRichiesteResponsabile;
    private JButton btnBackToLoginResponsabile;

    private final Controller controller;
    private final Utente responsabile;


    public ResponsabileHomeFrame(Controller controller, Utente responsabile) {

        this.controller = controller;
        this.responsabile = responsabile;

        setContentPane(mainPanelResponsabile);
        setTitle("Admin Dashboard");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(700, 600));
        setPreferredSize(new Dimension(700, 600));
        setResizable(true);

        loadResponsabileData();
        setupStyles();
        initializeListeners();

        pack();
        setLocationRelativeTo(null);
    }


    private void loadResponsabileData() {

        if (responsabile != null) {
            txtSurnameResponsabile.setText(responsabile.getCognome());
            txtNameResponsabile.setText(responsabile.getNome());
            txtEmailResponsabile.setText(responsabile.getEmail());
            txtLoginResponsabile.setText(responsabile.getLogin());
        }
    }


    private void initializeListeners() {

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

        btnVisualizzaOrarioResponsabile.addActionListener(e -> {

            VisualizzaOrarioFrame orarioFrame = new VisualizzaOrarioFrame(controller, responsabile, this);
            orarioFrame.setVisible(true);

            this.setVisible(false);
        });

        btnRichiesteResponsabile.addActionListener(e -> {

            RichiestePanel richiesteFrame = new RichiestePanel(controller, this);
            richiesteFrame.setVisible(true);

            this.setVisible(false);
        });

        btnBackToLoginResponsabile.addActionListener(e -> {

            controller.logout();

            LoginFrame loginFrame = new LoginFrame(controller);
            loginFrame.setVisible(true);

            dispose();
        });
    }


    private void setupStyles() {

        Color primaryColor = new Color(45, 62, 90);
        Color secondaryColor = new Color(86, 125, 185);

        lblTitleResponsabile.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitleResponsabile.setForeground(primaryColor);

        btnCreaLezioneResponsabile.setBackground(primaryColor);
        btnCreaLezioneResponsabile.setForeground(Color.WHITE);
        btnCreaLezioneResponsabile.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnGestioneAuleResponsabile.setBackground(primaryColor);
        btnGestioneAuleResponsabile.setForeground(Color.WHITE);
        btnGestioneAuleResponsabile.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnVisualizzaOrarioResponsabile.setBackground(primaryColor);
        btnVisualizzaOrarioResponsabile.setForeground(Color.WHITE);
        btnVisualizzaOrarioResponsabile.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnRichiesteResponsabile.setBackground(primaryColor);
        btnRichiesteResponsabile.setForeground(Color.WHITE);
        btnRichiesteResponsabile.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnBackToLoginResponsabile.setBackground(secondaryColor);
        btnBackToLoginResponsabile.setForeground(Color.WHITE);
        btnBackToLoginResponsabile.setFont(new Font("Segoe UI", Font.BOLD, 12));

        txtSurnameResponsabile.setEditable(false);
        txtNameResponsabile.setEditable(false);
        txtEmailResponsabile.setEditable(false);
        txtLoginResponsabile.setEditable(false);
    }
}