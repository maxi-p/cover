package dev.maxpetrushin.cover_gen.object;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Paragraph {
    @Id
    @GeneratedValue
    private Long id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cover_letter_id")
    private CoverLetter coverLetter;
}
