package br.com.zupacademy.natacha.mercadolivre.categoria;


import br.com.zupacademy.natacha.mercadolivre.commons.validator.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;


public class CategoriaForm {


    @NotBlank
    @ValorUnico(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    private Long idCategoriaMae;

    public CategoriaForm(@NotBlank String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }


    public Categoria toCategoria(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if (idCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }


}