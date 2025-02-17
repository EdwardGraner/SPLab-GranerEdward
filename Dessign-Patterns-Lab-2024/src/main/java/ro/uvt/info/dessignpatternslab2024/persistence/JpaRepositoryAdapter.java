package ro.uvt.info.dessignpatternslab2024.persistence;

import org.springframework.stereotype.Component;
import ro.uvt.info.dessignpatternslab2024.models.Book;

import java.util.List;
import java.util.Optional;


@Component
public class JpaRepositoryAdapter implements CrudRepository<Book, Integer> {

    private final BooksRepository booksRepository;

    public JpaRepositoryAdapter(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Book save(Book book) {
        return booksRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return booksRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        booksRepository.deleteById(id);
    }
}
