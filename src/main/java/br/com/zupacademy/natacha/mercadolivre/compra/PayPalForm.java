package br.com.zupacademy.natacha.mercadolivre.compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PayPalForm implements RetornoPagamento {

    @Min(0)
    @Max(1)
    private Integer status;
    @NotBlank
    private String idTransacao;

    public PayPalForm(Integer status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra) {
        @NotNull StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro
                : StatusTransacao.sucesso;

        return new Transacao(statusCalculado, idTransacao, compra);
    }
}

