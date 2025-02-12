package ro.uvt.info.dessignpatternslab2024.controllers;

import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dessignpatternslab2024.commands.Command;
import ro.uvt.info.dessignpatternslab2024.commands.CreateBookCommand;
import ro.uvt.info.dessignpatternslab2024.commands.DeleteBookCommand;
import ro.uvt.info.dessignpatternslab2024.services.BookService;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public String createBook(@RequestBody String bookTitle) {
        Command command = new CreateBookCommand(bookService, bookTitle);
        command.execute();
        return "Book created!";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        Command command = new DeleteBookCommand(bookService, id);
        command.execute();
        return "Book deleted!";
    }
}
