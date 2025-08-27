
package com.example.clinica.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnectionFactory {

    public static Connection getConnection() throws SQLException {
        String url  = System.getenv().getOrDefault("ORACLE_URL", "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl");
        String user = System.getenv().getOrDefault("ORACLE_USER", "pf2084");
        String pass = System.getenv().getOrDefault("ORACLE_PASSWORD", "fiap25");

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);
        return DriverManager.getConnection(url, props);
    }
}