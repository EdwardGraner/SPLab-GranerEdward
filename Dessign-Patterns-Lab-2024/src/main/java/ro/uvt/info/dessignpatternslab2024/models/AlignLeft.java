package ro.uvt.info.dessignpatternslab2024.models;

public class AlignLeft implements AlignStrategy {
    @Override
    public void render(String text) {
        System.out.println("<< " + text);
    }
}
