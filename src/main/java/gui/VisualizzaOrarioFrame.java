package gui;

import controller.Controller;
import model.utente.Utente;
import model.utente.UserRole;
import model.didattica.Lezione;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisualizzaOrarioFrame extends JFrame {

    private JPanel mainPanelOrario;
    private JLabel lblTitleOrario;
    private JScrollPane scrollPaneOrario;
    private JTable tblOrario;
    private JPanel buttonPanelOrario;
    private JButton btnBackOrario;
    private JPanel filterPanelOrario;
    private JComboBox comboBox1;
    private JComboBox<String> cmbFilter;

    private final Controller controller;
    private final Utente utente;
    private final JFrame parentFrame;

    public VisualizzaOrarioFrame(Controller controller, Utente utente, JFrame parentFrame) {
        this.controller = controller;
        this.utente = utente;
        this.parentFrame = parentFrame;

        setContentPane(mainPanelOrario);
        setTitle("University Timetable Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        adaptInterfaceByRole();
        loadTimetableData();

        btnBackOrario.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        btnBackOrario.setBackground(new Color(45, 62, 90));
        btnBackOrario.setForeground(Color.WHITE);
        btnBackOrario.setFont(new Font("Segoe UI", Font.BOLD, 14));

        pack();
        setSize(800, 480);
        setLocationRelativeTo(null);
    }

    private void adaptInterfaceByRole() {
        UserRole role = controller.getTargetHomeRole();

        if (role == UserRole.STUDENT) {
            lblTitleOrario.setText("Timetable - Student View (Your Course Year)");
            filterPanelOrario.setVisible(false);
        } else if (role == UserRole.TEACHER) {
            lblTitleOrario.setText("Timetable - Teacher View (My Lessons)");
            filterPanelOrario.setVisible(false);
        } else {
            lblTitleOrario.setText("Timetable - Administrator Master View");
            cmbFilter.removeAllItems();
            cmbFilter.addItem("All Lessons");
            cmbFilter.addItem("1st Year");
            cmbFilter.addItem("2nd Year");
            cmbFilter.addItem("3rd Year");

            cmbFilter.addActionListener(e -> loadTimetableData());
        }
    }

    private void loadTimetableData() {
        String[] columnNames = {"Subject", "Day", "Start Time", "End Time", "Classroom", "Teacher"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Lezione> lezioni = controller.getLezioniForUser(utente);

        if (lezioni != null) {
            String selectedFilter = (cmbFilter != null && cmbFilter.getSelectedItem() != null)
                    ? cmbFilter.getSelectedItem().toString() : "All Lessons";

            for (Lezione lez : lezioni) {

                if (controller.getTargetHomeRole() == UserRole.ADMIN) {
                    String annoString = lez.getInsegnamento().getAnnoCorso().toString();
                    if (selectedFilter.equals("1st Year") && !annoString.equalsIgnoreCase("PRIMO")) continue;
                    if (selectedFilter.equals("2nd Year") && !annoString.equalsIgnoreCase("SECONDO")) continue;