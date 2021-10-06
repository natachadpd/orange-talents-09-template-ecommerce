package br.com.zupacademy.natacha.mercadolivre.commons.error;

public class ErroDeFormularioForm {

    private String message;
    private String campo;

    public ErroDeFormularioForm(String message, String campo) {
        this.message = message;
        this.campo = campo;
    }

    public String getMessage() {
        return message;
    }

    public String getCampo() {
        return campo;
    }
}