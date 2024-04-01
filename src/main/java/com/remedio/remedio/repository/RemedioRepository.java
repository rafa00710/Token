package com.remedio.remedio.repository;

import com.remedio.remedio.remedio.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {

    List <Remedio>findAllByAtivoTrue();
}
