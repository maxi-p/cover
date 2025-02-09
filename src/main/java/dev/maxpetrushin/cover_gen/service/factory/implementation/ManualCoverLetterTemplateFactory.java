package dev.maxpetrushin.cover_gen.service.factory.implementation;

import dev.maxpetrushin.cover_gen.model.CoverLetterTemplate;
import dev.maxpetrushin.cover_gen.model.implementation.LatexCoverLetterTemplate;
import dev.maxpetrushin.cover_gen.model.implementation.TextCoverLetterTemplate;
import dev.maxpetrushin.cover_gen.service.factory.CoverLetterTemplateFactory;
import org.springframework.stereotype.Service;

@Service
public class ManualCoverLetterTemplateFactory implements CoverLetterTemplateFactory {
    public CoverLetterTemplate getCoverLetterTemplate(String templateType) {
        return switch (templateType) {
            case "latex" -> new LatexCoverLetterTemplate();
            case "text" -> new TextCoverLetterTemplate();
            default -> throw new IllegalArgumentException("Unknown template type");
        };
    }
}
