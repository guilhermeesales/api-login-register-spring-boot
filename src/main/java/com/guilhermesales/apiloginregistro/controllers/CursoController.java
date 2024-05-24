package com.guilhermesales.apiloginregistro.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilhermesales.apiloginregistro.models.Curso;
import com.guilhermesales.apiloginregistro.models.Curso.CreateCurso;
import com.guilhermesales.apiloginregistro.services.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/services/cursos")
@Validated
public class CursoController {
    
    // Aqui ficará a aba para cadastrar os cursos, que posteriormente será atribuido 
    // aos usuários que serão cadastrados

    @Autowired
    private CursoService cursoService;

    @PostMapping("/registration")
    @Validated(CreateCurso.class)
    public ResponseEntity<String> create(@Valid @RequestBody Curso obj) {
        this.cursoService.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(obj.getId()).toUri();
        
        ResponseEntity.created(uri).build();

        return ResponseEntity.ok("Cadastrado com sucesso");

    }
    



}
