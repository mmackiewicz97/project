package org.book.dao;

import org.book.entity.Book;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

public interface BookDAO {
    List<Book> getBooks();
    void saveBook(Book book);
    Book getBook(int bookId);
    void deleteBook(int bookId);
}
