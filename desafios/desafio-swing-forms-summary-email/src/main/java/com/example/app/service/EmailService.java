package com.example.app.service;

import com.example.app.model.Message;
import com.example.app.util.EmailConstants;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * Serviço para envio de e-mails usando JavaMail API.
 */
public class EmailService {
    private final EmailConfig config;
    
    public EmailService() {
        this.config = new EmailConfig();
    }
    
    public EmailService(EmailConfig config) {
        this.config = config;
    }
    
    /**
     * Envia um e-mail com base na mensagem do formulário.
     * @param message A mensagem contendo nome, e-mail e corpo
     * @return true se o e-mail foi enviado com sucesso
     * @throws MessagingException se houver erro no envio
     */
    public boolean sendEmail(Message message) throws MessagingException {
        if (!config.isConfigured()) {
            throw new IllegalStateException("Configurações de e-mail não estão completas");
        }
        
        Session session = createSession();
        MimeMessage mimeMessage = createMimeMessage(session, message);
        Transport.send(mimeMessage);
        return true;
    }
    
    /**
     * Testa a conexão com o servidor SMTP.
     * @return true se a conexão foi bem-sucedida
     */
    public boolean testConnection() {
        try {
            Session session = createSession();
            Transport transport = session.getTransport("smtp");
            transport.connect();
            transport.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Cria uma sessão SMTP configurada.
     */
    private Session createSession() {
        Properties props = createSmtpProperties();
        return Session.getInstance(props, createAuthenticator());
    }
    
    /**
     * Cria as propriedades SMTP baseadas na configuração.
     */
    private Properties createSmtpProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", config.getSmtpHost());
        props.put("mail.smtp.port", config.getSmtpPort());
        props.put(EmailConstants.SMTP_AUTH, "true");
        
        if (config.isUseSsl()) {
            props.put(EmailConstants.SMTP_SOCKET_FACTORY_PORT, config.getSmtpPort());
            props.put(EmailConstants.SMTP_SOCKET_FACTORY_CLASS, EmailConstants.SSL_SOCKET_FACTORY);
        }
        
        if (config.isUseTls()) {
            props.put(EmailConstants.SMTP_STARTTLS_ENABLE, "true");
        }
        
        return props;
    }
    
    /**
     * Cria um autenticador para a sessão SMTP.
     */
    private Authenticator createAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getUsername(), config.getPassword());
            }
        };
    }
    
    /**
     * Cria uma mensagem MIME configurada.
     */
    private MimeMessage createMimeMessage(Session session, Message message) throws MessagingException {

        //Montar e-mail
        //Definir de qual caixa de e-mail vai ser disparado
        //Quem irá receber o e-mail
        //Qual mensagem enviar no e-mail

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(config.getFromEmail()));
        mimeMessage.setRecipients(jakarta.mail.Message.RecipientType.TO, InternetAddress.parse(message.getEmail()));
        mimeMessage.setSubject(EmailConstants.EMAIL_SUBJECT_PREFIX + message.getName());
        
        String emailBody = buildEmailBody(message);
        mimeMessage.setText(emailBody, "UTF-8");
        
        return mimeMessage;
    }
    
    /**
     * Construir o corpo do e-mail com as informações da mensagem.
     */
    private String buildEmailBody(Message message) {
        return String.format(EmailConstants.EMAIL_BODY_TEMPLATE,
                message.getName(),
                message.getEmail(),
                message.getCreatedAt(),
                message.getBody());
    }
    
    public EmailConfig getConfig() {
        return config;
    }
} 