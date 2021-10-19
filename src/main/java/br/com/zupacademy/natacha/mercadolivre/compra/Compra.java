package br.com.zupacademy.natacha.mercadolivre.compra;

import br.com.zupacademy.natacha.mercadolivre.produto.Produto;
import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Enumerated
    @NotNull
    private Status status;

    @Deprecated
    public Compra() {
    }


    public Compra(Produto produto, @Positive @Valid @Min(1) Integer quantidade,
                  Usuario comprador, @NotNull GatewayPagamento gatewayPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = comprador;
        this.gatewayPagamento = gatewayPagamento;
        this.status = Status.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public Usuario getDono() {
        return produto.getDono();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public GatewayPagamento getGateway() {
        return gatewayPagamento;
    }

    public Produto getProduto() {
        return produto;
    }


    public String urlGateway(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.geraUrl(this, uriComponentsBuilder);
    }


    public void adicionaPagamento(@Valid RetornoPagamento retornoForm) {
        Transacao novaTransacao = retornoForm.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Transação já foi realizada!" + novaTransacao);
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(),
                "Essa transação já foi concluída com sucesso!");

        this.transacoes.add(novaTransacao);
    }


    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
                "Tem mais de uma transação concluída com sucesso na compra!" + this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }


    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", usuario=" + usuario +
                ", gatewayPagamento=" + gatewayPagamento +
                ", transacoes=" + transacoes +
                '}';
    }

}
