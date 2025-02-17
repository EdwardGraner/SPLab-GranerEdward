package ro.uvt.info.dessignpatternslab2024.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uvt.info.dessignpatternslab2024.models.Paragraph;

public interface ParagraphRepository extends JpaRepository<Paragraph, Integer> {
}
