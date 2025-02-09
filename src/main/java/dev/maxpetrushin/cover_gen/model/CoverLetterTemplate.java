package dev.maxpetrushin.cover_gen.model;

public abstract class CoverLetterTemplate {
    protected String template;

    public abstract void createTemplate();

    public String getTemplate() {
        return this.template;
    }
}