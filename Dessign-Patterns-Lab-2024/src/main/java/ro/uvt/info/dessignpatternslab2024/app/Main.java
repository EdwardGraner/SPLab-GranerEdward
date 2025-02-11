package ro.uvt.info.dessignpatternslab2024.app;


import ro.uvt.info.dessignpatternslab2024.models.*;

public class Main {
    public static void main(String[] args) {
        /*Book noapteBuna = new Book("Noapte buna, copii!");
        Author rpGheo = new Author("Radu Pavel Gheo");
        noapteBuna.addAuthor(rpGheo);

        CompositeSection cap1 = new CompositeSection("Capitolul 1");
        CompositeSection cap11 = new CompositeSection("Capitolul 1.1");
        CompositeSection cap111 = new CompositeSection("Capitolul 1.1.1");
        CompositeSection cap1111 = new CompositeSection("Subchapter 1.1.1.1");

        noapteBuna.addContent(new Paragraph("Multumesc celor care ..."));
        noapteBuna.addContent(cap1);
        cap1.add(new Paragraph("Moto capitol"));
        cap1.add(cap11);
        cap11.add(new Paragraph("Text from subchapter 1.1"));
        cap11.add(cap111);
        cap111.add(new Paragraph("Text from subchapter 1.1.1"));
        cap111.add(cap1111);
        cap1111.add(new Image("Image subchapter 1.1.1.1"));

        //TEST: Adăugăm același paragraf în două secțiuni diferite
        Paragraph testParagraph = new Paragraph("Acesta este un paragraf test.");
        cap1.add(testParagraph);
        System.out.println("Paragraful a fost adăugat în cap1 cu succes!");
        try {
            cap11.add(testParagraph);
            System.out.println(" Paragraful a fost adăugat în cap11 (NU TREBUIA!)");
        } catch (IllegalStateException e) {
            System.out.println("️ Eroare detectată: " + e.getMessage());
        }


        noapteBuna.print();
         */
        CompositeSection cap1 = new CompositeSection("Capitolul 1");

        Paragraph p1 = new Paragraph("Paragraph 1");
        cap1.add(p1);

        Paragraph p2 = new Paragraph("Paragraph 2");
        cap1.add(p2);

        Paragraph p3 = new Paragraph("Paragraph 3");
        cap1.add(p3);

        Paragraph p4 = new Paragraph("Paragraph 4");
        cap1.add(p4);

        System.out.println("Printing without Alignment:");
        cap1.print();

        // Aplicăm strategiile de aliniere
        p1.setAlignStrategy(new AlignCenter());
        p2.setAlignStrategy(new AlignRight());
        p3.setAlignStrategy(new AlignLeft());

        System.out.println("\nPrinting with Alignment:");
        cap1.print();
    }
}
