package br.com.zupacademy.natacha.mercadolivre.compra;

public interface RetornoPagamento {

    Transacao toTransacao(Compra compra);
}
