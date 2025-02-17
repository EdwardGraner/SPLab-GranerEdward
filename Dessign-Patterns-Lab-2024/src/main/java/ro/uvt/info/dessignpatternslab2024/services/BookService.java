package ro.uvt.info.dessignpatternslab2024.services;

import org.springframework.stereotype.Service;
import ro.uvt.info.dessignpatternslab2024.models.Book;
import ro.uvt.info.dessignpatternslab2024.persistence.BooksRepository;

import java.util.List;

@Service
public class BookService {
    private final BooksRepository booksRepository;

    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void createBook(String title) {
        Book book = new Book(title);
        booksRepository.save(book); // 🔥 Salvează în DB
        System.out.println("Book created: " + title);
    }

    public void deleteBook(int id) {
        booksRepository.deleteById(id); // 🔥 Șterge din DB
        System.out.println("Book deleted with ID: " + id);
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll(); // 🔥 Returnează din DB
    }
}
