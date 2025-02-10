package dev.maxpetrushin.cover_gen.model;

public abstract class CoverLetterTemplate {
    protected String template;

    public abstract void createTemplate(String type);

    public abstract byte[] generateCoverLetter(String template);

    public String getTemplate() {
        return this.template;
    }
}