package br.com.zupacademy.natacha.mercadolivre.controller;

import br.com.zupacademy.natacha.mercadolivre.controller.form.ProdutoForm;
import br.com.zupacademy.natacha.mercadolivre.entity.Produto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;



    @PostMapping
    @Transactional
    public void cadastraProduto(@RequestBody @Valid ProdutoForm produtoForm) {
        Produto produto = produtoForm.toProduto(manager);
        manager.persist(produto);
    }
}
