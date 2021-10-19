package br.com.zupacademy.natacha.mercadolivre.opiniao;

import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void inserirOpiniao(@RequestBody @Valid OpiniaoForm opiniaoForm,
                               @AuthenticationPrincipal Usuario usuario) {
        Opiniao opiniao = opiniaoForm.toOpiniao(manager, usuario);
        manager.persist(opiniao);
    }
}
