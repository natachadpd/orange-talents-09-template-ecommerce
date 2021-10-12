package br.com.zupacademy.natacha.mercadolivre.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String pergunta;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(@NotBlank String titulo, @NotBlank String pergunta, @NotNull Usuario usuario,
                    @NotNull Produto produto) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getPergunta() {
        return pergunta;
    }

    public Produto getProduto(){
        return produto;
    }


}
