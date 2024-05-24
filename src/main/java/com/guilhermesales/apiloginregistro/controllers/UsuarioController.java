package com.guilhermesales.apiloginregistro.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilhermesales.apiloginregistro.dto.DefaultError;
import com.guilhermesales.apiloginregistro.dto.LoginRequest;
import com.guilhermesales.apiloginregistro.models.Usuario;
import com.guilhermesales.apiloginregistro.models.Usuario.CreateUsuario;
import com.guilhermesales.apiloginregistro.models.Usuario.UpdateUsuario;
import com.guilhermesales.apiloginregistro.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("services/alunos")
@Validated
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    //obtem os dados do usuário através da matricula    
    @GetMapping("/user/{matricula}")
    public ResponseEntity<Usuario> findByMatricula(@PathVariable Long matricula) {
        Usuario usuario = this.usuarioService.procurarPelaMatricula(matricula);
        return ResponseEntity.ok().body(usuario);

    }

    @PostMapping("/registration")
    @Validated(CreateUsuario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Usuario obj) {
        this.usuarioService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(obj.getId()).toUri();
        
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/user/update/{matricula}")
    @Validated(UpdateUsuario.class)
    public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario obj, @PathVariable Long matricula) {
        obj.setMatricula(matricula);
        obj = this.usuarioService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/login")
    public ResponseEntity<DefaultError> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        DefaultError usuario = usuarioService.authenticate(loginRequest);
        return ResponseEntity.ok().body(usuario);

    }
}
