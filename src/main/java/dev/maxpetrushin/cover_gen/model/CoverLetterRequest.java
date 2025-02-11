package dev.maxpetrushin.cover_gen.model;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoverLetterRequest {
    private String companyName;
    private String companyAddress;
    private String yourName;
    private String customParagraph;
}
