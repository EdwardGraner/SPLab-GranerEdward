package ro.uvt.info.dessignpatternslab2024.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dessignpatternslab2024.models.AlignCenter;
import ro.uvt.info.dessignpatternslab2024.models.AlignLeft;
import ro.uvt.info.dessignpatternslab2024.models.AlignRight;
import ro.uvt.info.dessignpatternslab2024.models.Paragraph;
import ro.uvt.info.dessignpatternslab2024.persistence.ParagraphRepository;

@RestController
@RequestMapping("/paragraphs")
public class ParagraphController {
    private final ParagraphRepository paragraphRepository;

    public ParagraphController(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    @PostMapping
    public ResponseEntity<Paragraph> createParagraph(@RequestBody Paragraph paragraph) {
        return ResponseEntity.ok(paragraphRepository.save(paragraph));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paragraph> getParagraph(@PathVariable int id) {
        return ResponseEntity.of(paragraphRepository.findById(id));
    }

    @PutMapping("/{id}/align")
    public ResponseEntity<Paragraph> alignParagraph(@PathVariable int id, @RequestParam String alignment) {
        Paragraph paragraph = paragraphRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paragraph not found"));

        switch (alignment.toUpperCase()) {
            case "LEFT" -> paragraph.setAlignStrategy(new AlignLeft());
            case "RIGHT" -> paragraph.setAlignStrategy(new AlignRight());
            case "CENTER" -> paragraph.setAlignStrategy(new AlignCenter());
            default -> throw new IllegalArgumentException("Invalid alignment type");
        }

        return ResponseEntity.ok(paragraphRepository.save(paragraph));
    }
}
