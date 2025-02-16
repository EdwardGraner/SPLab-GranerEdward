package ro.uvt.info.dessignpatternslab2024.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dessignpatternslab2024.commands.CreateBookCommand;
import ro.uvt.info.dessignpatternslab2024.commands.DeleteBookCommand;
import ro.uvt.info.dessignpatternslab2024.commands.RequestQueue;
import ro.uvt.info.dessignpatternslab2024.models.Book;
import ro.uvt.info.dessignpatternslab2024.persistence.BooksRepository;
import ro.uvt.info.dessignpatternslab2024.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final RequestQueue requestQueue;
    private final BooksRepository booksRepository;

    public BooksController(BookService bookService, RequestQueue requestQueue, BooksRepository booksRepository) {
        this.bookService = bookService;
        this.requestQueue = requestQueue;
        this.booksRepository = booksRepository;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = booksRepository.save(book); // Salvăm direct în DB
        return ResponseEntity.ok(savedBook);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        String requestId = requestQueue.submitRequest(new DeleteBookCommand(bookService, id));
        return ResponseEntity.accepted().body("Request accepted. Track it with ID: " + requestId);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getRequestStatus(@PathVariable String id) {
        String status = requestQueue.getRequestStatus(id);
        return ResponseEntity.ok("Request " + id + " is " + status);
    }
}
