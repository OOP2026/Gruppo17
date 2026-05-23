package gui;

import controller.Controller;

import javax.swing.*;

public class LoginFrame extends JFrame {

    // =====================================================
    // COMPONENTS
    // =====================================================

    private JPanel mainPanel;

    private JLabel lblTitle;

    private JPanel formPanel;

    private JLabel lblRole;

    private JComboBox<String> cmbRole;

    private JLabel lblLogin;

    private JLabel lblPassword;

    private JTextField txtLogin;

    private JPasswordField txtPassword;

    private JButton btnOk;

    private JButton btnClear;

    private JButton btnRegister;

    // =====================================================
    // CONTROLLER
    // =====================================================

    private final Controller controller;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public LoginFrame(Controller controller) {

        this.controller = controller;

        setContentPane(mainPanel);

        setTitle("University Timetable Manager");

        setSize(850, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializeComboBox();

        initializeListeners();
    }

    // =====================================================
    // COMBOBOX
    // =====================================================

    private void initializeComboBox() {

        cmbRole.addItem("Student");

        cmbRole.addItem("Teacher");

        cmbRole.addItem("Administrator");
    }

    // =====================================================
    // LISTENERS
    // =====================================================

    private void initializeListeners() {

        // =================================================
        // LOGIN
        // =================================================

        btnOk.addActionListener(e -> login());

        // =================================================
        // CLEAR
        // =================================================

        btnClear.addActionListener(e -> clearFields());

        // =================================================
        // REGISTER
        // =================================================

        btnRegister.addActionListener(e ->
                controller.showRegisterFrame()
        );
    }

    // =====================================================
    // LOGIN
    // =====================================================

    private void login() {

        String login =
                txtLogin.getText().trim();

        String password =
                String.valueOf(
                        txtPassword.getPassword()
                );

        if(login.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fields cannot be empty!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean success =
                controller.login(
                        login,
                        password
                );

        if(success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            controller.openHomeByRole();

            dispose();
        }

        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Wrong login or password!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPassword.setText("");
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        txtLogin.setText("");

        txtPassword.setText("");

        cmbRole.setSelectedIndex(0);
    }

    // =====================================================
    // SWING COMPONENTS
    // =====================================================

    private void createUIComponents() {

    }
}