package com.example.app.service;

import com.example.app.util.EmailConstants;

import java.util.prefs.Preferences;

/**
 * Configurações de e-mail usando Preferences para persistir dados.
 */
public class EmailConfig {
    
    private final Preferences prefs;
    
    public EmailConfig() {
        this.prefs = Preferences.userRoot().node(EmailConstants.PREF_NODE);
        setDefaults();
    }
    
    private void setDefaults() {
        if (getSmtpHost().isEmpty()) {
            setSmtpHost(EmailConstants.DEFAULT_SMTP_HOST);
            setSmtpPort(EmailConstants.DEFAULT_SMTP_PORT);
            setUseTls(EmailConstants.DEFAULT_USE_TLS);
            setUseSsl(EmailConstants.DEFAULT_USE_SSL);
        }
    }
    
    // Getters e Setters
    public String getSmtpHost() { 
        return prefs.get(EmailConstants.SMTP_HOST, ""); 
    }
    
    public void setSmtpHost(String host) { 
        prefs.put(EmailConstants.SMTP_HOST, host); 
    }
    
    public String getSmtpPort() { 
        return prefs.get(EmailConstants.SMTP_PORT, EmailConstants.DEFAULT_SMTP_PORT); 
    }
    
    public void setSmtpPort(String port) { 
        prefs.put(EmailConstants.SMTP_PORT, port); 
    }
    
    public String getUsername() { 
        return prefs.get(EmailConstants.USERNAME, ""); 
    }
    
    public void setUsername(String username) { 
        prefs.put(EmailConstants.USERNAME, username); 
    }
    
    public String getPassword() { 
        return prefs.get(EmailConstants.PASSWORD, ""); 
    }
    
    public void setPassword(String password) { 
        prefs.put(EmailConstants.PASSWORD, password); 
    }
    
    public String getFromEmail() { 
        return prefs.get(EmailConstants.FROM_EMAIL, ""); 
    }
    
    public void setFromEmail(String email) { 
        prefs.put(EmailConstants.FROM_EMAIL, email); 
    }
    
    public String getToEmail() { 
        return prefs.get(EmailConstants.TO_EMAIL, ""); 
    }
    
    public void setToEmail(String email) { 
        prefs.put(EmailConstants.TO_EMAIL, email); 
    }
    
    public boolean isUseSsl() { 
        return prefs.getBoolean(EmailConstants.USE_SSL, EmailConstants.DEFAULT_USE_SSL); 
    }
    
    public void setUseSsl(boolean useSsl) { 
        prefs.putBoolean(EmailConstants.USE_SSL, useSsl); 
    }
    
    public boolean isUseTls() { 
        return prefs.getBoolean(EmailConstants.USE_TLS, EmailConstants.DEFAULT_USE_TLS); 
    }
    
    public void setUseTls(boolean useTls) { 
        prefs.putBoolean(EmailConstants.USE_TLS, useTls); 
    }
    
    public boolean isConfigured() {
        return !getSmtpHost().isEmpty() && 
               !getUsername().isEmpty() && 
               !getPassword().isEmpty() && 
               !getFromEmail().isEmpty() && 
               !getToEmail().isEmpty();
    }
} 