package br.com.zupacademy.natacha.mercadolivre.controller.email;

public interface Mailer {
     void enviar(String produto, String remetente, String para, String titulo , String pergunta);


}
