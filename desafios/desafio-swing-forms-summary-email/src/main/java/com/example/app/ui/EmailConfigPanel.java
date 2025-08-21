package com.example.app.ui;

import com.example.app.service.EmailConfig;
import com.example.app.service.EmailService;
import com.example.app.util.EmailConstants;
import com.example.app.util.UIHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Painel para configuração das opções de e-mail.
 */
public class EmailConfigPanel extends JPanel {
    
    private final EmailConfig config;
    private final EmailService emailService;
    
    // Campos de entrada
    private final JTextField txtSmtpHost = new JTextField(EmailConstants.FIELD_SIZE_SMTP_HOST);
    private final JTextField txtSmtpPort = new JTextField(EmailConstants.FIELD_SIZE_PORT);
    private final JTextField txtUsername = new JTextField(EmailConstants.FIELD_SIZE_USERNAME);
    private final JPasswordField txtPassword = new JPasswordField(EmailConstants.FIELD_SIZE_PASSWORD);
    private final JTextField txtFromEmail = new JTextField(EmailConstants.FIELD_SIZE_EMAIL);
    private final JTextField txtToEmail = new JTextField(EmailConstants.FIELD_SIZE_EMAIL);
    private final JCheckBox chkUseSsl = new JCheckBox(EmailConstants.LABEL_USE_SSL);
    private final JCheckBox chkUseTls = new JCheckBox(EmailConstants.LABEL_USE_TLS);
    
    // Botões
    private final JButton btnSave = new JButton(EmailConstants.BTN_SAVE);
    private final JButton btnTest = new JButton(EmailConstants.BTN_TEST);


    public EmailConfigPanel() {
        this.config = new EmailConfig();
        this.emailService = new EmailService(config);
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(EmailConstants.MSG_EMAIL_CONFIG));
        
        initComponents();
        loadConfig();
        setupActions();
    }
    
    private void initComponents() {
        // Painel principal com GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = UIHelper.createDefaultConstraints();
        
        // Servidor SMTP
        UIHelper.addLabelAndField(mainPanel, EmailConstants.LABEL_SMTP_HOST, txtSmtpHost, gbc, 0);
        UIHelper.addLabelAndField(mainPanel, EmailConstants.LABEL_PORT, txtSmtpPort, gbc, 1);
        
        // Autenticação
        UIHelper.addLabelAndField(mainPanel, EmailConstants.LABEL_USERNAME, txtUsername, gbc, 2);
        UIHelper.addLabelAndField(mainPanel, EmailConstants.LABEL_PASSWORD, txtPassword, gbc, 3);
        
        // E-mail
        UIHelper.addLabelAndField(mainPanel, EmailConstants.LABEL_FROM_EMAIL, txtFromEmail, gbc, 4);

        // Opções de segurança
        JPanel securityPanel = UIHelper.createLeftComponentPanel(chkUseSsl, chkUseTls);
        UIHelper.addLabelAndField(mainPanel, EmailConstants.LABEL_SECURITY, securityPanel, gbc, 6);
        
        // Botões
        UIHelper.configureSmallButton(btnSave, EmailConstants.ICON_SAVE, EmailConstants.TOOLTIP_SAVE, 'S');
        UIHelper.configureSmallButton(btnTest, EmailConstants.ICON_TEST, EmailConstants.TOOLTIP_TEST, 'T');
        
        JPanel buttonPanel = UIHelper.createButtonPanel(btnTest, btnSave);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadConfig() {
        txtSmtpHost.setText(config.getSmtpHost());
        txtSmtpPort.setText(config.getSmtpPort());
        txtUsername.setText(config.getUsername());
        txtPassword.setText(config.getPassword());
        txtFromEmail.setText(config.getFromEmail());
        txtToEmail.setText(config.getToEmail());
        chkUseSsl.setSelected(config.isUseSsl());
        chkUseTls.setSelected(config.isUseTls());
    }
    
    private void setupActions() {
        btnSave.addActionListener(e -> saveConfig());
        btnTest.addActionListener(e -> testConnection());
        
        // Atalhos de teclado
        UIHelper.setupKeyBinding(this, EmailConstants.KEY_SAVE, "save", this::saveConfig);
    }
    
    private void saveConfig() {
        config.setSmtpHost(txtSmtpHost.getText().trim());
        config.setSmtpPort(txtSmtpPort.getText().trim());
        config.setUsername(txtUsername.getText().trim());
        config.setPassword(new String(txtPassword.getPassword()));
        config.setFromEmail(txtFromEmail.getText().trim());
        config.setToEmail(txtToEmail.getText().trim());
        config.setUseSsl(chkUseSsl.isSelected());
        config.setUseTls(chkUseTls.isSelected());
        
        UIHelper.showSuccess(this, EmailConstants.MSG_CONFIG_SAVED);
    }
    
    private void testConnection() {
        btnTest.setEnabled(false);
        btnTest.setText(EmailConstants.BTN_TESTING);
        
        // Executa o teste em uma thread separada para não travar a UI
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return emailService.testConnection();
            }
            
            @Override
            protected void done() {
                btnTest.setEnabled(true);
                btnTest.setText(EmailConstants.BTN_TEST);
                
                try {
                    boolean success = get();
                    String message = success ? 
                            EmailConstants.MSG_CONNECTION_SUCCESS : 
                            EmailConstants.MSG_CONNECTION_FAILURE;
                    
                    if (success) {
                        UIHelper.showSuccess(EmailConfigPanel.this, message);
                    } else {
                        UIHelper.showError(EmailConfigPanel.this, message);
                    }
                } catch (Exception ex) {
                    UIHelper.showError(EmailConfigPanel.this, 
                            "Erro ao testar conexão: " + ex.getMessage());
                }
            }
        };
        
        worker.execute();
    }
    
    public boolean isConfigured() {
        return config.isConfigured();
    }
    
    public EmailService getEmailService() {
        return emailService;
    }
} 