package com.cibertec.demo.repositories;

import com.cibertec.demo.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}

