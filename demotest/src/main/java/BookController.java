import dto.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Model.Book;
import server.BookServer;

@RestController
public class BookController {
    @Autowired
    private BookServer bookserver;

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Integer bookId){
        Book book = bookserver.GetBookByID(bookId);

        if(book != null){
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest){
        Integer bookId = bookserver.createBook(bookRequest);

        Book book = bookserver.GetBookByID(bookId);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody BookRequest bookRequest){

        Book book = bookserver.GetBookByID(bookId);

        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        bookserver.updateBook(bookId, bookRequest);

        Book updateBook = bookserver.GetBookByID(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

   @DeleteMapping("/books/{bookId}")
   public ResponseEntity<?> deleteBook(@PathVariable Integer bookId){
        bookserver.deleteBookById(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
   }
}
