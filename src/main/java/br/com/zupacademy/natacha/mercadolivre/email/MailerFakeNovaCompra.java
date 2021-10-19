package br.com.zupacademy.natacha.mercadolivre.email;

import br.com.zupacademy.natacha.mercadolivre.compra.CompraForm;
import br.com.zupacademy.natacha.mercadolivre.produto.Produto;
import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import br.com.zupacademy.natacha.mercadolivre.compra.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Primary
public class MailerFakeNovaCompra implements MailerNovaCompra {

    @Autowired
    private MailerNovaCompra mailerNovaCompra;


    @Override
    public void emailNovaCompra(String remetente, GatewayPagamento gateway, String produto, Integer quantidade) {
        System.out.println("===============================[NOVA COMPRA]==============================");
        System.out.println("Compra iniciada com sucesso!");
        System.out.println("Comprador: " + remetente);
        System.out.println("Meio de pagamento: " + gateway);
        System.out.println("Produto escolhido: " + produto);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("===========================================================================");


    }

    public void novaCompra(Usuario usuario, Produto produto, CompraForm compraForm) {
        mailerNovaCompra.emailNovaCompra(usuario.getLogin(),
                compraForm.getGateway(),
                produto.getNomeProduto(),
                compraForm.getQuantidade());
    }
}
