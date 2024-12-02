package com.guilhermesales.apiloginregistro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermesales.apiloginregistro.models.Curso;
import com.guilhermesales.apiloginregistro.repositories.CursoRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Curso create(Curso obj) {
        Curso procurarPeloNome = cursoRepository.findByNome(obj.getNome().toLowerCase());
        
        if(procurarPeloNome != null) {
            throw new IllegalArgumentException("O curso de " + obj.getNome() + " já foi cadastrado");

        }

        obj.setId(null);
        obj = this.cursoRepository.save(obj);
        return obj;
        

    }


    public List<Curso> findAll() {
        return this.cursoRepository.findAll();

    }


}
