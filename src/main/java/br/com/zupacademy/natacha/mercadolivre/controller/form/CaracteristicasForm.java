package br.com.zupacademy.natacha.mercadolivre.controller.form;

import br.com.zupacademy.natacha.mercadolivre.entity.Caracteristicas;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicasForm {

    @NotBlank
    private String nome;
    private String descricao;


    public CaracteristicasForm(@NotBlank String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;

    }

    public Caracteristicas toCaracteristicas(){
        return new Caracteristicas (nome, descricao);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicasForm that = (CaracteristicasForm) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }
}
