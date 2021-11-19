package br.com.zupacademy.natacha.mercadolivre.email;

import org.springframework.stereotype.Component;

@Component
public class FakeMailer implements MailerEmail {

    @Override
    public void enviarEmail(DadosEmail dadosEmail) {
        System.out.println(dadosEmail);
    }
}
