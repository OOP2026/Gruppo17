package gui;

import controller.Controller;
import model.utente.Utente;
import model.utente.UserRole;
import model.utente.Docente;
import model.didattica.Lezione;
import model.didattica.Insegnamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisualizzaOrarioFrame extends JFrame {

    private JPanel mainPanelOrario;
    private JLabel lblTitleOrario;
    private JScrollPane scrollPanelOrario;
    private JTable tblOrario;
    private JPanel buttonPanelOrario;
    private JButton btnBackOrario;
    private JPanel filterPanelOrario;
    private JComboBox<String> cmbFilterYearOrario;
    private JComboBox<String> cmbFilterSubjectOrario;
    private JComboBox<String> cmbFilterTeacherOrario;
    private JButton btnCloseOrario;

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
        setupDynamicFilters();
        loadTimetableData();

        btnBackOrario.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        btnCloseOrario.addActionListener(e -> {
            System.exit(0);
        });

        btnBackOrario.setBackground(new Color(45, 62, 90));
        btnBackOrario.setForeground(Color.WHITE);
        btnBackOrario.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnCloseOrario.setBackground(new Color(140, 40, 50));
        btnCloseOrario.setForeground(Color.WHITE);
        btnCloseOrario.setFont(new Font("Segoe UI", Font.BOLD, 14));

        pack();
        setSize(950, 520);
        setLocationRelativeTo(null);
    }

    private void adaptInterfaceByRole() {
        UserRole role = controller.getTargetHomeRole();

        if (role == UserRole.STUDENT) {
            lblTitleOrario.setText("Timetable - Student View");
            cmbFilterYearOrario.setVisible(false);
        } else if (role == UserRole.TEACHER) {
            lblTitleOrario.setText("Timetable - Teacher View");
            cmbFilterTeacherOrario.setVisible(false);
        } else {
            lblTitleOrario.setText("Timetable - Administrator Master View");

            cmbFilterYearOrario.removeAllItems();
            cmbFilterYearOrario.addItem("All Years");
            cmbFilterYearOrario.addItem("1st Year");
            cmbFilterYearOrario.addItem("2nd Year");
            cmbFilterYearOrario.addItem("3rd Year");

            cmbFilterYearOrario.addActionListener(e -> loadTimetableData());
        }
    }

    private void setupDynamicFilters() {
        cmbFilterSubjectOrario.removeAllItems();
        cmbFilterSubjectOrario.addItem("All Subjects");

        cmbFilterTeacherOrario.removeAllItems();
        cmbFilterTeacherOrario.addItem("All Teachers");

        List<Lezione> lezioniPerUtente = controller.getLezioniForUser(utente);
        if (lezioniPerUtente == null) return;

        for (Lezione lez : lezioniPerUtente) {
            Insegnamento ins = lez.getInsegnamento();

            boolean subjectExists = false;
            for (int i = 0; i < cmbFilterSubjectOrario.getItemCount(); i++) {
                if (cmbFilterSubjectOrario.getItemAt(i).equals(ins.getNomeInsegnamento())) {
                    subjectExists = true;
                    break;
                }
            }
            if (!subjectExists) {
                cmbFilterSubjectOrario.addItem(ins.getNomeInsegnamento());
            }

            Docente doc = ins.getDocenteTitolare();
            String fullName = doc.getCognome() + " " + doc.getNome();

            boolean teacherExists = false;
            for (int i = 0; i < cmbFilterTeacherOrario.getItemCount(); i++) {
                if (cmbFilterTeacherOrario.getItemAt(i).equals(fullName)) {
                    teacherExists = true;
                    break;
                }
            }
            if (!teacherExists) {
                cmbFilterTeacherOrario.addItem(fullName);
            }
        }

        cmbFilterSubjectOrario.addActionListener(e -> loadTimetableData());
        cmbFilterTeacherOrario.addActionListener(e -> loadTimetableData());
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
            String selectedYear = (cmbFilterYearOrario != null && cmbFilterYearOrario.getSelectedItem() != null)
                    ? cmbFilterYearOrario.getSelectedItem().toString() : "All Years";
            String selectedSubject = (cmbFilterSubjectOrario != null && cmbFilterSubjectOrario.getSelectedItem() != null)
                    ? cmbFilterSubjectOrario.getSelectedItem().toString() : "All Subjects";
            String selectedTeacher = (cmbFilterTeacherOrario != null && cmbFilterTeacherOrario.getSelectedItem() != null)
                    ? cmbFilterTeacherOrario.getSelectedItem().toString() : "All Teachers";

            for (Lezione lez : lezioni) {
                UserRole role = controller.getTargetHomeRole();

                if (role == UserRole.ADMIN && !selectedYear.equals("All Years")) {
                    String annoString = lez.getInsegnamento().getAnnoCorso().toString();
                    if (selectedYear.equals("1st Year") && !annoString.equalsIgnoreCase("PRIMO")) continue;
                    if (selectedYear.equals("2nd Year") && !annoString.equalsIgnoreCase("SECONDO")) continue;
                    if (selectedYear.equals("3rd Year") && !annoString.equalsIgnoreCase("TERZO")) continue;
                }

                if (!selectedSubject.equals("All Subjects")) {
                    String currentSubject = lez.getInsegnamento().getNomeInsegnamento();
                    if (!currentSubject.equals(selectedSubject)) continue;
                }

                if (role != UserRole.TEACHER && !selectedTeacher.equals("All Teachers")) {
                    String currentTeacher = lez.getInsegnamento().getDocenteTitolare().getCognome() + " " +
                            lez.getInsegnamento().getDocenteTitolare().getNome();
                    if (!currentTeacher.equals(selectedTeacher)) continue;
                }

                Object[] rowData = {
                        lez.getInsegnamento().getNomeInsegnamento(),
                        lez.getGiornoSettimana().toString(),
                        lez.getOraInizio().toString(),
                        lez.getOraFine().toString(),
                        lez.getAula().getNomeAula(),
                        lez.getInsegnamento().getDocenteTitolare().getCognome() + " " +
                                lez.getInsegnamento().getDocenteTitolare().getNome()
                };
                tableModel.addRow(rowData);
            }
        }

        tblOrario.setModel(tableModel);
    }
}