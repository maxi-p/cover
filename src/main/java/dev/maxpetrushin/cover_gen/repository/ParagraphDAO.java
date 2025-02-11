package dev.maxpetrushin.cover_gen.repository;

import dev.maxpetrushin.cover_gen.dto.ParagraphDTO;

import java.util.List;

public interface ParagraphDAO {
    boolean saveParagraph(ParagraphDTO paragraph);

    List<ParagraphDTO> getAllParagraphs();

    public ParagraphDTO getParagraphById(int id);
}
