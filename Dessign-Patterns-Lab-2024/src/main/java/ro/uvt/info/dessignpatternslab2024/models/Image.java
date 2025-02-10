package ro.uvt.info.dessignpatternslab2024.models;

public class Image implements Element {
    private String imageName;

    public Image(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public void print() {
        System.out.println("Image with name: " + imageName);
    }
}
