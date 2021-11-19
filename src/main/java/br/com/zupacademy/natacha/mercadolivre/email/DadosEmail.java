package br.com.zupacademy.natacha.mercadolivre.email;

public class DadosEmail {

    private String mensagem;
    private String assunto;
    private String remetente;
    private String destinatario;
    private String para;

    public DadosEmail(String mensagem, String assunto, String remetente, String destinatario, String para) {
        this.mensagem = mensagem;
        this.assunto = assunto;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.para = para;
    }

    @Override
    public String toString() {
        return

                "   Assunto: " + assunto +
                " - Remetente: " + remetente +
                " - Destinatario: " + destinatario +
                " - Para: " + para +
                " - Mensagem: " + mensagem  ;
    }

}
