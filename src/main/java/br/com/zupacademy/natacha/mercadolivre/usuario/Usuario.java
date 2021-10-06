package br.com.zupacademy.natacha.mercadolivre.usuario;

import br.com.zupacademy.natacha.mercadolivre.utils.HashSenha;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime dataHora= LocalDateTime.now();
    @NotNull
    @NotBlank
    @Email
    private String login;
    @NotNull
    @Length(min = 6, message = "A senha precisa ter no minimo 6 caracteres")
    @NotBlank
    private String senha;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotNull @NotBlank @Email String login, @NotNull @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = HashSenha.hashSenha(senha);
    }



}


