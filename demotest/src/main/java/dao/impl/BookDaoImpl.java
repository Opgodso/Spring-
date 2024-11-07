package dao.impl;

import dao.BookDao;
import dto.BookRequest;
import Model.Book;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import rowmap.BookRowMap;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Book getBookById(Integer bookid){
        String sql = "SELECT bookid, title, author, image_url, price , published_date, create_date, last_modifier " + "FROM book WHERE book_id = :bookid";

        Map<String, Object> map = new HashMap<>();

        map.put("bookid",bookid);

        List<Book> bookList = namedParameterJdbcTemplate.query(sql,map, new BookRowMap());

        if(!bookList.isEmpty()){
            return bookList.getFirst();
        }else{
            return null;
        }
    }

    @Override
    public Integer createBook(BookRequest bookRequest){
        String sql = "INSERT INTO book(title, author, image_url, price , published_date, create_date, last_modifier)" + "VALUES(:title, :author, :imageUrl, :price , :publishedDate, :createDate, :lastModifier)";
        Map<String, Object> map = new HashMap<>();
        map.put("title", bookRequest.getTitle());
        map.put("author", bookRequest.getAuthor());
        map.put("imageUrl", bookRequest.getImageUrl());
        map.put("price", bookRequest.getPrice());
        map.put("public",bookRequest.getPublishedDate());

        Date now = new Date();

        map.put("create_date", now);
        map.put("last_modifier", now);

        KeyHolder keyHolder =  new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int bookid = keyHolder.getKey().intValue();

        return  bookid;
    }

    @Override
    public void updateBook(Integer bookId,BookRequest bookRequest){
        //用set 表示那些要更新 前面是資料庫的名稱後面是bookrequest 的值
        String sql = "UPDATE book SET title = :title, author = :author, image_url = :imageUrl, price =:price, published_date = :publishedDate,last_modifier = :lastModifiedDate WHERE book_id = :bookId";
        Map<String, Object> map = new HashMap<>();
        map.put("title", bookRequest.getTitle());
        map.put("author", bookRequest.getAuthor());
        map.put("imageUrl", bookRequest.getImageUrl());
        map.put("price", bookRequest.getPrice());
        map.put("publishedDate", bookRequest.getPublishedDate());
        map.put("lastModifiedDate", new Date());
        map.put("bookId", bookId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteBookById(Integer bookId){
        String sql = "DELETE FROM book WHERE book_id = :bookId";
        Map<String, Object> map = new HashMap<>();
        map.put("bookId", bookId);
        namedParameterJdbcTemplate.update(sql, map);
    }

}
