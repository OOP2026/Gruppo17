package gui;

import controller.Controller;
import model.didattica.AnnoCorso;
import model.utente.UserRole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class RegisterFrame extends JFrame {

    private JPanel mainPanelRegister;
    private JLabel lblTitleRegister;

    private JPanel formPanelRegister;
    private JLabel lblSurnameRegister;
    private JTextField txtSurnameRegister;
    private JLabel lblNameRegister;
    private JTextField txtNameRegister;
    private JLabel lblEmailRegister;
    private JTextField txtEmailRegister;
    private JLabel lblLoginRegister;
    private JTextField txtLoginRegister;
    private JLabel lblPasswordRegister;
    private JPasswordField txtPasswordRegister;
    private JLabel lblRoleRegister;
    private JComboBox<String> cmbRoleRegister;
    private JLabel lblStudentIDRegister;
    private JTextField txtStudentIDRegister;
    private JLabel lblAnnoCorsoRegister;
    private JComboBox<String> cmbAnnoCorsoRegister;

    private JPanel buttonPanelRegister;
    private JButton btnRegister;
    private JButton btnBackRegister;

    private final Controller controller;


    public RegisterFrame(Controller controller) {

        this.controller = controller;

        setContentPane(mainPanelRegister);
        setTitle("University Timetable Manager - Registration");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(700, 600));
        setPreferredSize(new Dimension(700, 600));
        setResizable(true);

        initializeComboBoxes();
        initializeListeners();
        toggleStudentFields();

        pack();
        setLocationRelativeTo(null);
    }


    private void initializeComboBoxes() {

        cmbRoleRegister.removeAllItems();
        cmbRoleRegister.addItem("Student");
        cmbRoleRegister.addItem("Teacher");
        cmbRoleRegister.addItem("Administrator");
        cmbRoleRegister.setSelectedIndex(0);

        cmbAnnoCorsoRegister.removeAllItems();
        cmbAnnoCorsoRegister.addItem("First Year");
        cmbAnnoCorsoRegister.addItem("Second Year");
        cmbAnnoCorsoRegister.addItem("Third Year");
        cmbAnnoCorsoRegister.setSelectedIndex(0);
    }


    private void initializeListeners() {

        cmbRoleRegister.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                toggleStudentFields();
            }
        });

        btnRegister.addActionListener(e -> register());

        btnBackRegister.addActionListener(e -> controller.showLoginFrame());
    }


    private void toggleStudentFields() {

        String selectedRole = (String) cmbRoleRegister.getSelectedItem();
        boolean isStudent = "Student".equals(selectedRole);

        lblStudentIDRegister.setVisible(isStudent);
        txtStudentIDRegister.setVisible(isStudent);
        lblAnnoCorsoRegister.setVisible(isStudent);
        cmbAnnoCorsoRegister.setVisible(isStudent);

        pack();
        setLocationRelativeTo(null);
    }


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
            String yearStr = (String) cmbAnnoCorsoRegister.getSelectedItem();

            if (studentId.isEmpty() || yearStr == null) {

                showWarning("Please provide Student ID and Year of Course!");
                return;
            }

            if ("First Year".equals(yearStr)) {
                annoCorso = AnnoCorso.PRIMO;
            } else if ("Second Year".equals(yearStr)) {
                annoCorso = AnnoCorso.SECONDO;
            } else if ("Third Year".equals(yearStr)) {
                annoCorso = AnnoCorso.TERZO;
            }
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
        cmbAnnoCorsoRegister.setSelectedIndex(0);
    }
}