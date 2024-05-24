package com.guilhermesales.apiloginregistro.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.guilhermesales.apiloginregistro.dto.DefaultError;
import com.guilhermesales.apiloginregistro.dto.LoginRequest;
import com.guilhermesales.apiloginregistro.models.Usuario;
import com.guilhermesales.apiloginregistro.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository userRepository;


    // O controlador irá chamar essas funções futuramente...
    public Usuario procurarPelaMatricula(Long mat) {
        Usuario usuario = this.userRepository.findByMatricula(mat);  // Corrige o nome do método
        
        if(usuario == null) {
            throw new IllegalArgumentException("Não foi encontrado usuário com essa matrícula!");
        }

        return usuario;

    }

    @Transactional // é interessante utilizar para quando for persistir os dados.
    public Usuario create(Usuario obj) {
        Usuario procurarPelaMatricula = userRepository.findByMatricula(obj.getMatricula());

        if(procurarPelaMatricula != null) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com essa matrícula!");

        }

        Usuario procurarPeloEmail = userRepository.findByEmail(obj.getEmail());
        
        if(procurarPeloEmail != null) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com esse email");

        }

        obj.setId(null);  // Zerar o id para criar um novo usuário
        obj = this.userRepository.save(obj);
        return obj;

    }

    @Transactional 
    public Usuario update(Usuario obj) {
        // Verifica se o usuário realmente existe
        Usuario usuarioObj = procurarPelaMatricula(obj.getMatricula());
        usuarioObj.setNomeCompleto(obj.getNomeCompleto());
        usuarioObj.setSenha(obj.getSenha());
        usuarioObj.setCurso(obj.getCurso());
        return this.userRepository.save(obj);

    }

    public void delete(Long mat) {
        this.procurarPelaMatricula(mat); // Verifica se o usuário existe

        try {
            this.userRepository.deleteByMatricula(mat);

        } catch(Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");
        } 

    }

    public DefaultError authenticate(LoginRequest loginRequest) {
        // Verifica se o email está cadastrado
        Usuario usuario = userRepository.findByEmail(loginRequest.getEmail());
        
        if(usuario != null && usuario.getSenha().equals(loginRequest.getSenha())) {
            return new DefaultError(HttpStatus.OK.value(), "Login realizado com sucesso");

        } else {
            return new DefaultError(HttpStatus.UNAUTHORIZED.value(), "Usuário e/ou senha incorretos");

        }

    }

}
