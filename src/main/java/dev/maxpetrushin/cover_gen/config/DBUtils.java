package dev.maxpetrushin.cover_gen.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    private static String dbDriver;

    public static void setDbUrl(String dbUrl) {
        DBUtils.dbUrl = dbUrl;
    }

    public static void setDbUsername(String dbUsername) {
        DBUtils.dbUsername = dbUsername;
    }

    public static void setDbPassword(String dbPassword) {
        DBUtils.dbPassword = dbPassword;
    }

    public static void setDbDriver(String dbDriver) {
        DBUtils.dbDriver = dbDriver;
    }

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
