package server.impl;

import Model.Book;
import dao.BookDao;
import dto.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.BookServer;

@Component
public class BookServerImpl implements BookServer {
    @Autowired
    BookDao bookDao;

    @Override
    public Book GetBookByID(Integer bookId){
        return bookDao.getBookById(bookId);
    }

    @Override
    public Integer createBook(BookRequest bookRequest){
        return bookDao.createBook(bookRequest);
    }

    @Override
    public void updateBook(Integer bookId,BookRequest bookRequest){
        bookDao.updateBook(bookId,bookRequest);
    }

    @Override
    public void deleteBookById(Integer bookId){
        bookDao.deleteBookById(bookId);
    }
}
