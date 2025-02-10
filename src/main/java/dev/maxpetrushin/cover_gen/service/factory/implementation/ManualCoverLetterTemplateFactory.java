package dev.maxpetrushin.cover_gen.service.factory.implementation;

import dev.maxpetrushin.cover_gen.model.CoverLetterTemplate;
import dev.maxpetrushin.cover_gen.model.implementation.LatexCoverLetterTemplate;
import dev.maxpetrushin.cover_gen.model.implementation.TextCoverLetterTemplate;
import dev.maxpetrushin.cover_gen.service.FileService;
import dev.maxpetrushin.cover_gen.service.factory.CoverLetterTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManualCoverLetterTemplateFactory implements CoverLetterTemplateFactory {
    final FileService fileService;

    @Autowired
    public ManualCoverLetterTemplateFactory(FileService fileService) {
        this.fileService = fileService;
    }

    public CoverLetterTemplate getCoverLetterTemplate(String templateType) {
        return switch (templateType) {
            case "latex" -> new LatexCoverLetterTemplate(fileService);
            case "text" -> new TextCoverLetterTemplate();
            default -> throw new IllegalArgumentException("Unknown template type");
        };
    }
}
