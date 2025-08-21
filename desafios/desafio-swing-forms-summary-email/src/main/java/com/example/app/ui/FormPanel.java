package com.example.app.ui;

import com.example.app.model.Message;
import com.example.app.service.EmailService;
import com.example.app.util.EmailConstants;
import com.example.app.util.UIHelper;
import com.example.app.util.Validation;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/** Tela 1: Formulário. */
public class FormPanel extends JPanel {

    private final JTextField txtName = new JTextField(EmailConstants.FIELD_SIZE_NAME);
    private final JTextField txtEmail = new JTextField(EmailConstants.FIELD_SIZE_EMAIL);
    private final JTextArea txtBody = new JTextArea(EmailConstants.FIELD_SIZE_MESSAGE_ROWS, EmailConstants.FIELD_SIZE_MESSAGE_COLS);
    private final JButton btnSend = new JButton(EmailConstants.BTN_SEND);
    private final JButton btnConfig = new JButton(EmailConstants.BTN_CONFIG);

    private Consumer<Message> onSubmit = m -> {};
    private EmailService emailService;

    public FormPanel() {
        super(new BorderLayout(10, 10));
        
        // Inicializa o serviço de e-mail
        this.emailService = new EmailService();

        // Centro: formulário com GridBagLayout
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = UIHelper.createDefaultConstraints();
        gbc.insets = new Insets(6,6,6,6);

        JLabel lblName = new JLabel(EmailConstants.LABEL_NAME);
        JLabel lblEmail = new JLabel(EmailConstants.LABEL_EMAIL);
        JLabel lblMsg = new JLabel(EmailConstants.LABEL_MESSAGE);

        txtBody.setLineWrap(true);
        txtBody.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtBody);

        UIHelper.addLabelAndField(form, EmailConstants.LABEL_NAME, txtName, gbc, 0);
        UIHelper.addLabelAndField(form, EmailConstants.LABEL_EMAIL, txtEmail, gbc, 1);
        UIHelper.addLabelAndField(form, EmailConstants.LABEL_MESSAGE, scroll, gbc, 2);

        // Rodapé: botões com ícones, tooltips e atalhos
        UIHelper.configureButton(btnConfig, EmailConstants.ICON_SEND, EmailConstants.TOOLTIP_CONFIG, 'C');
        UIHelper.configureButton(btnSend, EmailConstants.ICON_SEND, EmailConstants.TOOLTIP_SEND, 'E');
        
        JPanel footer = UIHelper.createButtonPanel(btnConfig, btnSend);

        // Ações dos botões
        btnSend.addActionListener(e -> trySubmit());
        btnConfig.addActionListener(e -> showEmailConfig());

        // Atalho Ctrl+Enter
        UIHelper.setupKeyBinding(this, EmailConstants.KEY_SEND, "send", this::trySubmit);

        add(form, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    public void setOnSubmit(java.util.function.Consumer<Message> onSubmit) {
        this.onSubmit = onSubmit != null ? onSubmit : m -> {};
    }

    public void prefillName(String name) {
        txtName.setText(name == null ? "" : name);
    }

    private void trySubmit() {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String body = txtBody.getText().trim();

        if (!Validation.notBlank(name) || !Validation.isEmail(email) || !Validation.notBlank(body)) {
            UIHelper.showWarning(this, EmailConstants.MSG_VALIDATION_ERROR);
            return;
        }

        Message m = new Message(name, email, body);
        
        // Tenta enviar o e-mail
        if (emailService.getConfig().isConfigured()) {
            sendEmail(m);
        } else {
            int choice = UIHelper.showConfirmDialog(this,
                    EmailConstants.MSG_EMAIL_NOT_CONFIGURED,
                    EmailConstants.MSG_CONFIG_REQUIRED,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            
            if (choice == JOptionPane.YES_OPTION) {
                showEmailConfig();
            }
        }
        
        // Chama o callback original
        onSubmit.accept(m);

        txtName.setText("");
        txtEmail.setText("");
        txtBody.setText("");
        txtName.setFocusable(true);
    }
    
    private void sendEmail(Message message) {
        btnSend.setEnabled(false);
        btnSend.setText(EmailConstants.BTN_SENDING);
        
        // Executa o envio em uma thread separada para não travar a UI
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return emailService.sendEmail(message);
            }
            
            @Override
            protected void done() {
                btnSend.setEnabled(true);
                btnSend.setText(EmailConstants.BTN_SEND);
                
                try {
                    boolean success = get();
                    String message = success ? 
                            EmailConstants.MSG_EMAIL_SENT_SUCCESS : 
                            EmailConstants.MSG_EMAIL_SENT_FAILURE;
                    
                    if (success) {
                        UIHelper.showSuccess(FormPanel.this, message);
                    } else {
                        UIHelper.showError(FormPanel.this, message);
                    }
                } catch (Exception ex) {
                    UIHelper.showError(FormPanel.this, 
                            "Erro ao enviar e-mail: " + ex.getMessage());
                }
            }
        };
        
        worker.execute();
    }
    
    private void showEmailConfig() {
        EmailConfigPanel configPanel = new EmailConfigPanel();
        
        int result = UIHelper.showConfirmDialog(this,
                configPanel,
                EmailConstants.MSG_EMAIL_CONFIG,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            // Atualiza o serviço de e-mail com as novas configurações
            this.emailService = configPanel.getEmailService();
        }
    }
}