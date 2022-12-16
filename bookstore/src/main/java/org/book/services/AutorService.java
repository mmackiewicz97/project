package org.book.services;

import org.book.entity.Autor;

import java.util.List;

public interface AutorService {
    List<Autor> getAutors();
    void saveAutor(Autor autor);
    Autor getAutor(int autorId);
    void deleteAutor(int autorId);
}
