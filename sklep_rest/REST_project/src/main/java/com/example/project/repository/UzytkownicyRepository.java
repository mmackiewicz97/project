package com.example.project.repository;

import com.example.project.model.Uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UzytkownicyRepository extends JpaRepository<Uzytkownicy, Integer> {

}
