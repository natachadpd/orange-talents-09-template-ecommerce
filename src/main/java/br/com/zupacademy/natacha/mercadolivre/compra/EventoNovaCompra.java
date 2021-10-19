package br.com.zupacademy.natacha.mercadolivre.compra;

import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class EventoNovaCompra {

    private final Set<EventoCompraSucesso> eventoCompraSucesso;


    public EventoNovaCompra(Set<EventoCompraSucesso> eventoCompraSucesso) {
        this.eventoCompraSucesso = eventoCompraSucesso;
    }

    public void processa(Compra compra) {
        if (compra.processadaComSucesso()) {

            eventoCompraSucesso.forEach(evento -> evento.processa(compra));
            return;
        }
    }

}
