package com.example.project.repository;

import com.example.project.model.Producenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProducenciRepository extends JpaRepository<Producenci, Integer> {
}
