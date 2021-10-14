package br.com.zupacademy.natacha.mercadolivre.controller.form;

import br.com.zupacademy.natacha.mercadolivre.entity.Compra;
import br.com.zupacademy.natacha.mercadolivre.entity.Produto;
import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import br.com.zupacademy.natacha.mercadolivre.enums.GatewayPagamento;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {

    @Valid
    @NotNull
    private Long idProduto;

    @Valid
    @Positive
    @NotNull
    private Integer quantidade;

    @Valid
    @NotNull
    private GatewayPagamento gateway;



    public CompraForm(@Valid @NotNull Long idProduto, @Valid @Positive @NotNull Integer quantidade, @Valid @NotNull  GatewayPagamento gateway) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gateway = gateway;
    }


    public Compra toCompra(Produto produto, Usuario usuario){
        return new Compra (produto, this.quantidade, usuario, gateway);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
