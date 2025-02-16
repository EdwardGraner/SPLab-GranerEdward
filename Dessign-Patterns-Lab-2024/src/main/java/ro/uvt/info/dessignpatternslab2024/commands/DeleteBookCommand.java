package ro.uvt.info.dessignpatternslab2024.commands;

import ro.uvt.info.dessignpatternslab2024.services.BookService;

public class DeleteBookCommand extends SynchronousCommand {
    private final BookService bookService;
    private final int bookId;

    public DeleteBookCommand(BookService bookService, int bookId) {
        this.bookService = bookService;
        this.bookId = bookId;
    }

    @Override
    protected void executeCommand() {
        bookService.deleteBook(bookId);
    }
}
