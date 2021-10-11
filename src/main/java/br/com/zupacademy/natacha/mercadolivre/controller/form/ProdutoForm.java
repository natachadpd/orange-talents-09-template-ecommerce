package br.com.zupacademy.natacha.mercadolivre.controller.form;

import br.com.zupacademy.natacha.mercadolivre.commons.validator.ExistsId;
import br.com.zupacademy.natacha.mercadolivre.entity.Caracteristicas;
import br.com.zupacademy.natacha.mercadolivre.entity.Categoria;
import br.com.zupacademy.natacha.mercadolivre.entity.Produto;
import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoForm {



    @NotBlank
    private String nomeProduto;

    @NotNull
    @Min(1)
    private BigDecimal valor;

    @PositiveOrZero
    @NotNull
    private Integer qtdDisponivel;

    private Set<CaracteristicasForm> caracteristicas;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long id;



    @NotBlank
    @Length(max = 1000, message = "Máximo 1000 caracteres")
    private String descricao;

    public ProdutoForm(@NotBlank String nomeProduto, @NotNull @Min(1) BigDecimal valor, @PositiveOrZero @NotNull Integer qtdDisponivel,
                       @Size(min=3) Set<CaracteristicasForm> caracteristicas, @NotNull Long id, @NotBlank @Length(max = 1000) String descricao) {
        this.nomeProduto = nomeProduto;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.caracteristicas = caracteristicas;
        this.id = id;
        this.descricao = descricao;

    }

    public Produto toProduto(EntityManager manager, Usuario dono) {
            Set<Caracteristicas> caracteristicasModelo = caracteristicas.stream()
                    .map(c -> c.toCaracteristicas()).collect(Collectors.toSet());
            Categoria categoria = manager.find(Categoria.class, id);
        return new Produto(nomeProduto, valor, qtdDisponivel, caracteristicasModelo, categoria, descricao, dono);
        }



    }
