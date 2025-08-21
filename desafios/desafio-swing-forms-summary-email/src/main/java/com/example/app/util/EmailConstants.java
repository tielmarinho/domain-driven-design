package com.example.app.util;

/**
 * Constantes relacionadas ao sistema de e-mail.
 */
public final class EmailConstants {
    
    // Configuração padrão
    public static final String DEFAULT_SMTP_HOST = "smtp.gmail.com";
    public static final String DEFAULT_SMTP_PORT = "587";
    public static final boolean DEFAULT_USE_TLS = true;
    public static final boolean DEFAULT_USE_SSL = false;
    
    // Chaves de preferências
    public static final String PREF_NODE = "email_config";
    public static final String SMTP_HOST = "smtp_host";
    public static final String SMTP_PORT = "smtp_port";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FROM_EMAIL = "from_email";
    public static final String TO_EMAIL = "to_email";
    public static final String USE_SSL = "use_ssl";
    public static final String USE_TLS = "use_tls";
    
    // Propriedades SMTP
    public static final String SMTP_AUTH = "mail.smtp.auth";
    public static final String SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    public static final String SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    public static final String SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String SSL_SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
    
    // Mensagens de UI
    public static final String MSG_EMAIL_SENT_SUCCESS = "E-mail enviado com sucesso!";
    public static final String MSG_EMAIL_SENT_FAILURE = "Falha ao enviar e-mail.";
    public static final String MSG_CONNECTION_SUCCESS = "Conexão bem-sucedida!";
    public static final String MSG_CONNECTION_FAILURE = "Falha na conexão. Verifique as configurações.";
    public static final String MSG_CONFIG_SAVED = "Configurações salvas com sucesso!";
    public static final String MSG_EMAIL_NOT_CONFIGURED = "Configurações de e-mail não estão definidas.\nDeseja configurar agora?";
    public static final String MSG_VALIDATION_ERROR = "Preencha Nome, E-mail válido e Mensagem.";
    public static final String MSG_CONFIG_REQUIRED = "Configuração Necessária";
    public static final String MSG_EMAIL_CONFIG = "Configurações de E-mail";
    public static final String MSG_EMAIL_SEND = "Envio de E-mail";
    public static final String MSG_CONNECTION_TEST = "Teste de Conexão";
    public static final String MSG_ERROR = "Erro";
    public static final String MSG_SUCCESS = "Sucesso";
    public static final String MSG_VALIDATION = "Validação";
    
    // Labels de UI
    public static final String LABEL_SMTP_HOST = "Servidor SMTP:";
    public static final String LABEL_PORT = "Porta:";
    public static final String LABEL_USERNAME = "Usuário:";
    public static final String LABEL_PASSWORD = "Senha:";
    public static final String LABEL_FROM_EMAIL = "E-mail de origem:";
    public static final String LABEL_SECURITY = "Segurança:";
    public static final String LABEL_USE_SSL = "Usar SSL";
    public static final String LABEL_USE_TLS = "Usar TLS";
    public static final String LABEL_NAME = "Nome:";
    public static final String LABEL_EMAIL = "E-mail:";
    public static final String LABEL_MESSAGE = "Mensagem:";
    
    // Botões
    public static final String BTN_SEND = "Enviar";
    public static final String BTN_CONFIG = "Configurar E-mail";
    public static final String BTN_SAVE = "Salvar";
    public static final String BTN_TEST = "Testar Conexão";
    public static final String BTN_SENDING = "Enviando...";
    public static final String BTN_TESTING = "Testando...";
    
    // Tooltips
    public static final String TOOLTIP_SEND = "Envia o formulário (Ctrl+Enter)";
    public static final String TOOLTIP_CONFIG = "Configurar opções de e-mail";
    public static final String TOOLTIP_SAVE = "Salvar configurações";
    public static final String TOOLTIP_TEST = "Testar conexão com servidor";
    
    // Atalhos de teclado
    public static final String KEY_SEND = "ctrl ENTER";
    public static final String KEY_SAVE = "ctrl S";
    
    // Ícones
    public static final String ICON_SEND = "/icons/send.png";
    public static final String ICON_SETTINGS = "/icons/app.png";
    public static final String ICON_SAVE = "/icons/save.png";
    public static final String ICON_TEST = "/icons/app.png";
    
    // Tamanhos de Ícones
    public static final int ICON_SIZE_SMALL = 16;
    public static final int ICON_SIZE_MEDIUM = 18;
    
    // Tamanhos de campos
    public static final int FIELD_SIZE_SMTP_HOST = 20;
    public static final int FIELD_SIZE_PORT = 10;
    public static final int FIELD_SIZE_USERNAME = 20;
    public static final int FIELD_SIZE_PASSWORD = 20;
    public static final int FIELD_SIZE_EMAIL = 20;
    public static final int FIELD_SIZE_NAME = 20;
    public static final int FIELD_SIZE_MESSAGE_ROWS = 6;
    public static final int FIELD_SIZE_MESSAGE_COLS = 20;
    
    // Assunto do e-mail
    public static final String EMAIL_SUBJECT_PREFIX = "Nova mensagem de ";
    
    // Template do corpo do e-mail
    public static final String EMAIL_BODY_TEMPLATE = 
        "Nova mensagem recebida:\n\n" +
        "Nome: %s\n" +
        "E-mail: %s\n" +
        "Data/Hora: %s\n\n" +
        "Mensagem:\n%s\n";
    
    private EmailConstants() {
    }
} 