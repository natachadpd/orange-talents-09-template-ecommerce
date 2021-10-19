package br.com.zupacademy.natacha.mercadolivre.compra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "rankingFeign", url = "http://localhost:8080/rankingSistema/ranking")
public interface Ranking {

    @PostMapping(value = "/{idCompra}/{idDono}")
    void ranking(@PathVariable Long idCompra, @PathVariable Long idDono);
}
