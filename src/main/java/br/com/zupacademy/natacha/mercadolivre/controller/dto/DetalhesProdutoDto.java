package br.com.zupacademy.natacha.mercadolivre.controller.dto;

import br.com.zupacademy.natacha.mercadolivre.entity.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class DetalhesProdutoDto {

    private String nomeProduto;
    private BigDecimal precoProduto;
    private String descricaoProduto;
    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String, String>> opinioes;
    private Double mediaNotas;
    private Integer totalNotas;


    public DetalhesProdutoDto(Produto produto) {
        this.nomeProduto = produto.getNomeProduto();
        this.precoProduto = produto.getValor();
        this.descricaoProduto = produto.getDescricao();
        this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());

        Opinioes opinioes = produto.getOpinioes();

        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });

        this.mediaNotas = opinioes.media();
        this.totalNotas = opinioes.total();
    }


    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }
}
