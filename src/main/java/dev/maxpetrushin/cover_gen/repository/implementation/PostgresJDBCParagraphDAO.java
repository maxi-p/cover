package dev.maxpetrushin.cover_gen.repository.implementation;

import dev.maxpetrushin.cover_gen.config.DBUtils;
import dev.maxpetrushin.cover_gen.dto.ParagraphDTO;
import dev.maxpetrushin.cover_gen.repository.ParagraphDAO;

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
        return new ArrayList<>();
    }

    @Override
    public ParagraphDTO getParagraphById(int id) {
        return new ParagraphDTO();
    }
}
