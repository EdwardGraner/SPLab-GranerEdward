package ro.uvt.info.dessignpatternslab2024.commands;

import ro.uvt.info.dessignpatternslab2024.services.BookService;

public class CreateBookCommand extends AsynchronousCommand {
    private final BookService bookService;
    private final String bookTitle;

    public CreateBookCommand(BookService bookService, String bookTitle) {
        this.bookService = bookService;
        this.bookTitle = bookTitle;
    }

    @Override
    protected void executeCommand() {
        bookService.createBook(bookTitle);
    }
}
