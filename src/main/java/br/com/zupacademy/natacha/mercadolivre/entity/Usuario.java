package br.com.zupacademy.natacha.mercadolivre.entity;

import br.com.zupacademy.natacha.mercadolivre.commons.utils.HashSenha;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @PastOrPresent
    private LocalDateTime dataHora;

    @NotBlank
    @Email
    private String login;

    @Length(min = 6, message = "A senha precisa ter no minimo 6 caracteres")
    @NotBlank
    private String senha;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = HashSenha.hashSenha(senha);
        this.dataHora = LocalDateTime.now();
    }



}


