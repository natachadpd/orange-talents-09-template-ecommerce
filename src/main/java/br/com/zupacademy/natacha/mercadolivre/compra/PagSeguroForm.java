package br.com.zupacademy.natacha.mercadolivre.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroForm implements RetornoPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagSeguro status;

    public PagSeguroForm(@NotBlank String idTransacao,
                         @NotNull StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagSeguroForm{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {

        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
