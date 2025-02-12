package dev.maxpetrushin.cover_gen.repository.implementation;

import dev.maxpetrushin.cover_gen.config.DBUtils;
import dev.maxpetrushin.cover_gen.dto.ParagraphDTO;
import dev.maxpetrushin.cover_gen.dto.ProfileDTO;
import dev.maxpetrushin.cover_gen.repository.ParagraphDAO;
import dev.maxpetrushin.cover_gen.repository.ProfileDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresJDBCProfileDAO implements ProfileDAO {
    private static PostgresJDBCProfileDAO instance;
    private final ParagraphDAO paragraphDAO;

    private PostgresJDBCProfileDAO() {
        paragraphDAO = PostgresJDBCParagraphDAO.getInstance();
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
                             "default_linkedin, default_github, default_website, default_signature_url, " +
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
        List<ProfileDTO> profileDTOList = new ArrayList<>();
        try (var connection = DBUtils.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM profile;");
             var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                ProfileDTO profileDTO = new ProfileDTO();
                transferFromResultSetToProfileDTO(profileDTO, resultSet);

                profileDTOList.add(profileDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileDTOList;
    }

    @Override
    public ProfileDTO getProfileById(int id) {
        ProfileDTO profileDTO = new ProfileDTO();
        try (var connection = DBUtils.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM profile WHERE id = ?;");
        ) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transferFromResultSetToProfileDTO(profileDTO, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileDTO;
    }

    private void transferFromResultSetToProfileDTO(ProfileDTO profileDTO, ResultSet resultSet) throws SQLException {
        profileDTO.setId(resultSet.getInt("id"));
        profileDTO.setProfileName(resultSet.getString("profile_name"));
        profileDTO.setDefaultTemplateSource(resultSet.getString("default_template_source"));
        profileDTO.setDefaultName(resultSet.getString("default_name"));
        profileDTO.setDefaultGreeting(resultSet.getString("default_greeting"));
        profileDTO.setDefaultEmail(resultSet.getString("default_email"));
        profileDTO.setDefaultPhone(resultSet.getString("default_phone"));
        profileDTO.setDefaultLinkedin(resultSet.getString("default_linkedin"));
        profileDTO.setDefaultGithub(resultSet.getString("default_github"));
        profileDTO.setDefaultWebsite(resultSet.getString("default_website"));
        profileDTO.setDefaultSignatureUrl(resultSet.getString("default_signature_url"));
        profileDTO.setDefaultComplimentaryClose(resultSet.getString("default_complimentary_close"));
        List<ParagraphDTO> paragraphDTOs = paragraphDAO.getParagraphsByProfileId(profileDTO.getId());
        profileDTO.setParagraphs(paragraphDTOs);
    }
}
