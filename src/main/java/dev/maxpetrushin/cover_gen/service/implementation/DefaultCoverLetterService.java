package dev.maxpetrushin.cover_gen.service.implementation;

import dev.maxpetrushin.cover_gen.dto.CoverLetterRequest;
import dev.maxpetrushin.cover_gen.model.CoverLetterTemplate;
import dev.maxpetrushin.cover_gen.service.CoverLetterService;
import dev.maxpetrushin.cover_gen.service.FileService;
import dev.maxpetrushin.cover_gen.service.factory.CoverLetterTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultCoverLetterService implements CoverLetterService {
    private final CoverLetterTemplateFactory coverLetterTemplateFactory;

    @Autowired
    public DefaultCoverLetterService(CoverLetterTemplateFactory coverLetterTemplateFactory) {
        this.coverLetterTemplateFactory = coverLetterTemplateFactory;
    }

    @Override
    public String generateCoverLetterSource(CoverLetterRequest request) {
        CoverLetterTemplate template = coverLetterTemplateFactory.getCoverLetterTemplate("latex");
        template.createTemplate("default");
        return sourceGenetationHelper(template, request);
    }

    private String sourceGenetationHelper(CoverLetterTemplate template, CoverLetterRequest request){
        String source = template.getTemplate();

        // Replace placeholders with actual data
        source = source
                .replace("[CompanyName]", request.getCompanyName())
                .replace("[CompanyAddress]", request.getCompanyAddress())
                .replace("[CustomParagraph]", request.getCustomParagraph())
                .replace("[YourName]", request.getYourName());

        return source;
    }

    @Override
    public byte[] generateCoverLetter(CoverLetterRequest request) {
        CoverLetterTemplate template = coverLetterTemplateFactory.getCoverLetterTemplate("latex");
        template.createTemplate("default");
        String source = sourceGenetationHelper(template, request);
        return template.generateCoverLetter(source);
    }
}
