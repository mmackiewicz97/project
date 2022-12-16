package org.book.services;

import org.book.dao.BookDAO;
import org.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDAO bookDAO;
    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    @Transactional
    public void saveBook(Book ksiazka) {
        bookDAO.saveBook(ksiazka);
    }
    @Override
    @Transactional
    public Book getBook(int bookId) {
        return bookDAO.getBook(bookId);
    }
    @Override
    @Transactional
    public void deleteBook(int bookId) {
        bookDAO.deleteBook(bookId);
    }

}