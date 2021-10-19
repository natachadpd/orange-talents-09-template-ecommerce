package br.com.zupacademy.natacha.mercadolivre.email;

import br.com.zupacademy.natacha.mercadolivre.compra.Compra;
import br.com.zupacademy.natacha.mercadolivre.compra.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MailerFakeTransacaoSemSucesso implements MailerTransacaoSemSucesso {

    @Autowired
    private MailerTransacaoSemSucesso mailerTransacao;

    @Override
    public void enviarEmail(String usuario, GatewayPagamento gateway, String produto, String vendedor, Long compra) {
        System.out.println("=======================[RESUMO DA COMPRA]====================================================================");
        System.out.println("Que pena! Não foi possível concluir a compra, refaça o processo: http://localhost:8080/retorno-" + gateway + "/" + compra);
        System.out.println("Comprador: " + usuario);
        System.out.println("Meio de pagamento escolhido: " + gateway);
        System.out.println("Produto: " + produto);
        System.out.println("Vendedor: " + vendedor);
        System.out.println("=============================================================================================================");

    }

    public void transacao(Compra compra) {
        mailerTransacao.enviarEmail(
                compra.getUsuario().getLogin(),
                compra.getGateway(),
                compra.getProduto().getNomeProduto(),
                compra.getDono().getLogin(),
                compra.getId());
    }
}
