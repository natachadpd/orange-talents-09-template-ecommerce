package br.com.zupacademy.natacha.mercadolivre.pergunta;

import br.com.zupacademy.natacha.mercadolivre.usuario.Usuario;
import br.com.zupacademy.natacha.mercadolivre.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta> {

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

    public Pergunta(@NotBlank String titulo, @NotBlank String pergunta,
                    @NotNull Usuario usuario,
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

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta1 = (Pergunta) o;
        return Objects.equals(titulo, pergunta1.titulo) && Objects.equals(pergunta, pergunta1.pergunta) && Objects.equals(produto, pergunta1.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, pergunta, produto);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}
