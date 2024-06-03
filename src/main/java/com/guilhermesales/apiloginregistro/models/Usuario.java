package com.guilhermesales.apiloginregistro.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Usuario {

    public interface CreateUsuario { }
    public interface UpdateUsuario { }

    public static final String TABLE_NAME = "Usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome_completo", length = 100, nullable = false, unique = false)
    @NotBlank(groups = {CreateUsuario.class, UpdateUsuario.class}, message = "O campo nome completo não pode estar em branco")
    @Size(groups = {CreateUsuario.class}, min = 10, max = 100, message = "O nome completo deve ter entre 10 e 100 caracteres")
    private String nomeCompleto;

    @Email(message = "Por favor, entre com um formato de email válido")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                      "(alu\\.ufc\\.br|ufc\\.br)$",
             message = "Por favor, entre com um email válido da UFC")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(groups = {CreateUsuario.class, UpdateUsuario.class}, message = "O campo email não pode estar em branco")
    @Size(groups = {CreateUsuario.class}, min = 10, max = 100, message = "O email deve ter entre 10 e 100 caracteres")
    private String email;

    @Column(name = "matricula", length = 20, nullable = false, unique = true)
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class}, message = "O campo matrícula não pode estar em branco")
    @Min(value = 1, groups = {CreateUsuario.class}, message = "A matrícula deve ser um número positivo")
    private Long matricula;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[!@#$%^&*()\\-+=])(?=.*[A-Z]).*$|^.*(?=.*[A-Z]).*$",
             message = "A senha deve conter pelo menos uma letra maiúscula ou um caractere especial")
    @Column(name = "senha", length = 60, nullable = false, unique = false)
    @NotBlank(groups = {CreateUsuario.class, UpdateUsuario.class}, message = "O campo senha não pode estar em branco")
    @Size(groups = {CreateUsuario.class, UpdateUsuario.class}, min = 7, max = 60, message = "A senha deve ter entre 7 e 60 caracteres")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    @NotNull(groups = {CreateUsuario.class, UpdateUsuario.class}, message = "O campo curso não pode estar em branco")
    private Curso curso;

}
