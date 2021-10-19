package br.com.zupacademy.natacha.mercadolivre.compra;

public enum StatusRetornoPagSeguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }
}
