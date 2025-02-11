package ro.uvt.info.dessignpatternslab2024.models;

import lombok.Setter;

public class Paragraph implements Element {
    private String text;
    @Setter
    private AlignStrategy alignStrategy;

    public Paragraph(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        if (alignStrategy != null) {
            alignStrategy.render(text);
        } else {
            System.out.println(text);
        }
    }
}
