package br.com.zupacademy.natacha.mercadolivre.email;

import br.com.zupacademy.natacha.mercadolivre.compra.GatewayPagamento;

public interface MailerTransacaoSemSucesso {
    void enviarEmail(String usuario, GatewayPagamento gateway, String produto, String vendedor, Long compra);
}
