package dev.maxpetrushin.cover_gen.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paragraph {
    private int id;
    private String content;
    private int profileId;
    private int position;
}
