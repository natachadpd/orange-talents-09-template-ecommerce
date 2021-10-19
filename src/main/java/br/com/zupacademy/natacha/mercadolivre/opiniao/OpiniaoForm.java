package br.com.zupacademy.natacha.mercadolivre.opiniao;

import br.com.zupacademy.natacha.mercadolivre.commons.validator.ExistsId;
import br.com.zupacademy.natacha.mercadolivre.produto.Produto;
import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoForm {

    @Min(1) @Max(5)
    private Integer nota;

    @NotNull
    private String titulo;

    @NotBlank
    @Length(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;




    public OpiniaoForm(@Min(1) @Max(5) Integer nota, @NotNull String titulo,
                       @NotBlank @Length(max = 500) String descricao, @NotNull Long idProduto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }


    public Opiniao toOpiniao(EntityManager manager, Usuario dono){
        Produto produto = manager.find(Produto.class, idProduto);
        return new Opiniao(nota, titulo, descricao, dono, produto);
    }

}
