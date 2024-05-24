package com.guilhermesales.apiloginregistro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    
    @NotBlank(message = "Por favor, preencha o campo de email.")
    private String email;

    @NotBlank(message = "Por favor, preencha o campo de senha.")
    private String senha;

}
