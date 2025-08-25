package pl.coderslab;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/books", produces = "application/json; charset=UTF-8")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }


    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int id) {
        Long longId = (long) id;
        Book book = new Book();
        if (bookService.get(longId).isPresent()) {
            book = bookService.get(longId).get();
        }
        return book;
    }


    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }


    @PutMapping("")
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") int id) {
        bookService.delete((long) id);
    }
}

