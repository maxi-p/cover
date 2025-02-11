package dev.maxpetrushin.cover_gen.service.implementation;

import dev.maxpetrushin.cover_gen.entity.Paragraph;
import dev.maxpetrushin.cover_gen.service.ParagraphService;

public class PostgresJDBCParagraphService implements ParagraphService {
    @Override
    public boolean addParagraph(Paragraph paragraph) {
        return true;
    }
}
