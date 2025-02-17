package ro.uvt.info.dessignpatternslab2024.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paragraph implements Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @Column(name = "alignment", nullable = false)
    private String alignmentType = "LEFT";

    @Transient
    @JsonIgnore
    private AlignStrategy alignStrategy;

    public Paragraph(String text) {
        this.text = text;
        this.alignmentType = "LEFT";
    }

    public AlignStrategy getAlignStrategy() {
        if (alignStrategy == null) { // Dacă nu a fost setat, îl determinăm din alignmentType
            switch (alignmentType) {
                case "RIGHT" -> alignStrategy = new AlignRight();
                case "CENTER" -> alignStrategy = new AlignCenter();
                default -> alignStrategy = new AlignLeft();
            }
        }
        return alignStrategy;
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
        if (alignStrategy instanceof AlignLeft) alignmentType = "LEFT";
        else if (alignStrategy instanceof AlignRight) alignmentType = "RIGHT";
        else if (alignStrategy instanceof AlignCenter) alignmentType = "CENTER";
    }

    @Override
    public void print() {
        getAlignStrategy().render(text);
    }
}
