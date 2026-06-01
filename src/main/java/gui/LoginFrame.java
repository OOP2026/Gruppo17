package gui;

import controller.Controller;
import model.utente.UserRole;

import javax.swing.*;

public class LoginFrame extends JFrame {

    // =====================================================
    // COMPONENTS (Повністю синхронізовано з 777.jpg)
    // =====================================================
    private JPanel mainPanel;
    private JLabel lblTitle;

    // Синя панель форми та її вміст
    private JPanel formPanel;
    private JLabel lblRole;
    private JComboBox<String> cmbRole;
    private JLabel lblLogin;
    private JTextField txtLogin;
    private JLabel lblPassword;
    private JPasswordField txtPassword;

    // Нижня панель для кнопок та самі кнопки
    private JPanel buttonPanel;
    private JButton btnOk;
    private JButton btnClear;
    private JButton btnRegister;
    private JButton btnExit;

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

        // Підганяємо розмір автоматично або залишаємо твій фіксований:
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        initializeComboBox();
        initializeListeners();
    }

    // =====================================================
    // COMBOBOX
    // =====================================================
    private void initializeComboBox() {
        cmbRole.removeAllItems();
        cmbRole.addItem("Student");
        cmbRole.addItem("Teacher");
        cmbRole.addItem("Administrator");
        cmbRole.setSelectedIndex(0);
    }

    // =====================================================
    // LISTENERS
    // =====================================================
    private void initializeListeners() {
        btnOk.addActionListener(e -> login());
        btnClear.addActionListener(e -> clearFields());
        btnRegister.addActionListener(e -> controller.showRegisterFrame());
        btnExit.addActionListener(e -> System.exit(0));
    }

    // =====================================================
    // LOGIN LOGIC
    // =====================================================
    private void login() {
        String login = txtLogin.getText().trim();
        String password = String.valueOf(txtPassword.getPassword());
        String selectedRole = (String) cmbRole.getSelectedItem();

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
            txtPassword.setText("");
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================
    public void clearFields() {
        txtLogin.setText("");
        txtPassword.setText("");
        cmbRole.setSelectedIndex(0);
    }
}