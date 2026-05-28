package gui;

import controller.Controller;
import model.Aula;

import javax.swing.*;
import java.awt.*;

public class GestioneAulePanel extends JPanel {

    private JTextField txtNomeAula;
    private JTextField txtCapienza;
    private JButton btnAggiungi;
    private JList<Aula> listAule;
    private DefaultListModel<Aula> listModel;

    public GestioneAulePanel() {
        setLayout(new BorderLayout(10, 10));
        initComponents();
        caricaAule();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(new JLabel("Nome Aula:"));
        txtNomeAula = new JTextField(15);
        topPanel.add(txtNomeAula);

        topPanel.add(new JLabel("Capienza:"));
        txtCapienza = new JTextField(8);
        topPanel.add(txtCapienza);

        btnAggiungi = new JButton("Aggiungi Aula");
        topPanel.add(btnAggiungi);

        add(topPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        listAule = new JList<>(listModel);
        add(new JScrollPane(listAule), BorderLayout.CENTER);

        btnAggiungi.addActionListener(e -> aggiungiAula());
    }

    private void caricaAule() {
        listModel.clear();
        Controller.getInstance().getAuleDisponibili().forEach(listModel::addElement);
    }

    private void aggiungiAula() {
        String nome = txtNomeAula.getText().trim();
        String capienzaStr = txtCapienza.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Inserisci il nome dell'aula!", "Errore", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int capienza = 0;
        if (!capienzaStr.isEmpty()) {
            try {
                capienza = Integer.parseInt(capienzaStr);
                if (capienza <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La capienza deve essere un numero intero positivo!", "Errore", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        Controller.getInstance().aggiungiAula(nome, capienza);
        JOptionPane.showMessageDialog(this, "Aula '" + nome + "' aggiunta con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        txtNomeAula.setText("");
        txtCapienza.setText("");
        caricaAule();
    }
}
