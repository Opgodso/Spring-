package dao;

import dto.BookRequest;
import Model.Book;
public interface BookDao {

    Book getBookById(Integer bookId);

    Integer createBook(BookRequest bookRequest);

    void updateBook(Integer bookId,BookRequest bookRequest);

    void deleteBookById(Integer bookId);
}
