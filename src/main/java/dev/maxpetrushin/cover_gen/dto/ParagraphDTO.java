package dev.maxpetrushin.cover_gen.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParagraphDTO {
    private int id;
    private String content;
    private int profileId;
    private int position;
}
