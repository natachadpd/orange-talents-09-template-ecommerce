package br.com.zupacademy.natacha.mercadolivre.compra;

import br.com.zupacademy.natacha.mercadolivre.compra.clients.NotaFiscal;
import br.com.zupacademy.natacha.mercadolivre.compra.clients.Ranking;
import br.com.zupacademy.natacha.mercadolivre.email.MailerFakeTransacaoSemSucesso;
import br.com.zupacademy.natacha.mercadolivre.email.MailerFakeTransacaoSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FinalizaCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private NotaFiscal nf;

    @Autowired
    private EventoNovaCompra eventosNovaCompra;

    @Autowired
    private MailerFakeTransacaoSucesso mailer;

    @Autowired
    private Ranking ranking;

    @Autowired
    private MailerFakeTransacaoSemSucesso mailerSemSucessos;


    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public String pagSeguro(@PathVariable("id") Long id,
                            @RequestBody @Valid PagSeguroForm pagSeguroForm) {

        return processa(id, pagSeguroForm);
    }


    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String payPal(@PathVariable("id") Long id,
                         @RequestBody @Valid PayPalForm payPalForm) {
        return processa(id, payPalForm);
    }

    private String processa(Long id, RetornoPagamento retornoPagamento) {
        Compra compra = manager.find(Compra.class, id);
        compra.adicionaPagamento(retornoPagamento);
        manager.merge(compra);
        if (compra.processadaComSucesso()) {
            nf.criaNota(compra.getId(), compra.getUsuario().getId());
            ranking.ranking(compra.getId(), compra.getDono().getId());
            eventosNovaCompra.processa(compra);
            mailer.transacao(compra);
        } else {
            mailerSemSucessos.transacao(compra);
        }
        return compra.toString();


    }
}
