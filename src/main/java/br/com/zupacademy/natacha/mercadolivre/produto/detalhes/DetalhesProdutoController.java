package br.com.zupacademy.natacha.mercadolivre.produto.detalhes;

import br.com.zupacademy.natacha.mercadolivre.produto.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/detalhes")
public class DetalhesProdutoController {

    @PersistenceContext
    private EntityManager manager;


    @GetMapping("/produtos/{id}")
    @Transactional
    public DetalhesProdutoDto getById(@PathVariable("id") Long id) {
        Produto produto = manager.find(Produto.class, id);
        return new DetalhesProdutoDto(produto);
    }

}
