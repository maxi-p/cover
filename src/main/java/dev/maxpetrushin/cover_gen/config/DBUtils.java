package dev.maxpetrushin.cover_gen.config;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    
    // Injecting the database connection props from application.yml
    @Value("${spring.datasource.url}")
    private static String dbUrl;
    @Value("${spring.datasource.username}")
    private static String dbUsername;
    @Value("${spring.datasource.password}")
    private static String dbPassword;
    @Value("${spring.datasource.driver-class-name}")
    private static String dbDriver;

    private DBUtils() {
    }

    public static Connection getConnection() {
        try {
            Class.forName(dbDriver);
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error establishing DB connection ", e);
        }
    }
}
