package br.com.zupacademy.natacha.mercadolivre.controller;

import br.com.zupacademy.natacha.mercadolivre.controller.form.ImagemForm;
import br.com.zupacademy.natacha.mercadolivre.controller.form.ProdutoForm;
import br.com.zupacademy.natacha.mercadolivre.controller.form.Uploader;
import br.com.zupacademy.natacha.mercadolivre.entity.Produto;
import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import br.com.zupacademy.natacha.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController<imagemForm> {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Uploader uploaderFake;

    @Autowired
    private UsuarioRepository usuarioRepository;



    @PostMapping
    @Transactional
    public void cadastraProduto(@RequestBody @Valid ProdutoForm produtoForm) {
        Usuario dono = usuarioRepository.findByLogin("teste2@teste.com").get();
        Produto produto = produtoForm.toProduto(manager, dono);

        manager.persist(produto);
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public String adicionarImagens(@PathVariable("id") Long id, @Valid ImagemForm imagemForm){

        Usuario dono = usuarioRepository.findByLogin("teste2@teste.com").get();
        Produto produto = manager.find(Produto.class, id);

        if(!produto.pertenceAoUsuario(dono)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        }

        Set<String> links = uploaderFake.envia(imagemForm.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);

        return produto.toString();

    }



}
