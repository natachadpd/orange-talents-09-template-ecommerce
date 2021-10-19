package br.com.zupacademy.natacha.mercadolivre.email;

import br.com.zupacademy.natacha.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.natacha.mercadolivre.produto.Produto;
import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@Primary
public class MailerFakePerguntas implements MailerPerguntas {

    @Autowired
    private MailerPerguntas mailerPerguntas;


    @Override
    public void enviar(String produto, String remetente, String para, String titulo, String pergunta) {
        System.out.println("============================[NOVA PERGUNTA]==============================");
        System.out.println(produto);
        System.out.println(remetente);
        System.out.println(para);
        System.out.println(titulo);
        System.out.println(pergunta);
        System.out.println("==========================================================================");

    }


    public void novaPergunta(@NotNull @Valid Pergunta pergunta, Usuario usuario, Produto produto) {
        mailerPerguntas.enviar("Pergunta sobre o produto: " + produto.getNomeProduto(),
                "Usuário interessado: " + usuario.getLogin(),
                "Vendedor: " + produto.getDono().getLogin(),
                "Título: " + pergunta.getTitulo(),
                "Pergunta: " + pergunta.getPergunta());
    }


}
