package br.com.zupacademy.natacha.mercadolivre.compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private StatusTransacao status;
    @NotBlank
    private String idTransacaoGateway;
    @NotNull
    private LocalDateTime instante;
    @ManyToOne
    private @NotNull @Valid Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacaoGateway,
                     @NotNull @Valid Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }


    public boolean concluidaComSucesso() {

        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id) && status == transacao.status
                && Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway)
                && Objects.equals(instante, transacao.instante)
                && Objects.equals(compra, transacao.compra);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacaoGateway='" + idTransacaoGateway + '\'' +
                ", instante=" + instante +
                '}';
    }

    public StatusTransacao getStatus() {
        return status;
    }
}
