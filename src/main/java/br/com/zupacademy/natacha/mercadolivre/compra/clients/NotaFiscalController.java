package br.com.zupacademy.natacha.mercadolivre.compra.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notaSistema")
public class NotaFiscalController {
    @PostMapping("/notas-fiscais/{idCompra}/{idComprador}")
    public void criaNota(@PathVariable Long idCompra, @PathVariable Long idComprador){
        System.out.println("Nota fiscal gerada ");
    }
}
