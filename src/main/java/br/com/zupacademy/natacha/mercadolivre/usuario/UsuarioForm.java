package br.com.zupacademy.natacha.mercadolivre.usuario;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioForm {

    @NotBlank
    @NotNull
    @Email
    private String login;
    @NotNull
    @Length(min = 6, message = "A senha precisa ter no minimo 6 caracteres")
    @NotBlank
    private String senha;

    public UsuarioForm(@NotNull @NotBlank @Email String login, @NotNull @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(login,senha);
    }
}

