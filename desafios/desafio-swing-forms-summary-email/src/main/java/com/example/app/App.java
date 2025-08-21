package com.example.app;

import javax.swing.*;

/** Ponto de entrada da aplicação. */
public class App {
    public static void main(String[] args) {
        // Tenta aplicar Nimbus antes de criar a UI
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) { }

        SwingUtilities.invokeLater(() -> {
            com.example.app.ui.MainFrame frame = new com.example.app.ui.MainFrame();
            frame.setVisible(true);
        });
    }
}