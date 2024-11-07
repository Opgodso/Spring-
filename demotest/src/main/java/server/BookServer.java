package server;
import Model.Book;
import dto.BookRequest;


public interface BookServer {

    Book GetBookByID(Integer bookId);

    Integer createBook(BookRequest bookRequest);

    void updateBook(Integer bookId, BookRequest bookRequest);

    void deleteBookById(Integer bookId);
}
