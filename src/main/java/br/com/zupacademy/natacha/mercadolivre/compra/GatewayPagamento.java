package br.com.zupacademy.natacha.mercadolivre.compra;


import br.com.zupacademy.natacha.mercadolivre.compra.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    pagseguro {
        @Override
        public String geraUrl(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com?returnId=" + compra.getId() + "&redirectUrl=" + urlPagseguro;
        }
    },

    paypal {
        @Override
        public String geraUrl(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "paypal.com?buyerId=" + compra.getId() + "&redirectUrl=" + urlPaypal;
        }
    };

    public abstract String geraUrl(Compra compra, UriComponentsBuilder uriComponentsBuilder);


}
