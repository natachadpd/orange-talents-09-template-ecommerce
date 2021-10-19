package br.com.zupacademy.natacha.mercadolivre.categoria;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {


    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void criarCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        Categoria categoria = categoriaForm.toCategoria(manager);
        manager.persist(categoria);

    }
}