package br.com.zupacademy.natacha.mercadolivre.compra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "nf", url = "http://localhost:8080/notaSistema/notas-fiscais")
public interface NotaFiscal {

    @PostMapping(value = "{idCompra}/{idComprador}")
    void criaNota(@PathVariable Long idCompra, @PathVariable Long idComprador);
}
