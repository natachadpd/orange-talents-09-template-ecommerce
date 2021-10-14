package br.com.zupacademy.natacha.mercadolivre.enums;


import br.com.zupacademy.natacha.mercadolivre.entity.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAGSEGURO {
        @Override
        public String geraUrl(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlPagseguro = uriComponentsBuilder.path("/pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com?returnId=" + compra.getId() + "&redirectUrl=" + urlPagseguro;
        }
    },

    PAYPAL {
        @Override
        public String geraUrl(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlPaypal = uriComponentsBuilder.path("/paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "paypal.com?buyerId=" + compra.getId() + "&redirectUrl=" + urlPaypal;
        }
    };

    public abstract String geraUrl(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
