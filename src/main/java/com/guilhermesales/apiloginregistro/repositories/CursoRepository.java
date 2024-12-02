package com.guilhermesales.apiloginregistro.repositories;

import org.springframework.stereotype.Repository;

import com.guilhermesales.apiloginregistro.models.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    Curso findByNome(String nome);
    List<Curso> findAll();

}
