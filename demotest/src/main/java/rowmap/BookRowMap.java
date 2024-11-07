package rowmap;
import Model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BookRowMap implements RowMapper<Book> {
    /**
     * 將 ResultSet 中的每一行數據映射為 Book 物件。
     *
     * @param rs    包含查詢結果的 ResultSet 對象
     * @param rowNum 當前行的編號
     * @return Book 包含此行數據的 Book 物件
     * @throws SQLException 如果在讀取數據時發生 SQL 錯誤
     */
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setAuthor(rs.getString("author"));
        book.setTitle(rs.getString("title"));
        book.setImageUrl(rs.getString("image_url"));
        book.setPublishedDate(rs.getDate("published_date"));
        book.setPrice(rs.getInt("price"));
        book.setCreatedDate(rs.getDate("created_date"));
        book.setLastModifiedDate(rs.getDate("last_modified_date"));
        return book;
    }
}
