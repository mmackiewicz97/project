package org.book.services;

import org.book.entity.Book;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

public interface BookService {
    List<Book> getBooks();
    void saveBook(Book ksiazka);
    Book getBook(int bookId);
    void deleteBook(int bookId);
}