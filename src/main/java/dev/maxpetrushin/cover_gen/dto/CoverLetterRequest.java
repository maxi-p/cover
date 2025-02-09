package dev.maxpetrushin.cover_gen.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoverLetterRequest {
    private String companyName;
    private String yourName;
    private String customParagraph;
}
