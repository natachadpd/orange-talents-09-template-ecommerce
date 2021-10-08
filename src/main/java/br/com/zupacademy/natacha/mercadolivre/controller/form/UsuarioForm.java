package br.com.zupacademy.natacha.mercadolivre.controller.form;


import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import br.com.zupacademy.natacha.mercadolivre.commons.validator.ValorUnico;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotBlank
    @Email
    @ValorUnico(domainClass = Usuario.class, fieldName = "login")
    private String login;

    @Length(min = 6, message = "A senha precisa ter no minimo 6 caracteres")
    @NotBlank
    private String senha;

    public UsuarioForm(@NotBlank @Email String login,  @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsuarioForm(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(){
        String senhaSec = encoder.encode(senha);
        return new Usuario(login,senhaSec);
    }


}

