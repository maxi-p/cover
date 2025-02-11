package dev.maxpetrushin.cover_gen.repository.implementation;

import dev.maxpetrushin.cover_gen.config.DBUtils;
import dev.maxpetrushin.cover_gen.dto.ProfileDTO;
import dev.maxpetrushin.cover_gen.repository.ProfileDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresJDBCProfileDAO implements ProfileDAO {
    private static PostgresJDBCProfileDAO instance;

    private PostgresJDBCProfileDAO() {
    }

    // Singleton
    public static PostgresJDBCProfileDAO getInstance() {
        if (instance == null) {
            instance = new PostgresJDBCProfileDAO();
        }
        return instance;
    }

    @Override
    public boolean saveProfile(ProfileDTO profile) {
        try (var connection = DBUtils.getConnection();
             var statement = connection.prepareStatement(
                     "INSERT INTO profile (profile_name, default_template_source, " +
                             "default_name, default_greeting, default_email, default_phone, " +
                             "default_linkedin, defualt_github, default_website, default_signature_url, " +
                             "default_complimentary_close) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
             )) {
            statement.setString(1, profile.getProfileName());
            statement.setString(2, profile.getDefaultTemplateSource());
            statement.setString(3, profile.getDefaultName());
            statement.setString(4, profile.getDefaultGreeting());
            statement.setString(5, profile.getDefaultEmail());
            statement.setString(6, profile.getDefaultPhone());
            statement.setString(7, profile.getDefaultLinkedin());
            statement.setString(8, profile.getDefaultGithub());
            statement.setString(9, profile.getDefaultWebsite());
            statement.setString(10, profile.getDefaultSignatureUrl());
            statement.setString(11, profile.getDefaultComplimentaryClose());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<ProfileDTO> getAllProfiles() {
        return new ArrayList<>();
    }

    @Override
    public ProfileDTO getProfileById(int id) {
        return new ProfileDTO();
    }
}
