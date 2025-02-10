package ro.uvt.info.dessignpatternslab2024.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeSection implements Section {
    private String title;
    private List<Element> elements = new ArrayList<>();
    private static Map<Element, CompositeSection> elementSectionMap = new HashMap<>();

    public CompositeSection(String title) {
        this.title = title;
    }

    public void add(Element element) {
        if (elementSectionMap.containsKey(element)) {
            throw new IllegalStateException("Elementul este deja folosit într-o altă secțiune!");
        }
        elements.add(element);
        elementSectionMap.put(element, this);
    }

    @Override
    public void print() {
        System.out.println(title);
        for (Element element : elements) {
            element.print();
        }
    }
}
