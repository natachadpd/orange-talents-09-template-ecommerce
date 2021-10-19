package br.com.zupacademy.natacha.mercadolivre.email;

import br.com.zupacademy.natacha.mercadolivre.compra.Compra;
import br.com.zupacademy.natacha.mercadolivre.compra.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MailerFakeTransacaoSucesso implements MailerTransacaoSucesso {

    @Autowired
    private MailerTransacaoSucesso mailerTransacaoSucesso;

    @Override
    public void enviarEmail(String usuario, GatewayPagamento gateway, String produto, String vendedor) {
        System.out.println("=======================[RESUMO DA COMPRA]==================================");
        System.out.println("Obaa! Parabéns, sua compra foi concluída com sucesso!!");
        System.out.println("Comprador: " + usuario);
        System.out.println("Meio de pagamento escolhido: " + gateway);
        System.out.println("Produto: " + produto);
        System.out.println("Vendedor: " + vendedor);
        System.out.println("============================================================================");

    }

    public void transacao(Compra compra) {
        mailerTransacaoSucesso.enviarEmail(
                compra.getUsuario().getLogin(),
                compra.getGateway(),
                compra.getProduto().getNomeProduto(),
                compra.getDono().getLogin());
    }


}
