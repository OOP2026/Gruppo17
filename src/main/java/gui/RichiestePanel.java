package gui;

import controller.Controller;
import model.richiesta.RichiestaSpostamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RichiestePanel extends JFrame {

    private JPanel mainPanelRichieste;
    private JLabel lblTitleRichieste;
    private JPanel tablePanelRichieste;
    private JLabel lblSubtitleRichieste;
    private JScrollPane scrollPanelRichieste;
    private JTable tableRichieste;
    private JButton btnApproveRichieste;
    private JButton btnRejectRichieste;
    private JButton btnBackRichieste;

    private final Controller controller;
    private final JFrame parentFrame;
    private DefaultTableModel tableModel;

    // ВИПРАВЛЕНО: назва конструктора тепер збігається з назвою класу
    public RichiestePanel(Controller controller, JFrame parentFrame) {

        this.controller = controller;
        this.parentFrame = parentFrame;

        setContentPane(mainPanelRichieste);
        setTitle("Teacher Requests Management");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setupTable();
        setupStyles();
        refreshTableData();

        btnApproveRichieste.addActionListener(e -> processRequest(true));
        btnRejectRichieste.addActionListener(e -> processRequest(false));

        btnBackRichieste.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void setupTable() {

        String[] columnNames = {
                "Professor",
                "Reason",
                "Status",
                "Current Slot",
                "Proposed Slot",
                "Request Date"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableRichieste.setModel(tableModel);
        tableRichieste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableRichieste.setRowSelectionAllowed(true);
    }

    private void refreshTableData() {

        tableModel.setRowCount(0);

        Object[][] data = controller.getDatiTabellaRichieste();
        if (data != null) {
            for (Object[] row : data) {
                tableModel.addRow(row);
            }
        }
    }

    private void processRequest(boolean approve) {

        int selectedRow = tableRichieste.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a request from the table first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<RichiestaSpostamento> listRichieste = controller.getRichieste();
        if (listRichieste == null || selectedRow >= listRichieste.size()) {
            return;
        }

        RichiestaSpostamento richiestaSelezionata = listRichieste.get(selectedRow);

        if (approve) {
            boolean success = controller.approvaRichiesta(richiestaSelezionata);
            if (success) {
                JOptionPane.showMessageDialog(this, "Request approved! Schedule updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cannot approve request due to schedule or room conflict!", "Conflict Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            controller.rifiutaRichiesta(richiestaSelezionata);
            JOptionPane.showMessageDialog(this, "Request rejected successfully.", "Status Updated", JOptionPane.INFORMATION_MESSAGE);
        }

        refreshTableData();
    }

    private void setupStyles() {

        lblTitleRichieste.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitleRichieste.setForeground(new Color(45, 62, 90));

        lblSubtitleRichieste.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        btnApproveRichieste.setBackground(new Color(45, 62, 90));
        btnApproveRichieste.setForeground(Color.WHITE);
        btnApproveRichieste.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnRejectRichieste.setBackground(new Color(140, 40, 50));
        btnRejectRichieste.setForeground(Color.WHITE);
        btnRejectRichieste.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnBackRichieste.setBackground(new Color(86, 125, 185));
        btnBackRichieste.setForeground(Color.WHITE);
        btnBackRichieste.setFont(new Font("Segoe UI", Font.BOLD, 13));

        tableRichieste.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableRichieste.setRowHeight(24);

        tableRichieste.setSelectionBackground(new Color(86, 125, 185));
        tableRichieste.setSelectionForeground(Color.WHITE);
        tableRichieste.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
    }
}