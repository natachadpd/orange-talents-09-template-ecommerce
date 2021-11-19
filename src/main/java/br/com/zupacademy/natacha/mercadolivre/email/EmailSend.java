package br.com.zupacademy.natacha.mercadolivre.email;

import br.com.zupacademy.natacha.mercadolivre.compra.Compra;
import br.com.zupacademy.natacha.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.natacha.mercadolivre.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSend {

    @Autowired
    private FakeMailer mailer;


    public void novaPergunta(Pergunta pergunta, Produto produto) {
        System.out.println("==================================================================[NOVA PERGUNTA]==============================================================");
        String assunto = "Interesse no produto '" + pergunta.getProduto().getNomeProduto() + "'";
        String remetente = pergunta.getUsuario().getLogin();
        String destinatario = "pergunta@mercadolivre.com";
        String para = "Vendedor " + produto.getDono().getLogin();
        String mensagem = pergunta.getPergunta();
        String teste = pergunta.getPergunta();
        DadosEmail dadosEmail = new DadosEmail(mensagem, assunto, remetente, destinatario, para);
        mailer.enviarEmail(dadosEmail);
        System.out.println("===============================================================================================================================================");
    }

    public void novaCompra(Compra compra, Produto produto) {
        System.out.println("============================[NOVA COMPRA]==============================");
        String assunto = "Olá, você tem uma nova compra!";
        String remetente = "Comprador " + compra.getUsuario().getLogin();
        String destinatario = "novaCompra@mercadolivre.com";
        String para = "Vendedor " + produto.getDono().getLogin();
        String mensagem = "O cliente " + compra.getUsuario().getLogin() + " comprou o produto " + produto.getNomeProduto() + " , quantidade comprada " + compra.getQuantidade() + " e o meio de pagamento escolhido foi " + compra.getGateway() + ".";
        DadosEmail dadosEmail = new DadosEmail(mensagem, assunto, remetente, destinatario, para);
        mailer.enviarEmail(dadosEmail);
        System.out.println("==========================================================================");
    }

    public void transacaoSucesso(Compra compra) {
        System.out.println("============================[RESUMO DA COMPRA]==============================");
        String assunto = "Obaa! Parabéns, sua compra foi concluída com sucesso!!";
        String remetente = "Comprador " + compra.getUsuario().getLogin();
        String destinatario = "";
        String para = "Vendedor " + compra.getDono().getLogin();
        String mensagem = "A compra do produto " + compra.getProduto().getNomeProduto() + " foi concluída com sucesso! O meio de pagamento escolhido foi o  " + compra.getGateway() + ".";
        DadosEmail dadosEmail = new DadosEmail(mensagem, assunto, remetente, destinatario, para);
        mailer.enviarEmail(dadosEmail);
        System.out.println("==========================================================================");
    }




    public void transacaoSemSucesso(Compra compra) {
        System.out.println("============================[RESUMO DA COMPRA]==============================");
        String assunto = "Que pena! Não foi possível concluir a compra!";
        String remetente = "Comprador " + compra.getUsuario().getLogin();
        String destinatario = "";
        String para = "Vendedor " + compra.getDono().getLogin();
        String mensagem = "Infelizmente a compra do produto " + compra.getProduto().getNomeProduto() + " não foi concluída. refaça o processo: http://localhost:8080/retorno-" + compra.getGateway() + "/" + compra.getId();
        DadosEmail dadosEmail = new DadosEmail(mensagem, assunto, remetente, destinatario, para);
        mailer.enviarEmail(dadosEmail);
        System.out.println("==========================================================================");
    }

}
