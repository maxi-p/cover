package dev.maxpetrushin.cover_gen.service;

import dev.maxpetrushin.cover_gen.entity.Paragraph;

import java.util.List;

public interface ParagraphService {
    boolean addParagraph(Paragraph paragraph);
    Paragraph getParagraphById(int id);
    List<Paragraph> getAllParagraphs();
}
