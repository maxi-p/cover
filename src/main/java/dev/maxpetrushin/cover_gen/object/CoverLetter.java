package dev.maxpetrushin.cover_gen.object;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class CoverLetter {
    @Id
    @GeneratedValue
    private Long id;
    private String companyName;
    private String companyAddress;
}
