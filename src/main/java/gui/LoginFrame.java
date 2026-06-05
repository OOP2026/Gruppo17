package gui;

import controller.Controller;
import model.utente.UserRole;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private JPanel mainPanelLogin;
    private JLabel lblTitleLogin;

    private JPanel formPanelLogin;
    private JLabel lblRoleLogin;
    private JComboBox<String> cmbRoleLogin;
    private JLabel lblLoginLogin;
    private JLabel lblPasswordLogin;
    private JTextField txtLoginLogin;
    private JPasswordField txtPasswordLogin;

    private JPanel buttonPanelLogin;
    private JButton btnOkLogin;
    private JButton btnClearLogin;
    private JButton btnRegisterLogin;
    private JButton btnExitLogin;

    private final Controller controller;


    public LoginFrame(Controller controller) {

        this.controller = controller;

        setContentPane(mainPanelLogin);
        setTitle("University Timetable Manager");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        initializeComboBox();
        initializeListeners();

        pack();
        setLocationRelativeTo(null);
    }


    private void initializeComboBox() {

        cmbRoleLogin.removeAllItems();
        cmbRoleLogin.addItem("Student");
        cmbRoleLogin.addItem("Teacher");
        cmbRoleLogin.addItem("Administrator");
        cmbRoleLogin.setSelectedIndex(0);
    }


    private void initializeListeners() {

        btnOkLogin.addActionListener(e -> login());

        btnClearLogin.addActionListener(e -> clearFields());

        btnRegisterLogin.addActionListener(e -> controller.showRegisterFrame());

        btnExitLogin.addActionListener(e -> System.exit(0));
    }


    private void login() {

        String login = txtLoginLogin.getText().trim();
        String password = String.valueOf(txtPasswordLogin.getPassword());
        String selectedRole = (String) cmbRoleLogin.getSelectedItem();


        if (login.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fields cannot be empty!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }


        boolean success = controller.login(login, password);


        if (success) {

            UserRole actualRole = controller.getTargetHomeRole();
            boolean roleMatches = false;

            if ("Student".equals(selectedRole) && actualRole == UserRole.STUDENT) {
                roleMatches = true;

            } else if ("Teacher".equals(selectedRole) && actualRole == UserRole.TEACHER) {
                roleMatches = true;

            } else if ("Administrator".equals(selectedRole) && actualRole == UserRole.ADMIN) {
                roleMatches = true;
            }

            if (!roleMatches) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selected role does not match this user account type!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                controller.logout();
                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            controller.openHomeByRole();
            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Wrong login or password!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            txtPasswordLogin.setText("");
        }
    }


    public void clearFields() {

        txtLoginLogin.setText("");
        txtPasswordLogin.setText("");
        cmbRoleLogin.setSelectedIndex(0);
    }
}