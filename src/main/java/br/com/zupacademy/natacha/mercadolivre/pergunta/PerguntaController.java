package br.com.zupacademy.natacha.mercadolivre.pergunta;

import br.com.zupacademy.natacha.mercadolivre.email.EmailSend;
import br.com.zupacademy.natacha.mercadolivre.email.MailerEmail;
import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/perguntas")
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EmailSend emailSend;


    @PostMapping
    @Transactional
    public void cadastrarPerguntas(@RequestBody @Valid PerguntaForm perguntaForm,
                                   @AuthenticationPrincipal Usuario usuario) {
        Pergunta pergunta = perguntaForm.toPergunta(manager, usuario);
        manager.persist(pergunta);

        emailSend.novaPergunta(pergunta, pergunta.getProduto());
//        emails.novaPergunta(pergunta, usuario, pergunta.getProduto());
    }
}
