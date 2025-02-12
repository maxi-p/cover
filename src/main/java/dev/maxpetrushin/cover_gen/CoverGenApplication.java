package dev.maxpetrushin.cover_gen;

import dev.maxpetrushin.cover_gen.config.DBUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@RestController
public class CoverGenApplication {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    public static void main(String[] args) {
        SpringApplication.run(CoverGenApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            // Injecting Credentials
            DBUtils.setDbUrl(dbUrl);
            DBUtils.setDbUsername(dbUsername);
            DBUtils.setDbPassword(dbPassword);
            DBUtils.setDbDriver(dbDriver);
            System.out.println("Credentials Injected");

            // Testing connection
            try (Connection connection = DBUtils.getConnection()) {
                System.out.println("JDBC Connection Works");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
    }
}
