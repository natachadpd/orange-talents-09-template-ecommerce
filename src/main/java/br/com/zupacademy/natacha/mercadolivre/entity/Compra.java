package br.com.zupacademy.natacha.mercadolivre.entity;

import br.com.zupacademy.natacha.mercadolivre.enums.GatewayPagamento;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @Positive
    @Valid
    @Min(1)
    private Integer quantidade;

    @ManyToOne
    private Usuario usuario;

    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;

    @Deprecated
    public Compra() {
    }


    public Compra(Produto produto, @Positive @Valid @Min(1) Integer quantidade,
                  Usuario comprador, @NotNull GatewayPagamento gatewayPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = comprador;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Long getId() {
        return id;
    }


    public String urlGateway(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.geraUrl(this, uriComponentsBuilder);
    }
}
