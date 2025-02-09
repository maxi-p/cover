package dev.maxpetrushin.cover_gen.controller;

import dev.maxpetrushin.cover_gen.dto.CoverLetterRequest;
import dev.maxpetrushin.cover_gen.service.CoverLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cover-letter")
public class CoverLetterController {
    @Autowired
    private CoverLetterService coverLetterService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateCoverLetter(@RequestBody CoverLetterRequest request) {
        try {
            // Generate LaTeX content by replacing placeholders in the template
            String coverLetter = coverLetterService.generateCoverLetter(request);

            // Return the source text as response
            return ResponseEntity.ok()
                    .body(coverLetter);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
