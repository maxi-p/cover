package dev.maxpetrushin.cover_gen.controller;

import dev.maxpetrushin.cover_gen.model.CoverLetterRequest;
import dev.maxpetrushin.cover_gen.service.implementation.DefaultCoverLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
    private DefaultCoverLetterService defaultCoverLetterService;

    @PostMapping("/generate-source")
    public ResponseEntity<String> generateCoverLetterSource(@RequestBody CoverLetterRequest request) {
        try {
            String coverLetter = defaultCoverLetterService.generateCoverLetterSource(request);

            return ResponseEntity.ok()
                    .body(coverLetter);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @PostMapping("/generate-pdf")
    public ResponseEntity<ByteArrayResource> generateCoverLetterPdf(@RequestBody CoverLetterRequest request) {
        try {
            byte[] pdfCoverLetter = defaultCoverLetterService.generateCoverLetter(request);
            ByteArrayResource resource = new ByteArrayResource(pdfCoverLetter);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
