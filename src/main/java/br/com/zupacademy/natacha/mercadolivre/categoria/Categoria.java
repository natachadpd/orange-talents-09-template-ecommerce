package br.com.zupacademy.natacha.mercadolivre.categoria;

import br.com.zupacademy.natacha.mercadolivre.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Produto> produto;

    @Deprecated
    public Categoria() {
    }

    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoriaMae, List<Produto> produto) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
        this.produto = produto;
    }


    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

}