package pl.coderslab;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MockBookService implements BookService {
    private List<Book> list;
    private static Long nextId = 4L;


    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa Java.", "Sierra	Kathy, Bates Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion",
                "programming"));
    }


    @Override
    public List<Book> getBooks() {
        return this.list;
    }

    @Override
    public Optional<Book> get(Long id) {
        Book bookById = new Book();
        for (Book book : this.list) {
            if (book.getId().equals(id)) {
                bookById = book;
            }
        }
        return Optional.of(bookById);
    }

    @Override
    public void add(Book book) {
        book.setId(nextId);
        nextId = nextId + 1;
        list.add(book);
    }

    @Override
    public void delete(Long id) {
        for (Book book : this.list) {
            if (book.getId().equals(id)) {
                list.remove(book);
            }
        }
    }

    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()) {
            int index = list.indexOf(this.get(book.getId()).get());
            list.set(index, book);
        }
    }
}
