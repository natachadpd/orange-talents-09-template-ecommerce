package br.com.zupacademy.natacha.mercadolivre.controller.form;

import br.com.zupacademy.natacha.mercadolivre.commons.validator.ExistsId;
import br.com.zupacademy.natacha.mercadolivre.entity.Pergunta;
import br.com.zupacademy.natacha.mercadolivre.entity.Produto;
import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class PerguntaForm {

    @NotBlank
    private String titulo;

    @NotBlank
    private String pergunta;

    @ExistsId(domainClass = Produto.class, fieldName = "id")
    @NotNull
    private Long idProduto;

    public PerguntaForm(@NotBlank String titulo, @NotBlank String pergunta, @NotNull Long idProduto) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.idProduto = idProduto;
    }

    public Pergunta toPergunta(EntityManager manager, Usuario usuario){
        Produto produto = manager.find(Produto.class, idProduto);
        return new Pergunta(titulo, pergunta, usuario, produto);
    }
}
