package com.example.clinic.infra.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ConnectionFactory para Oracle.
 * Defina variáveis de ambiente: ORACLE_URL, ORACLE_USER, ORACLE_PASSWORD.
 * Exemplo de URL:
 *   jdbc:oracle:thin:@//localhost:1521/FREEPDB1
 */
public class OracleConnectionFactory {

    public static Connection getConnection() throws SQLException {
        String url  = System.getenv().getOrDefault("ORACLE_URL", "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl");
        String user = System.getenv().getOrDefault("ORACLE_USER", "rm....");
        String pass = System.getenv().getOrDefault("ORACLE_PASSWORD", "senha");

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);
        return DriverManager.getConnection(url, props);
    }
}
