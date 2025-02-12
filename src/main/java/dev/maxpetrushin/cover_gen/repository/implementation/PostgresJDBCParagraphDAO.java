package dev.maxpetrushin.cover_gen.repository.implementation;

import dev.maxpetrushin.cover_gen.config.DBUtils;
import dev.maxpetrushin.cover_gen.dto.ParagraphDTO;
import dev.maxpetrushin.cover_gen.repository.ParagraphDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresJDBCParagraphDAO implements ParagraphDAO {
    private static PostgresJDBCParagraphDAO instance;

    private PostgresJDBCParagraphDAO() {
    }

    // Singleton
    public static PostgresJDBCParagraphDAO getInstance() {
        if (instance == null) {
            instance = new PostgresJDBCParagraphDAO();
        }
        return instance;
    }

    @Override
    public boolean saveParagraph(ParagraphDTO paragraph) {
        try (var connection = DBUtils.getConnection();
             var statement = connection.prepareStatement(
                     "INSERT INTO paragraph (content, profile_id, position) VALUES (?, ?, ?);"
             )) {
            statement.setString(1, paragraph.getContent());
            statement.setInt(2, paragraph.getProfileId());
            statement.setInt(3, paragraph.getPosition());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<ParagraphDTO> getAllParagraphs() {
        List<ParagraphDTO> paragraphDTOList = new ArrayList<>();
        try (var connection = DBUtils.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM paragraph;");
             var resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                ParagraphDTO paragraphDTO = new ParagraphDTO();
                transferResultSetToParagraphDTO(paragraphDTO, resultSet);
                paragraphDTOList.add(paragraphDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paragraphDTOList;
    }

    @Override
    public ParagraphDTO getParagraphById(int id) {
        ParagraphDTO paragraphDTO = new ParagraphDTO();
        try (var connection = DBUtils.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM paragraph WHERE id = ?;");) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transferResultSetToParagraphDTO(paragraphDTO, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paragraphDTO;
    }

    @Override
    public List<ParagraphDTO> getParagraphsByProfileId(int profileId) {
        List<ParagraphDTO> paragraphDTOList = new ArrayList<>();
        try (var connection = DBUtils.getConnection();
             var statement = connection.prepareStatement("SELECT * FROM paragraph WHERE profile_id = ?;");) {
            statement.setInt(1, profileId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ParagraphDTO paragraphDTO = new ParagraphDTO();
                transferResultSetToParagraphDTO(paragraphDTO, resultSet);
                paragraphDTOList.add(paragraphDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paragraphDTOList;
    }

    public void transferResultSetToParagraphDTO(ParagraphDTO paragraphDTO, ResultSet resultSet) throws SQLException {
        paragraphDTO.setId(resultSet.getInt("id"));
        paragraphDTO.setContent(resultSet.getString("content"));
        paragraphDTO.setProfileId(resultSet.getInt("profile_id"));
        paragraphDTO.setPosition(resultSet.getInt("position"));
    }
}
