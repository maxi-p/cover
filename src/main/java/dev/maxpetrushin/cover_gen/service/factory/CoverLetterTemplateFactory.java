package dev.maxpetrushin.cover_gen.service.factory;

import dev.maxpetrushin.cover_gen.model.CoverLetterTemplate;

public interface CoverLetterTemplateFactory {
    CoverLetterTemplate getCoverLetterTemplate(String templateType);
}
