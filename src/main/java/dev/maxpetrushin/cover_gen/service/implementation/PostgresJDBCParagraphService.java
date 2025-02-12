package dev.maxpetrushin.cover_gen.service.implementation;

import dev.maxpetrushin.cover_gen.dto.ParagraphDTO;
import dev.maxpetrushin.cover_gen.dto.converter.ParagraphToParagraphDTO;
import dev.maxpetrushin.cover_gen.entity.Paragraph;
import dev.maxpetrushin.cover_gen.repository.ParagraphDAO;
import dev.maxpetrushin.cover_gen.repository.implementation.PostgresJDBCParagraphDAO;
import dev.maxpetrushin.cover_gen.service.ParagraphService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostgresJDBCParagraphService implements ParagraphService {
    public ParagraphDAO paragraphDAO;
    public ParagraphToParagraphDTO paragraphToParagraphDTO;

    public PostgresJDBCParagraphService() {
        this.paragraphDAO = PostgresJDBCParagraphDAO.getInstance();
        this.paragraphToParagraphDTO = ParagraphToParagraphDTO.getInstance();
    }

    @Override
    public boolean addParagraph(Paragraph paragraph) {
        ParagraphDTO paragraphDTO = paragraphToParagraphDTO.paragraphToDTO(paragraph);
        return paragraphDAO.saveParagraph(paragraphDTO);
    }

    @Override
    public Paragraph getParagraphById(int id) {
        ParagraphDTO paragraphDTO = paragraphDAO.getParagraphById(id);
        return paragraphToParagraphDTO.dtoToParagraph(paragraphDTO);
    }

    @Override
    public List<Paragraph> getAllParagraphs() {
        List<ParagraphDTO> paragraphDTOList = paragraphDAO.getAllParagraphs();
        return paragraphToParagraphDTO.dtoListToParagraphList(paragraphDTOList);
    }
}
