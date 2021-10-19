package br.com.zupacademy.natacha.mercadolivre.compra.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rankingSistema")
public class RankingController {
    @PostMapping(value = "/ranking/{idCompra}/{idDono}")
    public void ranking(@PathVariable Long idCompra, @PathVariable Long idDono){
        System.out.println("Ranking vendedores");
    }
}
