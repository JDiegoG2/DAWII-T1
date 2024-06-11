package com.cibertec.demo.repositories;

import com.cibertec.demo.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
}
