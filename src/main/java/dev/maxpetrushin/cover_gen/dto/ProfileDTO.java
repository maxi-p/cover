package dev.maxpetrushin.cover_gen.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private int id;
    private List<ParagraphDTO> paragraphs;
    private String profileName;
    private String defaultTemplateSource;
    private String defaultName;
    private String defaultGreeting;
    private String defaultEmail;
    private String defaultPhone;
    private String defaultLinkedin;
    private String defaultGithub;
    private String defaultWebsite;
    private String defaultSignatureUrl;
    private String defaultComplimentaryClose;
}
