package ro.uvt.info.dessignpatternslab2024.commands;

import ro.uvt.info.dessignpatternslab2024.services.BookService;

public class DeleteBookCommand implements Command {
    private final BookService bookService;
    private final int bookId;

    public DeleteBookCommand(BookService bookService, int bookId) {
        this.bookService = bookService;
        this.bookId = bookId;
    }

    @Override
    public void execute() {
        bookService.deleteBook(bookId);
    }
}
