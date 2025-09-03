package br.com.fiap.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final String url;
    private final String user;
    private final String pass;

    public ConnectionFactory(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public static ConnectionFactory fromEnv() {
        String url  = System.getenv().getOrDefault("ORACLE_URL", "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl");
        String user = System.getenv().getOrDefault("ORACLE_USER", "RM");
        String pass = System.getenv().getOrDefault("ORACLE_PASSWORD", "ddmmaa");
        return new ConnectionFactory(url, user, pass);
    }

    public Connection getConnection() {
        try {
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", pass);
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao obter conexão JDBC: " + e.getMessage(), e);
        }
    }
}