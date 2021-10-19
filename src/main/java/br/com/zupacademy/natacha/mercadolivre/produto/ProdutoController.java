package br.com.zupacademy.natacha.mercadolivre.produto;

import br.com.zupacademy.natacha.mercadolivre.produto.imagem.ImagemForm;
import br.com.zupacademy.natacha.mercadolivre.produto.imagem.Uploader;
import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import br.com.zupacademy.natacha.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public void cadastraProduto(@RequestBody @Valid ProdutoForm produtoForm,
                                @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoForm.toProduto(manager, usuario);

        manager.persist(produto);
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public String adicionarImagens(@PathVariable("id") Long id, @Valid ImagemForm imagemForm){

        Usuario dono = usuarioRepository.findByLogin("usuario.teste@teste.com").get();
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
