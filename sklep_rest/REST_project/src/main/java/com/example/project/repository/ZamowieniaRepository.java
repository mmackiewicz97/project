package com.example.project.repository;

import com.example.project.model.Zamowienia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZamowieniaRepository extends JpaRepository<Zamowienia, Integer> {
}
