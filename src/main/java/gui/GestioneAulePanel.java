package gui;

import controller.Controller;
import model.logistica.Aula;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestioneAulePanel extends JFrame {

    // Елементи, які автоматично зв'язуються з GUI Designer (.form)
    private JPanel mainPanelAule;
    private JLabel lblTitleAule;
    private JPanel addPanelAule;
    private JLabel lblNewAula;
    private JTextField txtNomeAula;
    private JButton btnAddAule; // Точна назва з твого GUI
    private JPanel viewPanelAule;
    private JLabel lblExistingAule;
    private JScrollPane scrollPanelAule; // Точна назва з твого GUI
    private JList<Aula> listAule;
    private JButton btnBackAule;

    // Логічні змінні класу
    private final DefaultListModel<Aula> listModel;
    private final Controller controller;
    private final JFrame parentFrame;

    public GestioneAulePanel(Controller controller, JFrame parentFrame) {
        this.controller = controller;
        this.parentFrame = parentFrame;
        this.listModel = new DefaultListModel<>();

        // Налаштування контейнера вікна
        setContentPane(mainPanelAule);
        setTitle("Manage Classrooms");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Підв'язуємо динамічну модель даних до JList
        listAule.setModel(listModel);

        // Завантажуємо наявні аудиторії та задаємо стилі
        updateClassroomList();
        setupStyles();

        // Лісенер для додавання нової аудиторії
        btnAddAule.addActionListener(e -> processAddAula());

        // Лісенер для повернення назад у головне меню
        btnBackAule.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        // Пакуємо та центруємо вікно на екрані
        pack();
        setLocationRelativeTo(null);
    }

    // Метод для оновлення списку аудиторій з контролера
    private void updateClassroomList() {
        listModel.clear();
        List<Aula> auleSistema = controller.getTutteAule();
        if (auleSistema != null) {
            for (Aula aula : auleSistema) {
                listModel.addElement(aula);
            }
        }
    }

    // Логіка обробки натискання на кнопку "Add Classroom"
    private void processAddAula() {
        String nomeAula = txtNomeAula.getText().trim();

        if (nomeAula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a classroom name.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Aula nuovaAula = new Aula(nomeAula);
            boolean success = controller.aggiungiAula(nuovaAula);

            if (success) {
                JOptionPane.showMessageDialog(this, "Classroom added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                txtNomeAula.setText("");
                updateClassroomList();
            } else {
                JOptionPane.showMessageDialog(this, "A classroom with this name already exists.", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Кастомізація зовнішнього вигляду компонентів під твою палітру
    private void setupStyles() {
        btnAddAule.setBackground(new Color(45, 62, 90));
        btnAddAule.setForeground(Color.WHITE);
        btnAddAule.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnBackAule.setBackground(new Color(86, 125, 185));
        btnBackAule.setForeground(Color.WHITE);
        btnBackAule.setFont(new Font("Segoe UI", Font.BOLD, 13));

        lblTitleAule.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitleAule.setForeground(new Color(45, 62, 90));

        listAule.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listAule.setSelectionBackground(new Color(45, 62, 90));
        listAule.setSelectionForeground(Color.WHITE);
    }
}