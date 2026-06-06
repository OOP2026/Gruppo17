package gui;

import controller.Controller;
import model.utente.UserRole;

import javax.swing.*;
import java.awt.*;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(700, 600));
        setPreferredSize(new Dimension(700, 600));
        setResizable(true);

        initializeComponents();
        initializeListeners();

        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        if (cmbRoleLogin != null) {
            cmbRoleLogin.removeAllItems();
            cmbRoleLogin.addItem("Student");
            cmbRoleLogin.addItem("Teacher");
            cmbRoleLogin.addItem("Administrator");
            cmbRoleLogin.setSelectedIndex(0);
        }
    }

    private void initializeListeners() {
        if (btnOkLogin != null) btnOkLogin.addActionListener(e -> performLogin());
        if (btnClearLogin != null) btnClearLogin.addActionListener(e -> clearFields());
        if (btnRegisterLogin != null) btnRegisterLogin.addActionListener(e -> controller.showRegisterFrame());
        if (btnExitLogin != null) btnExitLogin.addActionListener(e -> System.exit(0));
    }

    private void performLogin() {
        if (txtLoginLogin == null || txtPasswordLogin == null || cmbRoleLogin == null) return;

        String username = txtLoginLogin.getText().trim();
        String password = new String(txtPasswordLogin.getPassword());
        String selectedRole = (String) cmbRoleLogin.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            showNotification("Fields cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (controller.login(username, password)) {
            if (isAuthorized(selectedRole)) {
                controller.openHomeByRole();
                dispose();
            } else {
                showNotification("Role mismatch!", "Error", JOptionPane.ERROR_MESSAGE);
                controller.logout();
            }
        } else {
            showNotification("Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            txtPasswordLogin.setText("");
        }
    }

    private boolean isAuthorized(String selectedRole) {
        UserRole actualRole = controller.getTargetHomeRole();
        if (selectedRole == null || actualRole == null) return false;

        return (selectedRole.equalsIgnoreCase("Student") && actualRole == UserRole.STUDENT) ||
                (selectedRole.equalsIgnoreCase("Teacher") && actualRole == UserRole.TEACHER) ||
                (selectedRole.equalsIgnoreCase("Administrator") && actualRole == UserRole.ADMIN);
    }

    private void showNotification(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }

    public void clearFields() {
        if (txtLoginLogin != null) txtLoginLogin.setText("");
        if (txtPasswordLogin != null) txtPasswordLogin.setText("");
        if (cmbRoleLogin != null) cmbRoleLogin.setSelectedIndex(0);
    }
}