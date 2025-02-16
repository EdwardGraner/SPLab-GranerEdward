package ro.uvt.info.dessignpatternslab2024.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookService {
    private final Map<Integer, String> books = new ConcurrentHashMap<>();
    private int idCounter = 1;

    public void createBook(String title) {
        books.put(idCounter++, title);
        System.out.println("Book created: " + title);
    }

    public void deleteBook(int id) {
        books.remove(id);
        System.out.println("Book deleted with ID: " + id);
    }

    public List<String> getAllBooks() {
        return new ArrayList<>(books.values());
    }

}
