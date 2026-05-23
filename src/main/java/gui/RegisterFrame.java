package gui;

import controller.Controller;

import javax.swing.*;

public class RegisterFrame extends JFrame {

    // =====================================================
    // COMPONENTS
    // =====================================================

    private JPanel mainPanelRegister;

    private JLabel lblTitleRegister;

    private JPanel formPanelRegister;

    private JLabel lblRoleRegister;

    private JComboBox<String> cmbRoleRegister;

    private JLabel lblSurnameRegister;

    private JLabel lblNameRegister;

    private JLabel lblEmailRegister;

    private JLabel lblLoginRegister;

    private JLabel lblPasswordRegister;

    private JLabel lblStudentIDRegister;

    private JTextField txtSurnameRegister;

    private JTextField txtNameRegister;

    private JTextField txtEmailRegister;

    private JTextField txtLoginRegister;

    private JTextField txtStudentIDRegister;

    private JPasswordField txtPasswordRegister;

    private JButton btnRegister;

    private JButton btnBackRegister;

    // =====================================================
    // CONTROLLER
    // =====================================================

    private final Controller controller;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RegisterFrame(Controller controller) {

        this.controller = controller;

        // =================================================
        // FRAME SETTINGS
        // =================================================

        setContentPane(mainPanelRegister);

        setTitle("Create New Account");

        setSize(850, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);

        // =================================================
        // INITIALIZATION
        // =================================================

        initializeComboBox();

        initializeListeners();

        updateRoleFields();
    }

    // =====================================================
    // COMBOBOX
    // =====================================================

    private void initializeComboBox() {

        cmbRoleRegister.removeAllItems();

        cmbRoleRegister.addItem("Student");

        cmbRoleRegister.addItem("Teacher");

        cmbRoleRegister.addItem("Administrator");

        cmbRoleRegister.setSelectedIndex(0);
    }

    // =====================================================
    // LISTENERS
    // =====================================================

    private void initializeListeners() {

        // =================================================
        // REGISTER BUTTON
        // =================================================

        btnRegister.addActionListener(e -> register());

        // =================================================
        // BACK BUTTON
        // =================================================

        btnBackRegister.addActionListener(e -> backToLogin());

        // =================================================
        // ROLE CHANGE
        // =================================================

        cmbRoleRegister.addActionListener(e -> updateRoleFields());
    }

    // =====================================================
    // REGISTER
    // =====================================================

    private void register() {

        // =================================================
        // GET DATA
        // =================================================

        String surname =
                txtSurnameRegister
                        .getText()
                        .trim();

        String name =
                txtNameRegister
                        .getText()
                        .trim();

        String email =
                txtEmailRegister
                        .getText()
                        .trim();

        String login =
                txtLoginRegister
                        .getText()
                        .trim();

        String password =
                String.valueOf(
                        txtPasswordRegister
                                .getPassword()
                );

        String role =
                (String) cmbRoleRegister
                        .getSelectedItem();

        String studentId =
                txtStudentIDRegister
                        .getText()
                        .trim();

        // =================================================
        // VALIDATION
        // =================================================

        if (surname.isEmpty()
                || name.isEmpty()
                || email.isEmpty()
                || login.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =================================================
        // STUDENT VALIDATION
        // =================================================

        if (role.equals("Student")
                && studentId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID is required!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =================================================
        // REGISTER IN CONTROLLER
        // =================================================

        boolean success =
                controller.register(
                        surname,
                        name,
                        email,
                        login,
                        password,
                        role,
                        studentId
                );

        // =================================================
        // SUCCESS
        // =================================================

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            controller.showLoginFrame();

            dispose();
        }

        // =================================================
        // ERROR
        // =================================================

        else {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration failed!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPasswordRegister.setText("");
        }
    }

    // =====================================================
    // BACK TO LOGIN
    // =====================================================

    private void backToLogin() {

        controller.showLoginFrame();

        dispose();
    }

    // =====================================================
    // UPDATE ROLE FIELDS
    // =====================================================

    private void updateRoleFields() {

        String role =
                (String) cmbRoleRegister
                        .getSelectedItem();

        boolean isStudent =
                role.equals("Student");

        // =================================================
        // ENABLE / DISABLE STUDENT ID
        // =================================================

        lblStudentIDRegister
                .setEnabled(isStudent);

        txtStudentIDRegister
                .setEnabled(isStudent);

        // =================================================
        // CLEAR FIELD IF NOT STUDENT
        // =================================================

        if (!isStudent) {

            txtStudentIDRegister.setText("");
        }

        // =================================================
        // REFRESH UI
        // =================================================

        mainPanelRegister.revalidate();

        mainPanelRegister.repaint();
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        txtSurnameRegister.setText("");

        txtNameRegister.setText("");

        txtEmailRegister.setText("");

        txtLoginRegister.setText("");

        txtPasswordRegister.setText("");

        txtStudentIDRegister.setText("");

        cmbRoleRegister.setSelectedIndex(0);
    }

    // =====================================================
    // SWING CUSTOM COMPONENTS
    // =====================================================

    private void createUIComponents() {

    }
}
