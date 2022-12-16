package org.book.services;

import org.book.dao.AutorDAO;
import org.book.dao.BookDAO;
import org.book.entity.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AutorServiceImpl implements AutorService{
    @Autowired
    private AutorDAO autorDAO;


    @Override
    @Transactional
    public List<Autor> getAutors() {
        return autorDAO.getAutors();
    }

    @Override
    @Transactional
    public void saveAutor(Autor autor) {
        autorDAO.saveAutor(autor);
    }

    @Override
    @Transactional
    public Autor getAutor(int autorId) {
        return autorDAO.getAutor(autorId);
    }

    @Override
    @Transactional
    public void deleteAutor(int autorId) {
        autorDAO.deleteAutor(autorId);
    }
}
