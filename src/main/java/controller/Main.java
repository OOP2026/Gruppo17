package controller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Ensuring the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize the controller
                Controller controller = new Controller();

                // Start the application lifecycle
                controller.startApplication();

                System.out.println("Application started successfully.");

            } catch (Exception e) {
                System.err.println("Failed to start the application:");
                e.printStackTrace();

                JOptionPane.showMessageDialog(null,
                        "Critical error during startup: " + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}