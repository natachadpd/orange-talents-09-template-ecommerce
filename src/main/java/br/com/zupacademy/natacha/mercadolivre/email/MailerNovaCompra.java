package br.com.zupacademy.natacha.mercadolivre.email;

import br.com.zupacademy.natacha.mercadolivre.compra.GatewayPagamento;

public interface MailerNovaCompra {
    void emailNovaCompra(String remetente, GatewayPagamento gateway, String produto, Integer quantidade);


}
