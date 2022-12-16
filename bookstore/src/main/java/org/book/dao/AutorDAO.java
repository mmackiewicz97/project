package org.book.dao;

import org.book.entity.Autor;

import java.util.List;

public interface AutorDAO {
    List<Autor> getAutors();
    void saveAutor(Autor autor);
    Autor getAutor(int autorId);
    void deleteAutor(int autorId);
}
