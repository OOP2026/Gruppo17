package gui;

import controller.Controller;
import model.didattica.AnnoCorso;
import model.utente.UserRole;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class RegisterFrame extends JFrame {

    // =====================================================
    // COMPONENTS
    // =====================================================

    private JPanel mainPanelRegister;

    private JPanel formPanelRegister;

    private JLabel lblSurnameRegister;

    private JLabel lblNameRegister;

    private JLabel lblEmailRegister;

    private JLabel lblLoginRegister;

    private JLabel lblPasswordRegister;

    private JLabel lblRoleRegister;

    private JTextField txtStudentIDRegister;

    private JComboBox<String> cmbRoleRegister;

    private JPasswordField txtPasswordRegister;

    private JTextField txtLoginRegister;

    private JTextField txtEmailRegister;

    private JTextField txtNameRegister;

    private JTextField txtSurnameRegister;

    private JComboBox<String> cmbAnnoCorso;

    private JButton btnBackRegister;

    private JButton btnRegister;

    private JLabel lblAnnoCorso;

    private JLabel lblTitleRegister;

    // =====================================================
    // CONTROLLER
    // =====================================================

    private final Controller controller;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RegisterFrame(Controller controller) {

        this.controller = controller;

        setContentPane(mainPanelRegister);

        setTitle("University Timetable Manager - Registration");

        setSize(850, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);

        initializeComboBoxes();

        initializeListeners();

        toggleStudentFields();
    }

    // =====================================================
    // COMBOBOXES INITIALIZATION
    // =====================================================

    private void initializeComboBoxes() {

        cmbRoleRegister.removeAllItems();
        cmbRoleRegister.addItem("Student");
        cmbRoleRegister.addItem("Teacher");
        cmbRoleRegister.addItem("Administrator");
        cmbRoleRegister.setSelectedIndex(0);

        cmbAnnoCorso.removeAllItems();
        cmbAnnoCorso.addItem("First Year");
        cmbAnnoCorso.addItem("Second Year");
        cmbAnnoCorso.addItem("Third Year");
        cmbAnnoCorso.addItem("Fourth Year");
        cmbAnnoCorso.addItem("Fifth Year");
        cmbAnnoCorso.setSelectedIndex(0);
    }

    // =====================================================
    // LISTENERS
    // =====================================================

    private void initializeListeners() {

        cmbRoleRegister.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                toggleStudentFields();
            }
        });

        btnRegister.addActionListener(e -> register());

        btnBackRegister.addActionListener(e -> controller.showLoginFrame());
    }

    // =====================================================
    // TOGGLE STUDENT FIELDS
    // =====================================================

    private void toggleStudentFields() {

        String selectedRole = (String) cmbRoleRegister.getSelectedItem();
        boolean isStudent = "Student".equals(selectedRole);

        lblAnnoCorso.setVisible(isStudent);
        cmbAnnoCorso.setVisible(isStudent);
        lblTitleRegister.setVisible(isStudent);
        txtStudentIDRegister.setVisible(isStudent);

        revalidate();
        repaint();
    }

    // =====================================================
    // REGISTER LOGIC
    // =====================================================

    private void register() {

        String name = txtNameRegister.getText().trim();
        String surname = txtSurnameRegister.getText().trim();
        String email = txtEmailRegister.getText().trim();
        String login = txtLoginRegister.getText().trim();
        String password = String.valueOf(txtPasswordRegister.getPassword());
        String roleStr = (String) cmbRoleRegister.getSelectedItem();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            showWarning("All standard fields must be filled!");
            return;
        }

        UserRole role = null;
        AnnoCorso annoCorso = null;
        String studentId = null;

        if ("Student".equals(roleStr)) {
            role = UserRole.STUDENT;
        } else if ("Teacher".equals(roleStr)) {
            role = UserRole.TEACHER;
        } else if ("Administrator".equals(roleStr)) {
            role = UserRole.ADMIN;
        }

        if (role == UserRole.STUDENT) {
            studentId = txtStudentIDRegister.getText().trim();
            String yearStr = (String) cmbAnnoCorso.getSelectedItem();

            if (studentId.isEmpty() || yearStr == null) {
                showWarning("Please provide Student ID and Year of Course!");
                return;
            }

            if ("First Year".equals(yearStr)) annoCorso = AnnoCorso.PRIMO;
            else if ("Second Year".equals(yearStr)) annoCorso = AnnoCorso.SECONDO;
            else if ("Third Year".equals(yearStr)) annoCorso = AnnoCorso.TERZO;

        }

        boolean success = controller.register(
                name,
                surname,
                email,
                login,
                password,
                role,
                studentId,
                annoCorso
        );

        if (success) {
            JOptionPane.showMessageDialog(
                    this,
                    "Registration successful! You can now log in.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            controller.showLoginFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Registration failed! Login might already be taken.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // HELPER METHODS
    // =====================================================

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void clearFields() {
        txtNameRegister.setText("");
        txtSurnameRegister.setText("");
        txtEmailRegister.setText("");
        txtLoginRegister.setText("");
        txtPasswordRegister.setText("");
        txtStudentIDRegister.setText("");
        cmbRoleRegister.setSelectedIndex(0);
        cmbAnnoCorso.setSelectedIndex(0);
    }

    private void createUIComponents() {

    }
}