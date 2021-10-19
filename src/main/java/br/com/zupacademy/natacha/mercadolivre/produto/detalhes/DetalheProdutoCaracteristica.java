package br.com.zupacademy.natacha.mercadolivre.produto.detalhes;

import br.com.zupacademy.natacha.mercadolivre.produto.caracteristicas.Caracteristicas;

public class DetalheProdutoCaracteristica {

    private String nome;
    private String descricao;

    public DetalheProdutoCaracteristica(Caracteristicas caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
