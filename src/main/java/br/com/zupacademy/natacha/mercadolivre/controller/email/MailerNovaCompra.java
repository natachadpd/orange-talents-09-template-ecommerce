package br.com.zupacademy.natacha.mercadolivre.controller.email;

import br.com.zupacademy.natacha.mercadolivre.enums.GatewayPagamento;

import java.math.BigDecimal;

public interface MailerNovaCompra {
    void emailNovaCompra(String remetente, GatewayPagamento gateway, String produto, Integer quantidade, BigDecimal valor);


}
