package com.example.project.repository;

import com.example.project.model.Opinie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OpinieRepository extends JpaRepository<Opinie, Integer> {
}
