package dev.maxpetrushin.cover_gen.service;

import dev.maxpetrushin.cover_gen.dto.CoverLetterRequest;
import dev.maxpetrushin.cover_gen.model.CoverLetterTemplate;
import dev.maxpetrushin.cover_gen.service.factory.CoverLetterTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverLetterService {

    private final CoverLetterTemplateFactory coverLetterTemplateFactory;

    @Autowired
    public CoverLetterService(CoverLetterTemplateFactory coverLetterTemplateFactory) {
        this.coverLetterTemplateFactory = coverLetterTemplateFactory;
    }

    public String generateCoverLetter(CoverLetterRequest request) {
        CoverLetterTemplate template = coverLetterTemplateFactory.getCoverLetterTemplate("latex");
        template.createTemplate();

        String source = template.getTemplate();
        // Replace placeholders with actual data
        source = source.replace("#CompanyName", request.getCompanyName()).replace("#YourName", request.getYourName())

                .replace("#CustomParagraph", request.getCustomParagraph());

        return source;
    }
}
