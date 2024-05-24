package com.guilhermesales.apiloginregistro.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermesales.apiloginregistro.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByEmail(String email);
    Usuario findByMatricula(Long matricula);
    Usuario deleteByMatricula(Long matricula);
}
