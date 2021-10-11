package br.com.zupacademy.natacha.mercadolivre.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Caracteristicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    public Caracteristicas() {
    }

    @Deprecated


    public Caracteristicas(@NotBlank String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caracteristicas that = (Caracteristicas) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }
}
