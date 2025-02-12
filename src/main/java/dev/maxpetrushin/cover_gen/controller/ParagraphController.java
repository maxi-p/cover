package dev.maxpetrushin.cover_gen.controller;

import dev.maxpetrushin.cover_gen.entity.Paragraph;
import dev.maxpetrushin.cover_gen.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paragraph")
public class ParagraphController {
    private final ParagraphService paragraphService;

    @Autowired
    public ParagraphController(ParagraphService paragraphService) {
        this.paragraphService = paragraphService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addParagraph(@RequestBody Paragraph paragraph) {
        boolean result = paragraphService.addParagraph(paragraph);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<Paragraph>> getParagraphs() {
        List<Paragraph> paragraphs = paragraphService.getAllParagraphs();
        return ResponseEntity.ok()
                .body(paragraphs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paragraph> getParagraphById(@PathVariable("id") int id) {
        Paragraph paragraph = paragraphService.getParagraphById(id);
        return ResponseEntity.ok()
                .body(paragraph);
    }

}
