package dev.maxpetrushin.cover_gen.model.implementation;

import dev.maxpetrushin.cover_gen.model.CoverLetterTemplate;

public class TextCoverLetterTemplate extends CoverLetterTemplate {
    @Override
    public void createTemplate(String type) {
        if (type.equals("default")) {
            String companyName = "[CompanyName]";
            String yourName = "[YourName]";
            String customParagraph = "[CustomParagraph]";
            this.template = String.format("Dear %s, \n\n%s\n\nSincerely,\n%s", companyName, customParagraph, yourName);
        }
    }

    @Override
    public byte[] generateCoverLetter(String template){
        return null;
    }
}
