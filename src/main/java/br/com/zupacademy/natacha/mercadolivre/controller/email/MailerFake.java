package br.com.zupacademy.natacha.mercadolivre.controller.email;

import br.com.zupacademy.natacha.mercadolivre.entity.Pergunta;
import br.com.zupacademy.natacha.mercadolivre.entity.Produto;
import br.com.zupacademy.natacha.mercadolivre.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@Primary
public class MailerFake implements  Mailer {

    @Autowired
    private Mailer mailer;


    @Override
    public void enviar(String produto, String remetente, String para, String titulo, String pergunta) {
        System.out.println(produto);
        System.out.println(remetente);
        System.out.println(para);
        System.out.println(titulo);
        System.out.println(pergunta);

    }


    public void novaPergunta(@NotNull @Valid Pergunta pergunta, Usuario usuario, Produto produto) {
        mailer.enviar("Pergunta sobre o produto: " + produto.getNomeProduto(),
                     "Usuário interessado: " + usuario.getLogin() ,
                         "Destinatário: duvidas@mercadolivre.com.br",
                         "Título: "+ pergunta.getTitulo(),
                      "Pergunta: "+ pergunta.getPergunta() );
    }

}
