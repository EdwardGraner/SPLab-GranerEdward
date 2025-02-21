package ro.uvt.info.dessignpatternslab2024.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dessignpatternslab2024.commands.CreateBookCommand;
import ro.uvt.info.dessignpatternslab2024.commands.DeleteBookCommand;
import ro.uvt.info.dessignpatternslab2024.commands.RequestQueue;
import ro.uvt.info.dessignpatternslab2024.models.Author;
import ro.uvt.info.dessignpatternslab2024.models.Book;
import ro.uvt.info.dessignpatternslab2024.models.Paragraph;
import ro.uvt.info.dessignpatternslab2024.observers.AllBooksSubject;
import ro.uvt.info.dessignpatternslab2024.persistence.AuthorsRepository;
import ro.uvt.info.dessignpatternslab2024.persistence.BooksRepository;
import ro.uvt.info.dessignpatternslab2024.persistence.CrudRepository;
import ro.uvt.info.dessignpatternslab2024.persistence.ParagraphRepository;
import ro.uvt.info.dessignpatternslab2024.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final RequestQueue requestQueue;
    private final CrudRepository<Book, Integer> bookRepository;
    private final AuthorsRepository authorsRepository;
    private final AllBooksSubject allBooksSubject;
    private final ParagraphRepository paragraphRepository;

    public BooksController(BookService bookService, RequestQueue requestQueue, CrudRepository<Book, Integer> bookRepository, AuthorsRepository authorsRepository, AllBooksSubject allBooksSubject, ParagraphRepository paragraphRepository) {
        this.bookService = bookService;
        this.requestQueue = requestQueue;
        this.bookRepository = bookRepository;
        this.authorsRepository = authorsRepository;
        this.allBooksSubject = allBooksSubject;
        this.paragraphRepository = paragraphRepository;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        allBooksSubject.notifyObservers("New book added: " + savedBook.getTitle());
        return ResponseEntity.ok(savedBook);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        String requestId = requestQueue.submitRequest(new DeleteBookCommand(bookService, id));
        return ResponseEntity.accepted().body("Request accepted. Track it with ID: " + requestId);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getRequestStatus(@PathVariable String id) {
        String status = requestQueue.getRequestStatus(id);
        return ResponseEntity.ok("Request " + id + " is " + status);
    }
    @PostMapping("/{id}/authors")
    public ResponseEntity<Book> addAuthorToBook(@PathVariable int id, @RequestBody Author author) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        Author finalAuthor = author;
        boolean exists = book.getAuthors().stream()
                .anyMatch(a -> a.getName().equalsIgnoreCase(finalAuthor.getName()));
        if (!exists) {
            author = authorsRepository.save(author); // Salvăm doar dacă e nou
            book.addAuthor(author);
            bookRepository.save(book);
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<Book> removeAuthorFromBook(@PathVariable int bookId, @PathVariable int authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorsRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.getAuthors().remove(author);
        bookRepository.save(book);

        return ResponseEntity.ok(book);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return ResponseEntity.ok(book);
    }

    @PostMapping("/{id}/paragraphs")
    public ResponseEntity<Book> addParagraphToBook(@PathVariable int id, @RequestBody Paragraph paragraph) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        paragraph = paragraphRepository.save(paragraph);
        book.getElements().add(paragraph);
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{bookId}/paragraphs/{paragraphId}")
    public ResponseEntity<Book> removeParagraphFromBook(@PathVariable int bookId, @PathVariable int paragraphId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Paragraph paragraph = paragraphRepository.findById(paragraphId)
                .orElseThrow(() -> new RuntimeException("Paragraph not found"));

        book.getElements().remove(paragraph);
        bookRepository.save(book);
        paragraphRepository.delete(paragraph);

        return ResponseEntity.ok(book);
    }




}
