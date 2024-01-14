package com.example.project;

public class KategorieNotFoundException extends RuntimeException {
    public KategorieNotFoundException(int id) {
        super("Nie można znaleźć kategorii id: "+id);
    }
}
