package com.example.app.util;

import javax.swing.*;
import java.awt.*;

/**
 * Utilitários para operações comuns de UI.
 */
public final class UIHelper {
    
    /**
     * Configura um botão com ícone, tooltip e mnemonic.
     */
    public static void configureButton(JButton button, String iconPath, String tooltip, char mnemonic) {
        ImageIcon icon = IconLoader.load(iconPath, EmailConstants.ICON_SIZE_MEDIUM);
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setToolTipText(tooltip);
        button.setMnemonic(mnemonic);
    }
    
    /**
     * Configura um botão pequeno com ícone, tooltip e mnemonic.
     */
    public static void configureSmallButton(JButton button, String iconPath, String tooltip, char mnemonic) {
        ImageIcon icon = IconLoader.load(iconPath, EmailConstants.ICON_SIZE_SMALL);
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setToolTipText(tooltip);
        button.setMnemonic(mnemonic);
    }
    
    /**
     * Cria um painel de botões alinhados à direita.
     */
    public static JPanel createButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        for (JButton button : buttons) {
            panel.add(button);
        }
        return panel;
    }
    
    /**
     * Cria um painel de botões alinhados à esquerda.
     */
    public static JPanel createLeftButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (JButton button : buttons) {
            panel.add(button);
        }
        return panel;
    }
    
    /**
     * Cria um painel de componentes alinhados à esquerda.
     */
    public static JPanel createLeftComponentPanel(Component... components) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Component component : components) {
            panel.add(component);
        }
        return panel;
    }
    
    /**
     * Configura atalhos de teclado para um componente.
     */
    public static void setupKeyBinding(JComponent component, String keyStroke, String actionName, Runnable action) {
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke(keyStroke), actionName);
        component.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                action.run();
            }
        });
    }
    
    /**
     * Mostra uma mensagem de diálogo.
     */
    public static void showMessage(Component parent, String message, String title, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }
    
    /**
     * Mostra uma mensagem de confirmação.
     */
    public static int showConfirmDialog(Component parent, String message, String title, int optionType, int messageType) {
        return JOptionPane.showConfirmDialog(parent, message, title, optionType, messageType);
    }
    
    /**
     * Mostra uma mensagem de confirmação com componente customizado.
     */
    public static int showConfirmDialog(Component parent, Component component, String title, int optionType, int messageType) {
        return JOptionPane.showConfirmDialog(parent, component, title, optionType, messageType);
    }
    
    /**
     * Mostra uma mensagem de erro.
     */
    public static void showError(Component parent, String message) {
        showMessage(parent, message, EmailConstants.MSG_ERROR, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Mostra uma mensagem de sucesso.
     */
    public static void showSuccess(Component parent, String message) {
        showMessage(parent, message, EmailConstants.MSG_SUCCESS, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Mostra uma mensagem de aviso.
     */
    public static void showWarning(Component parent, String message) {
        showMessage(parent, message, EmailConstants.MSG_VALIDATION, JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Cria um GridBagConstraints padrão.
     */
    public static GridBagConstraints createDefaultConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }
    
    /**
     * Adiciona um componente ao painel usando GridBagLayout.
     */
    public static void addToGridBag(JPanel panel, Component component, GridBagConstraints gbc, int x, int y, double weightx) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = weightx;
        panel.add(component, gbc);
    }
    
    /**
     * Adiciona um label e campo ao painel usando GridBagLayout.
     */
    public static void addLabelAndField(JPanel panel, String labelText, Component field, GridBagConstraints gbc, int row) {
        addToGridBag(panel, new JLabel(labelText), gbc, 0, row, 0);
        addToGridBag(panel, field, gbc, 1, row, 1);
    }
    
    private UIHelper() {
    }
} 