package ro.uvt.info.dessignpatternslab2024.models;

public class AlignCenter implements AlignStrategy {
    @Override
    public void render(String text) {
        System.out.println("<< " + text + " >>");
    }
}
